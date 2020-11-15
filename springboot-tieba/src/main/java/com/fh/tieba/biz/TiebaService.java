package com.fh.tieba.biz;

import com.fh.tieba.model.Tieba;

import java.util.List;

public interface TiebaService {

    /**
     * 查询所有评论
     * @param userId
     * @return
     */
    List<Tieba> queryTieba(Integer userId);

    /**
     * 点赞+1
     * @param userId
     * @return
     */
    void addTiebaLike(Integer userId, Integer tiebaId);
}