package com.onnoa.springcloud.alibaba.consumer.service.fallback;

import com.onnoa.springcloud.alibaba.consumer.service.EchoService;
import org.springframework.stereotype.Component;

@Component
public class EchoServiceFallback implements EchoService {
    @Override
    public String echo(String string) {
        return "你的网络有问题，请重试！";
    }

    @Override
    public String port() {
        return "网络不稳定，请稍后重试";
    }
}
