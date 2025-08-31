package com.f4stsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class F4stSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(F4stSearchApplication.class, args);
    }
}
