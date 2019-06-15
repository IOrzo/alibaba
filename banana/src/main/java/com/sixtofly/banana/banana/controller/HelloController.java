package com.sixtofly.banana.banana.controller;

import com.sixtofly.banana.banana.feign.HelloResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author xie yuan bing
 * @date 2019-06-15 16:58
 * @description
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;
    @Resource
    private HelloResource helloResource;

    @GetMapping("/banana")
    public String hello(){
        return "banana hello world!";
    }

    @GetMapping("/connectApple")
    public String connectApple(){
        String apple = restTemplate.getForObject("http://apple/hello/world", String.class);
        return String.format("get message from apple service: %s",  apple);
    }

    @GetMapping("/connectFeign")
    public String connectFeign(){
        return helloResource.helloWorld();
    }
}
