package com.rogercw.weather.feign;

import com.rogercw.weather.pojo.City;
import com.rogercw.weather.pojo.WeatherResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: rogercw
 * @Date: 2018/7/24 21:57
 * @Version 1.0
 */
@Component
public class WeatherFallBack implements WeatherFeignClient{
    @Override
    public List<City> getCityList() throws Exception {
        return null;
    }

    @Override
    public WeatherResponse findByCityId(String cityId) {
        return null;
    }
}
