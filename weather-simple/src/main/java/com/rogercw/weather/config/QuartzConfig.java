package com.rogercw.weather.config;

import com.rogercw.weather.quartz.WeatherDataSnycJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: rogercw
 * @Date: 2018/7/24 10:51
 * @Version 1.0
 * Quartz的配置类
 */
@Configuration
public class QuartzConfig {
    private final int TIME_OUT=30;

    //1:配置JobDetail
    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob(WeatherDataSnycJob.class)
                .withIdentity("weatherDataSyncJob")
                .storeDurably()
                .build();
    }

    //创建触发器Trigger
    @Bean
    public Trigger trigger(){
        ScheduleBuilder scheduleBuilder= SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInMinutes(TIME_OUT)
                .repeatForever();
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withIdentity("weatherDataSyncTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
