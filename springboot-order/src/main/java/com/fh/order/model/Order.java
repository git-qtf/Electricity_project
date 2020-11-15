package com.fh.order.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author huangp
 * @create 2020-11-05 19:11
 */
@TableName("t_order")
public class Order {

    @TableId(type = IdType.INPUT)
    private String id;//订单表id

    private Integer userId;//用户id

    private Integer addressId;//地址id

    private Integer payType;//支付的方式

    private BigDecimal totalPrice;//总的价格

    private int totalCount;//总数量

    private Date createDate;//创建日期

    private int status;//订单状态 1.未支付 2.已支付 3.完成订单

    @TableField(exist = false)
    private List<OrderDetail> orderDetailLsit;


    public Order() {
    }

    public Order(String id, Integer userId, Integer addressId, Integer payType, BigDecimal totalPrice, int totalCount, Date createDate, int status, List<OrderDetail> orderDetailLsit) {
        this.id = id;
        this.userId = userId;
        this.addressId = addressId;
        this.payType = payType;
        this.totalPrice = totalPrice;
        this.totalCount = totalCount;
        this.createDate = createDate;
        this.status = status;
        this.orderDetailLsit = orderDetailLsit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<OrderDetail> getOrderDetailLsit() {
        return orderDetailLsit;
    }

    public void setOrderDetailLsit(List<OrderDetail> orderDetailLsit) {
        this.orderDetailLsit = orderDetailLsit;
    }
}
