package com.rogercw.weather.web.controller;

import com.rogercw.weather.pojo.WeatherResponse;
import com.rogercw.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: rogercw
 * @Date: 2018/7/23 19:47
 * @Version 1.0
 */
@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/findByCityName/{cityName}")
    public WeatherResponse findByCityName(@PathVariable String cityName){
        return weatherService.findByCityName(cityName);
    }

    @GetMapping("/findByCityId/{cityId}")
    public WeatherResponse findByCityId(@PathVariable String cityId){
        return weatherService.findByCityId(cityId);
    }
}
