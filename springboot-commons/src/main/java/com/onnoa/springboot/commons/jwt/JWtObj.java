package com.onnoa.springboot.commons.jwt;

/**
 * @Description: jwt 自定义对象
 * @Author: onnoA
 * @Date: 2019/11/29 10:58
 */

public class JWtObj {

    private int id;
    private String username;

    public JWtObj() {

    }

    public JWtObj(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public JWtObj setId(int id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public JWtObj setUsername(String username) {
        this.username = username;
        return this;
    }



}
