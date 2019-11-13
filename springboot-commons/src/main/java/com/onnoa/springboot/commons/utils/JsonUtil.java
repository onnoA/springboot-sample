package com.onnoa.springboot.commons.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @Description: JSON数据转换工具类
 * @Author: onnoA
 * @Date: 2019/11/13 14:40
 */
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 功能描述: json字符串转为Map对象
     * @auther: onnoA
     * @date: 2019/11/13 14:44
     */
    @SuppressWarnings("unchecked")
    public static final Map<String, Object> json2Map(String json) {
        if (json == null) {
            return null;
        }

        try {
            return objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String map2Json(Map<String,Object> map) {
        return obj2Json(map);
    }

    /**
     * 功能描述: json字符串转为对象
     * @auther: onnoA
     * @date: 2019/11/13 14:44
     */
    public static final <T> T json2Obj(String content, Class<T> clazz) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        try {
            return objectMapper.readValue(content, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 功能描述: 对象转为字符串
     * @auther: onnoA
     * @date: 2019/11/13 14:44
     */
    public static String obj2Json(Object obj) {
        if (obj == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 反序列化复杂Collection如List<Bean>, 先使用函数createCollectionType构造类型,然后调用本函数.
     * @param jsonString
     * @param javaType
     * @return
     */
    public static <T> T fromJson(String jsonString, JavaType javaType) {
        try {
            return objectMapper.readValue(jsonString, javaType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  添加一个反序列化方法，上面的一个不知道怎么用
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
