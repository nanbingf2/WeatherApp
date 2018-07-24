package com.rogercw.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class WeatherGatewayZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherGatewayZuulApplication.class, args);
    }
}
