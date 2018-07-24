package com.rogercw.weather.quartz;

import com.rogercw.weather.pojo.City;
import com.rogercw.weather.service.CityDataService;
import com.rogercw.weather.service.WeatherService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * @Author: rogercw
 * @Date: 2018/7/23 21:57
 * @Version 1.0
 * 天气数据同步任务
 */
public class WeatherDataSnycJob extends QuartzJobBean {
    private final Logger logger=LoggerFactory.getLogger(WeatherDataSnycJob.class);
    @Autowired
    private CityDataService cityDataService;
    @Autowired
    private WeatherService weatherService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //1：获取城市数据列表
        List<City> cityList=null;
        try {
            cityList=cityDataService.getCityList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //2：根据城市id同步天气信息
        if (cityList != null) {
            cityList.forEach(city -> {
                weatherService.weatherSyncByCityId(city.getCityId());
                logger.info(city.getCityName()+"天气数据同步完成");
            });
        }
    }
}
