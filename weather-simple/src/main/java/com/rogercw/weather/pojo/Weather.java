package com.rogercw.weather.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: rogercw
 * @Date: 2018/7/23 19:27
 * @Version 1.0
 */
public class Weather implements Serializable {

    private Yesterday yesterday;
    private String city;
    private String aqi;
    private String ganmao;
    private String wendu;
    private List<Forecast> forecast;

    public Yesterday getYesterday() {
        return yesterday;
    }

    public void setYesterday(Yesterday yesterday) {
        this.yesterday = yesterday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }
}
