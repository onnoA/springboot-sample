package com.onnoa.springboot.apache.dubbo.provider.service.impl;

import com.onnoa.springboot.apache.dubbo.provider.service.HelloService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHi(String string) {
        return "Hello Apache Duboo" + string;
    }
}
