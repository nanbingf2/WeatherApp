package com.rogercw.weather.feign;

import com.rogercw.weather.pojo.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Author: rogercw
 * @Date: 2018/7/24 14:52
 * @Version 1.0
 */
@FeignClient(name = "weather-gateway-zuul",fallback = CityFallBack.class)
public interface CityFeignClient {

    @GetMapping("/city/getCityList")
    List<City> getCityList() throws Exception;
}
