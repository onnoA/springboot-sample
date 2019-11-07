package com.onnoa.springboot.mybatis.plus.enums;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/10/30 11:09
 */
public enum  DataSourceEnum {

    DB1("db1"),DB2("db2");

    private String value;

    DataSourceEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}
