package com.fh.cart.model;

import java.math.BigDecimal;

/**
 * @author huangp
 * @create 2020-11-03 22:05
 */
public class Cart {

    private Integer userId;

    private Integer id;

    private String name;

    private BigDecimal price;

    private int count;

    private String filePath;

    private Integer stockStatus;//库存状态


    public Cart() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(Integer stockStatus) {
        this.stockStatus = stockStatus;
    }

    public Cart(Integer userId, Integer id, String name, BigDecimal price, int count, String filePath, Integer stockStatus) {
        this.userId = userId;
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.filePath = filePath;
        this.stockStatus = stockStatus;
    }
}
