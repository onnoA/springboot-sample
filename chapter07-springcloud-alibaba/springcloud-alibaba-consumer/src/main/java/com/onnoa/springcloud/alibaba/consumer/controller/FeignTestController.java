package com.onnoa.springcloud.alibaba.consumer.controller;

import com.onnoa.springcloud.alibaba.consumer.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class FeignTestController {

    @Autowired
    private EchoService echoService;

    @Value("${user.name}")
    private String name;

    @GetMapping(value = "/feign/echo/{str}")
    public String echo(@PathVariable("str") String str){
        return echoService.echo(str);
    }

    @GetMapping(value = "/feign/echo")
    public String echo(){
        return echoService.echo(name);
    }


    @GetMapping(value = "/echo/port")
    public String port(){
        return echoService.port();
    }
}
