package com.rogercw.weather.feign;

import com.rogercw.weather.pojo.City;
import com.rogercw.weather.pojo.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Author: rogercw
 * @Date: 2018/7/24 15:07
 * @Version 1.0
 */
@FeignClient(value = "weather-gateway-zuul",fallback = WeatherFallBack.class)
public interface WeatherFeignClient {

    @GetMapping("/city/getCityList")
    List<City> getCityList() throws Exception;

    @GetMapping("/weather/findByCityId/{cityId}")
    WeatherResponse findByCityId(@PathVariable("cityId") String cityId);
}
