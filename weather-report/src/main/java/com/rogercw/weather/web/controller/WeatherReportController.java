package com.rogercw.weather.web.controller;

import com.rogercw.weather.feign.WeatherFeignClient;
import com.rogercw.weather.pojo.City;
import com.rogercw.weather.pojo.Weather;
import com.rogercw.weather.pojo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: rogercw
 * @Date: 2018/7/24 11:25
 * @Version 1.0
 * 天气预报控制类
 */
@RestController
@RequestMapping("/report")
public class WeatherReportController {

    @Autowired
    private WeatherFeignClient weatherFeignCient;

    @GetMapping("/findByCityId/{cityId}")
    public ModelAndView findByCityId(@PathVariable String cityId, Model model) throws Exception {
        model.addAttribute("cityId",cityId);
        List<City> cityList=weatherFeignCient.getCityList();
        model.addAttribute("cityList",cityList);

        WeatherResponse weatherResponse=weatherFeignCient.findByCityId(cityId);
        Weather weather=null;
        if (weatherResponse != null) {
            weather= weatherResponse.getData();
        }
        model.addAttribute("weather",weather);
        return new ModelAndView("weather/report.html","weatherModel",model);
    }
}
