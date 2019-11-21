package com.onnoa.springboot.apache.dubbo.provider.service.impl;

import com.onnoa.springboot.apache.dubbo.provider.service.HelloService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@Service
@RefreshScope
public class HelloServiceImpl implements HelloService {

    @Value("${dubbo.protocol.port}")
    private String port;

    @Value("${user.name}")
    private String name;

    @Override
    public String sayHi(String string) {
        return "Hello Apache Duboo " + string + "from port: " + port + "name: " + name;
    }
}
