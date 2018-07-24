package com.rogercw.weather.feign;

import com.rogercw.weather.pojo.City;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: rogercw
 * @Date: 2018/7/24 21:53
 * @Version 1.0
 */
@Component
public class CityFallBack implements CityFeignClient{
    @Override
    public List<City> getCityList() throws Exception {
        return null;
    }
}
