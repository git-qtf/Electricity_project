package com.fh.user.biz;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.user.mapper.UserMapper;
import com.fh.user.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    @Override
    public User queryLogin(String userName, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
            queryWrapper.eq("userName", userName);
            queryWrapper.or().eq("phone", userName);
       return userMapper.selectOne(queryWrapper);
    }

    /**
     * 注册
     *
     */
    @Override
    public void registered(User user) {
        userMapper.insert(user);
    }

    /**
     * 查询全部数据
     * @return
     */
    @Override
    public List<User> queryList() {
        return userMapper.selectList(null);
    }

    /**
     * 根据name查询数据
     * @param userName
     * @return
     */
    @Override
    public User queryUser(String userName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userName",userName);
        return userMapper.selectOne(queryWrapper);
    }
}
