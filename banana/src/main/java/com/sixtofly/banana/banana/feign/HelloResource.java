package com.sixtofly.banana.banana.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "apple")
@RequestMapping("/hello")
public interface HelloResource {

    @GetMapping("/world")
    String helloWorld();
}
