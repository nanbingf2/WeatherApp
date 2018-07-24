package com.rogercw.weather.web.controller;

import com.rogercw.weather.service.CityDataService;
import com.rogercw.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
    private CityDataService cityDataService;
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/findByCityId/{cityId}")
    public ModelAndView findByCityId(@PathVariable String cityId, Model model) throws Exception {
        model.addAttribute("cityId",cityId);
        //获取城市列表数据
        model.addAttribute("cityList",cityDataService.getCityList());
        //获取天气信息
        model.addAttribute("weather",weatherService.findByCityId(cityId).getData());
        return new ModelAndView("weather/report.html","weatherModel",model);
    }
}
