package com.fh.user.controller;


import com.alibaba.fastjson.JSON;
import com.fh.common.Ignore;
import com.fh.common.ServerResponse;
import com.fh.common.UserCustomAnnotations;
import com.fh.user.biz.UserService;
import com.fh.user.model.User;
import com.fh.utils.JwtUtil;
import com.fh.utils.RedisUtil;
import com.fh.utils.SmsUtil;
import com.fh.utils.SystemConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    @Ignore
    @RequestMapping("login")
    public ServerResponse login(String userName, String password){

        if(StringUtils.isBlank(userName)){
            return ServerResponse.error(SystemConstant.USER_IS_NULL);
        }
        if(StringUtils.isBlank(password)){
            return ServerResponse.error(SystemConstant.PASSWORD_IS_NULL);
        }

        User user = userService.queryLogin(userName, password);

        if(!user.getPassword().equals(password)){
            return ServerResponse.error();
        }

        //将user转为json格式的字符串
        String jsonString = JSON.toJSONString(user);
        //使用工具类将字符串进行加密
        String token = JwtUtil.sign(jsonString);

        //将加密过后的字符串放入redis中
        RedisUtil.setEx(token,token,30*60);

        if(user == null){
            return ServerResponse.error(SystemConstant.USER_IS_EXIST);
        }
        //返回加密后的字符串
        return ServerResponse.success(token);
    }


    /**
     * 注册
     */
    @Ignore
    @RequestMapping("registered")
    public ServerResponse registered(User user){
        try {
            //判断用户输入的验证码是否正确
            if(RedisUtil.get(user.getPhone()).equals(user.getCode())){

                User userOld =  userService.queryUser(user.getUserName());
               //判断用户两次输入的密码是否一致
               if (!user.getPassword().equals(user.getPassword2())){//不一致直接返回200
                   return ServerResponse.error(SystemConstant.PASSWORD_IS_ERROR);
               }

               //判断用户是否存在
                if(userOld != null){//存在直接返回200
                    return ServerResponse.error(SystemConstant.USERNAME_IS_ERROR);
                }
                user.setCreateDate(new Date());
                userService.registered(user);
                return ServerResponse.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }
        return ServerResponse.error();
    }


    /**
     * 发送验证码
     */
    @Ignore
    @RequestMapping("sendCode")
    public ServerResponse sendCode(String phone){
        try {
            //生成验证码
            int code = (int)(Math.random()*9999)+1000;
            //将验证码放入redis中
            RedisUtil.set(phone,code+"");
            //将验证码发送给用户
            SmsUtil.getCode(phone, String.valueOf(code));
            return ServerResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }

    }


    /**
     * 判断用户是否登录
     * @param user
     * @return
     */
    @RequestMapping("isLogin")
    public ServerResponse isLogin(@UserCustomAnnotations User user){
        try {
            if(user == null){
                return ServerResponse.error();
            }

            return ServerResponse.success(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }
    }

    /**
     * 注销用户
     */
    @RequestMapping("logOff")
    public ServerResponse logOff(HttpServletRequest request){
        try {
            String token = request.getHeader("auth");

            //将加密过后的字符串放入redis中
            RedisUtil.del(token);

            request.getSession().removeAttribute("user");
            return ServerResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }
    }


}
