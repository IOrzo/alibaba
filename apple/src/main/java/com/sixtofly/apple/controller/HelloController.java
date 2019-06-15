package com.sixtofly.apple.controller;

import com.sixtofly.apple.config.NacosConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private NacosConfig nacosConfig;

    @GetMapping("/world")
    public String hello(){
        return "hello world";
    }

    @GetMapping("/nacos")
    public String nacos(){
        return nacosConfig.getApp() + ":" + nacosConfig.getVersion();
    }
}
