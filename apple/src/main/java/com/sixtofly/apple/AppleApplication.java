package com.sixtofly.apple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AppleApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppleApplication.class, args);
    }

}
