package com.onnoa.springboot.security.jwt.utils;

import com.onnoa.springboot.security.jwt.common.BusinessException;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

/**
 * @Description: 前端传参校验工具类
 * @Author: onnoA
 * @Date: 2019/11/8 16:23
 */
public class ValidationUtil {

    private static javax.validation.Validator validator = Validation.
            byProvider(HibernateValidator.class)
            .configure()
            .buildValidatorFactory()
            .getValidator();

    /**
     * @param obj 需要验证的对象
     * @catalog hibernate
     * @title hibernate注解验证参数
     * @description 使用hibernate注解验证类中参数的合法性
     */
    public static <T> void validate(T obj) {
        if (obj == null) {
            throw BusinessException.COMMON_PARAMS_NOT_NULL.format("【参数实体】");
        }
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        // 抛出检验异常
        if (!constraintViolations.isEmpty()) {
            StringBuffer sb = new StringBuffer();
            constraintViolations.stream().forEach(violation -> {
                sb.append(violation.getPropertyPath())
                        .append(violation.getMessage())
                        .append(",");
            });
            sb.deleteCharAt(sb.length() - 1);
            throw  BusinessException.COMMON_PARAMS_IS_ILLICIT.format(String.format("参数校验失败:%s", sb.toString()));
        }
    }
}
