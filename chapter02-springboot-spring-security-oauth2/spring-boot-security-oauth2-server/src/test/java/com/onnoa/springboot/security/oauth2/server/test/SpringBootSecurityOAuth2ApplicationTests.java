package com.onnoa.springboot.security.oauth2.server.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/10/31 15:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootSecurityOAuth2ApplicationTests {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void getEncoderPassword(){
        System.out.println(passwordEncoder.encode("secretId"));
    }
}
