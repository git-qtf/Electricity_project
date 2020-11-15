package com.fh.product.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@TableName("t_product")
public class Product {
    private Integer   id;
    private String   name;
    private Integer brandId;
    private BigDecimal price;
    private Integer  status; //上下架
    private Integer  isHot; //是否热销
    private String filePath;
    @JsonFormat(pattern="yyyy-MM-dd ",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    private Integer stock; //库存


    public Product() {
    }


    public Product(Integer id, String name, Integer brandId, BigDecimal price, Integer status, Integer isHot, String filePath, Date createDate, Integer stock) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
        this.price = price;
        this.status = status;
        this.isHot = isHot;
        this.filePath = filePath;
        this.createDate = createDate;
        this.stock = stock;
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

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
