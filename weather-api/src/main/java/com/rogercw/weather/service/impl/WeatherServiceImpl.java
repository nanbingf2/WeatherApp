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

    //*****************************私有方法*************************************
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
            throw new RuntimeException("缓存中没有数据");
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
