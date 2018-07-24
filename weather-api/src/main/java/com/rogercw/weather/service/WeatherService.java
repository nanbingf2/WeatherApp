package com.rogercw.weather.service;

import com.rogercw.weather.pojo.WeatherResponse;

/**
 * @Author: rogercw
 * @Date: 2018/7/23 19:30
 * @Version 1.0
 */
public interface WeatherService {

    /**
     * 根据城市名称查询天气信息
     * @param cityName
     * @return
     */
    WeatherResponse findByCityName(String cityName);

    /**
     * 根据城市编码查询天气信息
     * @param cityId
     * @return
     */
    WeatherResponse findByCityId(String cityId);

}
