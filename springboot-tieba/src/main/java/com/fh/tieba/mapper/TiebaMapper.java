package com.fh.tieba.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.tieba.model.Tieba;

public interface TiebaMapper extends BaseMapper<Tieba> {



    /**
     * 根据用户id查询数据
     * @param userId
     * @return
     */
    Tieba getUserId(Integer userId, Integer tiebaId);

    /**
     * 点赞+1
     * @param
     * @return
     */
    void updateTiebaLike(Integer tiebaLike, Integer userId, Integer tiebaId);
}
