package com.rogercw.weather.service;

/**
 * @Author: rogercw
 * @Date: 2018/7/23 19:30
 * @Version 1.0
 */
public interface WeatherService {

    /**
     * 根据城市id同步（更新）天气信息
     * @param cityId
     */
    void weatherSyncByCityId(String cityId);
}
