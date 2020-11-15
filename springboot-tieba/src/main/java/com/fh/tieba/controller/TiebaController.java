package com.fh.tieba.controller;


import com.fh.common.ServerResponse;
import com.fh.common.UserCustomAnnotations;
import com.fh.tieba.biz.TiebaService;
import com.fh.tieba.model.Tieba;
import com.fh.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("tieba")
public class TiebaController {

    @Autowired
    private TiebaService tiebaService;

    /**
     * 查询所有评论
     * @param user
     * @return
     */
    @RequestMapping("queryTieba")
    public ServerResponse queryTieba(@UserCustomAnnotations User user){
        try {
            List<Tieba> tiebaList = tiebaService.queryTieba(user.getUserId());
            return ServerResponse.success(tiebaList);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }
    }

    /**
     * 点赞+1
     * @param user
     * @return
     */
    @RequestMapping("addTiebaLike")
    public ServerResponse addTiebaLike(@UserCustomAnnotations User user, Integer tiebaId){
        try {
            tiebaService.addTiebaLike(user.getUserId(),tiebaId);
            return ServerResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }
    }
}
