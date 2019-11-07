package com.onnoa.springboot.mybatis.plus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.onnoa.springboot.mybatis.plus.entity.OrderInfo;
import com.onnoa.springboot.mybatis.plus.entity.TbUser;
import com.onnoa.springboot.mybatis.plus.mapper.db1.TbUserMapper;
import com.onnoa.springboot.mybatis.plus.mapper.db2.OrderInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/10/30 11:23
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootMyBatisPlusTests {

    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Test
    public void multiDataSourceTest() {
        Page<TbUser> tbUserPage = new Page<>(1,3);
        IPage<TbUser> tbUserIPage = tbUserMapper.selectPage(tbUserPage, null);
        System.out.println("总记录数："+tbUserIPage.getTotal());
        System.out.println("当前页:"+tbUserIPage.getCurrent());
        List<TbUser> records = tbUserIPage.getRecords();
        for (TbUser record : records) {
            System.out.println(record);
        }
        /*List<TbUser> tbUserList = tbUserMapper.selectList(null);
        for (TbUser tbUser : tbUserList) {
            System.out.println("tbUser:" + tbUser);
        }*/
        QueryWrapper<OrderInfo> qw = new QueryWrapper<>();
        qw.eq("pay_order_no","1012019071000071845036331");
        List<OrderInfo> orderInfos = orderInfoMapper.selectList(qw);
        orderInfos.forEach(System.out::println);
    }

}
