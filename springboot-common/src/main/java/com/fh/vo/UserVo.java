package com.fh.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserVo {
    private Integer id;
    private String userName;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    private String password;
    private String areaName;
    private Integer area1;
    private Integer area2;
    private Integer area3;

    public UserVo(Integer id, String userName, Date createDate, String password, String areaName, Integer area1, Integer area2, Integer area3) {
        this.id = id;
        this.userName = userName;
        this.createDate = createDate;
        this.password = password;
        this.areaName = areaName;
        this.area1 = area1;
        this.area2 = area2;
        this.area3 = area3;
    }

    public UserVo() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }



    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getArea1() {
        return area1;
    }

    public void setArea1(Integer area1) {
        this.area1 = area1;
    }

    public Integer getArea2() {
        return area2;
    }

    public void setArea2(Integer area2) {
        this.area2 = area2;
    }

    public Integer getArea3() {
        return area3;
    }

    public void setArea3(Integer area3) {
        this.area3 = area3;
    }
}
