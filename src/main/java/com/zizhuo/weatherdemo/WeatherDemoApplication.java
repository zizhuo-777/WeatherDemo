package com.zizhuo.weatherdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zizhuo.weatherdemo.dao")
public class WeatherDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherDemoApplication.class, args);
    }

}
