package com.onnoa.springboot.commons.utils;

import java.math.BigDecimal;

/**
 * @Description: 金额运算帮助类
 * @Author: onnoA
 * @Date: 2019/11/13 14:34
 */
public class MoneyArithUtil {


    /**
     *
     * @Description 元转分
     * @param yuan
     * @return
     */
    public static long yuan2fen(Double yuan) {
        if (yuan == null) {
            return 0L;
        }
        return yuan2fen(yuan.toString());
    }

    public static long yuan2fen(String yuan) {
        BigDecimal yuanBd = new BigDecimal(yuan);
        return yuanBd.movePointRight(2).longValue();
    }

    /**
     *
     * @Description 分转元
     * @param fen
     * @return
     */
    public static double fen2yuan(Long fen) {
        if (fen == null) {
            return 0.0;
        }
        BigDecimal fenBd = new BigDecimal(fen.toString());
        return fenBd.movePointLeft(2).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }

}
