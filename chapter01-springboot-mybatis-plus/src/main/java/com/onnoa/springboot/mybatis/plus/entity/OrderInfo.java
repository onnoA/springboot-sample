package com.onnoa.springboot.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/10/30 11:26
 */
@TableName("pay_order_info")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 4133688674110967598L;

    private Long id;

    // 支付流水号
    private String payOrderNo;

    // 订单号
    private String orderNo;

    // appId
    private String appId;

    // 收款人uid
    private String beneficiaryUid;

    // 兵团号/商户号  与收款人必填其一
    private String businessNo;

    // 付款人uid
    private String payerUid;

    // 订单总金额（单位：分）
    private long orderTotalMoney;

    // 收入方式（0：无；1：支付宝；2：微信；3：银联；99：未指定或线下操作）
    private int incomeWay;

    // 余额收入（单位：分）
    private long balanceIncome;

    // 赠送余额收入（单位：分）
    private long giveBalanceIncome;

    // 第三方收入金额（单位：分）
    private long otherIncomeMoney;

    // 支付方式,PayWayEnum
    private int payWay;

    // 余额抵扣（单位：分）
    private long balanceDeduction;

    // 赠送余额抵扣（单位：分）
    private long giveBalanceDeduction;

    // 第三方支付金额（单位：分）
    private long otherPayMoney;

    // 支付状态（1：未支付、2：支付完成、3：已取消、4：部分退款、5：已退款）',
    private int payStatus;

    // 商品名称
    private String subject;

    // 商品描述
    private String body;

    // 业务数据
    private String bizContent;

    // 回调地址
    private String callUrl;

    // 回调状态(0表示未回调，1表示回调成功，2表示回调失败)
    private int callStatus;

    // 第三方流水号（第三方支付完成后才有）
    private String thirdPartyNo;

    /**
     * 禁用的支付方式,值来源PayWayEnum的value。 用逗号隔开，如为“0,3”表示禁用余额和银联
     */
    private String disablePayWay;

    // 创建时间
    private Date createTime;

    //用户支付完成后重定向时间（由前端提供）
    private Date redirectTime;

    //支付完成时的系统时间
    private Date paySystemTime;

    // 支付时间
    private Date payTime;

    // 失效时间
    private Date failureTime;

    // 备注
    private String remark;

    // 平台id
    private int sysId;

    // 第四方支付渠道
    private String fourthPartyChannel;

    // 第四方支付银行名称
    private String fourthPartyBankName;

    // 第四方支付流水号
    private String fourthPartyNo;

    // 支付宝或者微信收款码 因为该model很多地方用，就直接加到这
    @TableField(exist=false)
    private String authCode;

    @TableField(exist=false)
    private String thirdPayOrderNo;

    @TableField(exist=false)
    private String payWayName2;

    @TableField(exist=false)
    private String payWayName3;

    @TableField(exist=false)
    private Date callPayTime;

    // 是否更新icbcOrder状态
    @TableField(exist=false)
    private boolean notUpdateIcbcOrderStatus = false;

    //公众号支付
    @TableField(exist=false)
    private String openId;

    /** 如果微信支付是H5，须传用户端IP，支持IPV4，IPV6 */
    @TableField(exist=false)
    private String spbillCreateIp;

    @TableField(exist=false)
    private Integer qryTimes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo == null ? null : payOrderNo.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getBeneficiaryUid() {
        return beneficiaryUid;
    }

    public void setBeneficiaryUid(String beneficiaryUid) {
        this.beneficiaryUid = beneficiaryUid == null ? null : beneficiaryUid.trim();
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getPayerUid() {
        return payerUid;
    }

    public void setPayerUid(String payerUid) {
        this.payerUid = payerUid == null ? null : payerUid.trim();
    }

    public long getOrderTotalMoney() {
        return orderTotalMoney;
    }

    public void setOrderTotalMoney(long orderTotalMoney) {
        this.orderTotalMoney = orderTotalMoney;
    }

    public int getIncomeWay() {
        return incomeWay;
    }

    public void setIncomeWay(int incomeWay) {
        this.incomeWay = incomeWay;
    }

    public long getBalanceIncome() {
        return balanceIncome;
    }

    public void setBalanceIncome(long balanceIncome) {
        this.balanceIncome = balanceIncome;
    }

    public long getGiveBalanceIncome() {
        return giveBalanceIncome;
    }

    public void setGiveBalanceIncome(long giveBalanceIncome) {
        this.giveBalanceIncome = giveBalanceIncome;
    }

    public long getOtherIncomeMoney() {
        return otherIncomeMoney;
    }

    public void setOtherIncomeMoney(long otherIncomeMoney) {
        this.otherIncomeMoney = otherIncomeMoney;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public long getBalanceDeduction() {
        return balanceDeduction;
    }

    public void setBalanceDeduction(long balanceDeduction) {
        this.balanceDeduction = balanceDeduction;
    }

    public long getGiveBalanceDeduction() {
        return giveBalanceDeduction;
    }

    public void setGiveBalanceDeduction(long giveBalanceDeduction) {
        this.giveBalanceDeduction = giveBalanceDeduction;
    }

    public long getOtherPayMoney() {
        return otherPayMoney;
    }

    public void setOtherPayMoney(long otherPayMoney) {
        this.otherPayMoney = otherPayMoney;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    public String getBizContent() {
        return bizContent;
    }

    public void setBizContent(String bizContent) {
        this.bizContent = bizContent;
    }

    public String getCallUrl() {
        return callUrl;
    }

    public void setCallUrl(String callUrl) {
        this.callUrl = callUrl == null ? null : callUrl.trim();
    }

    public int getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(int callStatus) {
        this.callStatus = callStatus;
    }

    public String getThirdPartyNo() {
        return thirdPartyNo;
    }

    public void setThirdPartyNo(String thirdPartyNo) {
        this.thirdPartyNo = thirdPartyNo == null ? null : thirdPartyNo.trim();
    }

    public String getDisablePayWay() {
        return disablePayWay;
    }

    public void setDisablePayWay(String disablePayWay) {
        this.disablePayWay = disablePayWay == null ? null : disablePayWay.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getRedirectTime() {
        return redirectTime;
    }

    public void setRedirectTime(Date redirectTime) {
        this.redirectTime = redirectTime;
    }

    public Date getPaySystemTime() {
        return paySystemTime;
    }

    public void setPaySystemTime(Date paySystemTime) {
        this.paySystemTime = paySystemTime;
    }

    public Date getFailureTime() {
        return failureTime;
    }

    public void setFailureTime(Date failureTime) {
        this.failureTime = failureTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public int getSysId() {
        return sysId;
    }

    public void setSysId(int sysId) {
        this.sysId = sysId;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getThirdPayOrderNo() {
        return thirdPayOrderNo;
    }

    public void setThirdPayOrderNo(String thirdPayOrderNo) {
        this.thirdPayOrderNo = thirdPayOrderNo;
    }

    public String getPayWayName2() {
        return payWayName2;
    }

    public void setPayWayName2(String payWayName2) {
        this.payWayName2 = payWayName2;
    }

    public String getPayWayName3() {
        return payWayName3;
    }

    public void setPayWayName3(String payWayName3) {
        this.payWayName3 = payWayName3;
    }

    public String getFourthPartyChannel() {
        return fourthPartyChannel;
    }

    public void setFourthPartyChannel(String fourthPartyChannel) {
        this.fourthPartyChannel = fourthPartyChannel;
    }

    public String getFourthPartyBankName() {
        return fourthPartyBankName;
    }

    public void setFourthPartyBankName(String fourthPartyBankName) {
        this.fourthPartyBankName = fourthPartyBankName;
    }

    public String getFourthPartyNo() {
        return fourthPartyNo;
    }

    public void setFourthPartyNo(String fourthPartyNo) {
        this.fourthPartyNo = fourthPartyNo;
    }

    public Date getCallPayTime() {
        return callPayTime;
    }

    public void setCallPayTime(Date callPayTime) {
        this.callPayTime = callPayTime;
    }

    public boolean isNotUpdateIcbcOrderStatus() {
        return notUpdateIcbcOrderStatus;
    }

    public void setNotUpdateIcbcOrderStatus(boolean notUpdateIcbcOrderStatus) {
        this.notUpdateIcbcOrderStatus = notUpdateIcbcOrderStatus;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public Integer getQryTimes() {
        return qryTimes;
    }

    public void setQryTimes(Integer qryTimes) {
        this.qryTimes = qryTimes;
    }
}
