package com.onnoa.springboot.apache.dubbo.consumer.controller;

import com.onnoa.springboot.apache.dubbo.provider.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Reference
    private HelloService helloService;

    @GetMapping(value = "/echo/{string}")
    public String echo(@PathVariable String string) {
        return helloService.sayHi(string);
    }
}
