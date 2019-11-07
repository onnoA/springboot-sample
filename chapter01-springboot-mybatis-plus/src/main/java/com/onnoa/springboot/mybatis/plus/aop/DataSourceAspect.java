package com.onnoa.springboot.mybatis.plus.aop;

import com.onnoa.springboot.mybatis.plus.enums.DataSourceEnum;
import com.onnoa.springboot.mybatis.plus.multidatasourceconfig.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/10/30 11:11
 */
@Component
@Slf4j
@Aspect
@Order(-100)
public class DataSourceAspect {

    @Pointcut("execution(* com.onnoa.springboot.mybatis.plus.mapper.db1..*.*(..))")
    private void db1Aspect() {
    }

    @Before("db1Aspect()")
    public void doBefore() {
        log.info("切换到db1 数据源...");
        DataSourceContextHolder.setDataSource(DataSourceEnum.DB1.getValue());
    }

    @Before("execution(* com.onnoa.springboot.mybatis.plus.mapper.db2..*.*(..))")
    public void db2() {
        log.info("切换到db2 数据源...");
        DataSourceContextHolder.setDataSource(DataSourceEnum.DB2.getValue());
    }

    /*@After("pointCut()")
    public void doAfter() {
        DataSourceContextHolder.clear();
    }*/
}
