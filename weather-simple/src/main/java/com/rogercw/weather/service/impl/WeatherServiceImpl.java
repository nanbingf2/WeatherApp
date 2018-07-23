package com.rogercw.weather.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rogercw.weather.pojo.WeatherResponse;
import com.rogercw.weather.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: rogercw
 * @Date: 2018/7/23 19:32
 * @Version 1.0
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    private final Logger logger=LoggerFactory.getLogger(WeatherServiceImpl.class);
    private final String WEATHER_URL="http://wthrcdn.etouch.cn/weather_mini";
    private final Long REDIS_TIMEOUT=30L;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public WeatherResponse findByCityName(String cityName) {
        String url=this.WEATHER_URL+"?city="+cityName;
        return this.findByUrl(url);
    }

    @Override
    public WeatherResponse findByCityId(String cityId) {
        String url=this.WEATHER_URL+"?citykey="+cityId;
        return this.findByUrl(url);
    }

    @Override
    public void weatherSyncByCityId(String cityId) {
        String url=this.WEATHER_URL+"?citykey="+cityId;
        this.saveWeatherDataToRedis(url);
    }

    //*****************************私有方法**************************************
    //将天气数据保存到redis缓存中
    private void saveWeatherDataToRedis(String url) {
        String key = url;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String strResponse = restTemplate.getForObject(url, String.class);
        //将查询出的数据直接保存到redis缓存中
        ops.set(key, strResponse, REDIS_TIMEOUT, TimeUnit.MINUTES);
    }

    //通过url查询天气信息
    private WeatherResponse findByUrl(String url) {
        String key=url;
        String strResponse=null;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        //查询缓存
        if (stringRedisTemplate.hasKey(key)){
            strResponse=ops.get(key);
            logger.info("从缓存中获取数据");
        }else{
            strResponse = restTemplate.getForObject(url, String.class);
            //将查询出的数据存到缓存中
            ops.set(key,strResponse, REDIS_TIMEOUT,TimeUnit.MINUTES);
        }
        ObjectMapper objectMapper=new ObjectMapper();
        WeatherResponse weatherResponse = null;
        try {
            //将字符串转换为Json对象
            weatherResponse = objectMapper.readValue(strResponse, WeatherResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherResponse;
    }
}
