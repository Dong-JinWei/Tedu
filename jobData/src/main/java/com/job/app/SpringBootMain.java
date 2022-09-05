package com.job.app;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.job.app.dao")
public class SpringBootMain {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMain.class, args);
    }
}
