package com.rogercw.weather.service.impl;

import com.rogercw.weather.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

}
