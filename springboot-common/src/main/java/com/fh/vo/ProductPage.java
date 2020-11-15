package com.fh.vo;

/**
 * @author huangp
 * @create 2020-10-29 14:25
 */
public class ProductPage {

    private Integer pageSize;
    private Integer pageLimit;
    private String  name;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(Integer pageLimit) {
        this.pageLimit = pageLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
