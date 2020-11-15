package com.fh.tieba.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@TableName("t_tieba")
public class Tieba {

    private Integer tiebaId;
    private Integer userId;
    private String tiebaContent;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date releaseTime;
    private Integer tiebaLike;//评论
    private Integer tiebaComment;//点赞


    public Tieba() {
    }

    public Tieba(Integer tiebaId, Integer userId, String tiebaContent, Date releaseTime, Integer tiebaLike, Integer tiebaComment) {
        this.tiebaId = tiebaId;
        this.userId = userId;
        this.tiebaContent = tiebaContent;
        this.releaseTime = releaseTime;
        this.tiebaLike = tiebaLike;
        this.tiebaComment = tiebaComment;
    }

    public Integer getTiebaId() {
        return tiebaId;
    }

    public void setTiebaId(Integer tiebaId) {
        this.tiebaId = tiebaId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTiebaContent() {
        return tiebaContent;
    }

    public void setTiebaContent(String tiebaContent) {
        this.tiebaContent = tiebaContent;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getTiebaLike() {
        return tiebaLike;
    }


    public Integer getTiebaComment() {
        return tiebaComment;
    }

    public void setTiebaComment(Integer tiebaComment) {
        this.tiebaComment = tiebaComment;
    }
}
