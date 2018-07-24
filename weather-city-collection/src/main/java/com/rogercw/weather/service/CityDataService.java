package com.rogercw.weather.service;

import com.rogercw.weather.pojo.City;

import java.io.IOException;
import java.util.List;

/**
 * @Author: rogercw
 * @Date: 2018/7/23 21:19
 * @Version 1.0
 */
public interface CityDataService {

    /**
     * 获取城市列表
     * @return
     */
    List<City> getCityList() throws IOException, Exception;
}
