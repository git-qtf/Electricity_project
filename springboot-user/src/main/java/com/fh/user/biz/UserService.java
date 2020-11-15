package com.fh.user.biz;

import com.fh.user.model.User;

import java.util.List;


public interface UserService {

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    User queryLogin(String userName, String password);

    /**
     * 注册
     *
     */
    void registered(User user);

    /**
     * 查询全部数据
     * @return
     */
    List<User> queryList();

    /**
     * 根据name查询数据
     * @param userName
     * @return
     */
    User queryUser(String userName);
}