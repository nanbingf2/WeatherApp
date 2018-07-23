package com.rogercw.weather.pojo;

import java.io.Serializable;

/**
 * @Author: rogercw
 * @Date: 2018/7/23 19:29
 * @Version 1.0
 */
public class WeatherResponse implements Serializable {

    private Weather data;
    private String status;
    private String desc;

    public Weather getData() {
        return data;
    }

    public void setData(Weather data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
