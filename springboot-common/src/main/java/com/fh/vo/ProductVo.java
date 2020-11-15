package com.fh.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ProductVo {
    private Integer   id;
    private String   name;
    private Integer brandId;
    private String brandName;
    private BigDecimal price;
    private Integer  status; //上下架
    private Integer  isHot; //是否热销
    private String filePath;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date createDate;
    private Integer stock; //库存

    private String catrgoryName;
    private String status2;
    private String isHot2;
    private Integer catgory1;
    private Integer catgory2;
    private Integer catgory3;


    public String getStatus2() {
        return status2;
    }

    public void setStatus2(String status2) {
        this.status2 = status2;
    }

    public String getIsHot2() {
        return isHot2;
    }

    public void setIsHot2(String isHot2) {
        this.isHot2 = isHot2;
    }

    public ProductVo(Integer id, String name, Integer brandId, String brandName, BigDecimal price, Integer status, Integer isHot, String filePath, Date createDate, Integer stock, String catrgoryName, String status2, String isHot2, Integer catgory1, Integer catgory2, Integer catgory3) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
        this.brandName = brandName;
        this.price = price;
        this.status = status;
        this.isHot = isHot;
        this.filePath = filePath;
        this.createDate = createDate;
        this.stock = stock;
        this.catrgoryName = catrgoryName;
        this.status2 = status2;
        this.isHot2 = isHot2;
        this.catgory1 = catgory1;
        this.catgory2 = catgory2;
        this.catgory3 = catgory3;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getCatrgoryName() {
        return catrgoryName;
    }

    public Integer getCatgory1() {
        return catgory1;
    }

    public void setCatgory1(Integer catgory1) {
        this.catgory1 = catgory1;
    }

    public Integer getCatgory2() {
        return catgory2;
    }

    public void setCatgory2(Integer catgory2) {
        this.catgory2 = catgory2;
    }

    public Integer getCatgory3() {
        return catgory3;
    }

    public void setCatgory3(Integer catgory3) {
        this.catgory3 = catgory3;
    }

    public void setCatrgoryName(String catrgoryName) {
        this.catrgoryName = catrgoryName;
    }

    public ProductVo() {
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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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

    @Override
    public String toString() {
        return "ProductVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", isHot=" + isHot +
                ", filePath='" + filePath + '\'' +
                ", createDate=" + createDate +
                ", stock=" + stock +
                ", catrgoryName='" + catrgoryName + '\'' +
                ", status2='" + status2 + '\'' +
                ", isHot2='" + isHot2 + '\'' +
                ", catgory1=" + catgory1 +
                ", catgory2=" + catgory2 +
                ", catgory3=" + catgory3 +
                '}';
    }
}
