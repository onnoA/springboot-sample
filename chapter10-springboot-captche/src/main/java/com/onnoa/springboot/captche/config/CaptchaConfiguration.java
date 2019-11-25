package com.onnoa.springboot.captche.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/11/22 10:51
 */
@Component
public class CaptchaConfiguration {


    @Bean(name = "captchaProducer")
    public DefaultKaptcha captchaProducer(){
        DefaultKaptcha captchaProducer = new DefaultKaptcha();
        Properties properties = new Properties();
        // 图片边框，合法值：yes , no 默认为: yes
        properties.setProperty(Constants.KAPTCHA_BORDER, "yes");
        // 边框颜色，合法值： r,g,b (and optional alpha) 或者 white,black,blue 默认为： black
        properties.setProperty(Constants.KAPTCHA_BORDER_COLOR, "red");
        // 图片宽 默认：200
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, "200");
        // 图片高 默认：50
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, "50");
        //图片实现类 默认： com.google.code.kaptcha.impl.DefaultKaptcha
        properties.setProperty(Constants.KAPTCHA_PRODUCER_IMPL,"com.google.code.kaptcha.impl.DefaultKaptcha");
        // 文本实现类 默认：com.google.code.kaptcha.text.impl.DefaultTextCreator
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_IMPL,"com.google.code.kaptcha.text.impl.DefaultTextCreator");
        // 文本集合，验证码值从此集合中获取 默认值: abcde2345678gfynmnpwx
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        // 验证码长度 默认: 5
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        // 字体 默认： Arial(宋体), Courier
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "宋体,楷体,微软雅黑");
        // 字体大小 默认： 40px
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "50");
        // 字体颜色，合法值： r,g,b 或者 white,black,blue. 默认：black
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");
        // 文字间隔 默认： 2
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE,"2");
        // 干扰实现类 默认： com.google.code.kaptcha.impl.DefaultNoise
        properties.setProperty(Constants.KAPTCHA_NOISE_IMPL,"com.google.code.kaptcha.impl.DefaultNoise");
        // 干扰 颜色，合法值： r,g,b 或者 white,black,blue. 默认：black
        properties.setProperty(Constants.KAPTCHA_NOISE_COLOR,"red");
        //图片样式：
        // <br />水纹 com.google.code.kaptcha.impl.WaterRipple
        // <br /> 鱼眼 com.google.code.kaptcha.impl.FishEyeGimpy
        // <br /> 阴影 com.google.code.kaptcha.impl.ShadowGimpy
        // 默认：com.google.code.kaptcha.impl.WaterRipple
        properties.setProperty(Constants.KAPTCHA_OBSCURIFICATOR_IMPL,"com.google.code.kaptcha.impl.WaterRipple");
        // 背景实现类 默认：com.google.code.kaptcha.impl.DefaultBackground
        properties.setProperty(Constants.KAPTCHA_BACKGROUND_IMPL,"com.google.code.kaptcha.impl.DefaultBackground");
        //背景颜色渐变，开始颜色 默认: light grey(淡灰色)
        properties.setProperty(Constants.KAPTCHA_BACKGROUND_CLR_FROM,"white");
        // 背景颜色渐变， 结束颜色 默认：white
        properties.setProperty(Constants.KAPTCHA_BACKGROUND_CLR_TO,"white");
        // 文字渲染器 默认：com.google.code.kaptcha.text.impl.DefaultWordRenderer
        properties.setProperty(Constants.KAPTCHA_WORDRENDERER_IMPL,"com.google.code.kaptcha.text.impl.DefaultWordRenderer");
        // session key 默认：KAPTCHA_SESSION_KEY
        properties.setProperty(Constants.KAPTCHA_SESSION_KEY,"KAPTCHA_SESSION_KEY");
        // session date 默认：KAPTCHA_SESSION_DATE
        properties.setProperty(Constants.KAPTCHA_SESSION_DATE,"KAPTCHA_SESSION_DATE");

        properties.setProperty(Constants.KAPTCHA_SESSION_CONFIG_KEY, "code");
        Config config = new Config(properties);
        captchaProducer.setConfig(config);
        return captchaProducer;
    }

}
