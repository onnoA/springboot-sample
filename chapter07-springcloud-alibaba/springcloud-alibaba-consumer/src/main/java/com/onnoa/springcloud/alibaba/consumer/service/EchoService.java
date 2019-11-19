package com.onnoa.springcloud.alibaba.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-provider")
public interface EchoService {

    @GetMapping(value = "/echo/{string}")
    public String echo(@PathVariable("string") String string);

    @GetMapping(value = "/echo/port")
    String port();
}
