package com.onnoa.springcloud.alibaba.consumer.controller;

import com.onnoa.springcloud.alibaba.consumer.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignTestController {

    @Autowired
    private EchoService echoService;

    @GetMapping(value = "/feign/echo/{str}")
    public String echo(@PathVariable("str") String str){
        return echoService.echo(str);
    }

}
