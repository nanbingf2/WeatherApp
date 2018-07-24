package com.rogercw.weather.web.controller;

import com.rogercw.weather.pojo.City;
import com.rogercw.weather.service.CityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: rogercw
 * @Date: 2018/7/24 13:23
 * @Version 1.0
 */
@RestController
public class CityController {

    @Autowired
    private CityDataService cityDataService;

    @GetMapping("/getCityList")
    public List<City> getCityList() throws Exception {
        return cityDataService.getCityList();
    }
}
