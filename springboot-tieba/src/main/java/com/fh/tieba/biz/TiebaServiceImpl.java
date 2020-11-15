package com.fh.tieba.biz;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.tieba.mapper.TiebaMapper;
import com.fh.tieba.model.Tieba;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class TiebaServiceImpl implements TiebaService {

    @Resource
    private TiebaMapper tiebaMapper;


    /**
     * 查询所有评论
     * @param userId
     * @return
     */
    @Override
    public List<Tieba> queryTieba(Integer userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userId",userId);
        return tiebaMapper.selectList(queryWrapper);
    }

    /**
     * 点赞+1
     * @param userId
     * @return
     */
    @Override
    public void addTiebaLike(Integer userId,Integer tiebaId) {
        Tieba tieba = tiebaMapper.getUserId(userId,tiebaId);

        Integer tiebaLike  = tieba.getTiebaLike()+1;
        tiebaMapper.updateTiebaLike(tiebaLike,userId,tiebaId);
        /*
          Map<Object, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("tiebaId",tiebaId);
        map.put("tieba",tieba.getTiebaLike());
         */
    }
}
