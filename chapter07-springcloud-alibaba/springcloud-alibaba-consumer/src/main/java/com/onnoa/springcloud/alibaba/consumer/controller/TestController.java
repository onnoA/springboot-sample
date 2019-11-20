package com.onnoa.springcloud.alibaba.consumer.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    private final RestTemplate restTemplate;



    @Autowired
    public TestController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/echo/{str}")
    public String echo(@PathVariable("str") String str){
        return restTemplate.getForObject("http://service-provider/echo/ "+ str,String.class);
    }


    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("https://www.baidu.com/", String.class);
        System.out.println(forObject);
    }
}
