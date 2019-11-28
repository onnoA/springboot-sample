package com.onnoa.springboot.captche.common.util;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.onnoa.springboot.captche.common.result.ResultBean;
import com.onnoa.springboot.captche.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;


/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/11/22 16:54
 */
@Slf4j
@Configuration
public class TokenUtil {

    @Autowired
    private DefaultKaptcha captchaProducer;
    @Autowired
    private RedisUtil redisUtil;


    public ResultBean createVerificationCode() {
        try (ByteArrayOutputStream byteOutPutStream = new ByteArrayOutputStream()) {
            /*response.setDateHeader("Expires", 0);
            // Set standard HTTP/1.1 no-cache headers.
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            // Set standard HTTP/1.0 no-cache header.
            response.setHeader("Pragma", "no-cache");
            //  定义response输出类型为image/jpeg类型
            response.setContentType("image/jpeg");*/
            String captchaText = captchaProducer.createText();
            String shortUUID = UuidUtil.getShortUUID();
            redisUtil.set(shortUUID, captchaText, 5 * 60L);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage image = captchaProducer.createImage(captchaText);
            ImageIO.write(image, "jpg", byteOutPutStream);
            //将流转为byte数组
            byte[] bytes = byteOutPutStream.toByteArray();
            // 加密后传送给前端
            String jpgEncode = Base64.getEncoder().encodeToString(bytes);
            HashMap<String, Object> map = new HashMap<>();
            map.put("jpg", jpgEncode);
            map.put("jpgKey", shortUUID);
            log.info("*****服务端验证码：*******" + captchaText);
            return ResultBean.buildResultBeanVO(ResultCode.SUCCESS, map);
        } catch (IOException e) {
            log.info("*****服务端验证码异常*******" + e.getMessage());
            e.printStackTrace();
        }
        return ResultBean.buildResultBeanVO(ResultCode.FAIL, "验证码生成失败，请重试！");
    }


}
