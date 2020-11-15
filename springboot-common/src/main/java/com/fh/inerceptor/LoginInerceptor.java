package com.fh.inerceptor;

import com.alibaba.fastjson.JSON;
import com.fh.common.AjaxException;
import com.fh.common.Ignore;
import com.fh.user.model.User;
import com.fh.utils.JwtUtil;
import com.fh.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author huangp
 * @create 2020-11-03 10:58
 */
public class LoginInerceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //将所有加了Ignore注解的类全部都放开拦截
        HandlerMethod  handlerMethod = (HandlerMethod)handler;
        Ignore ignore = handlerMethod.getMethodAnnotation(Ignore.class);
        if(ignore!=null){
            return true;
        }


        String token = request.getHeader("auth");

        //判断redis中是否有token信息
        boolean exist = RedisUtil.exist(token);
        if(!exist){
            throw new AjaxException();
        }

        //如果前端传来的字符串为空直接跳转登录页面
        if(StringUtils.isBlank(token)){
            throw new AjaxException();
        }


        //调用工具类中订单verify进行解析
        String verify = JwtUtil.verify(token);
        if(StringUtils.isBlank(verify)){
            //解析失败，跳转登录页面
            throw new AjaxException();
        }

        //使用JSON将json字符串转换为对象信息
        Object parse = JSON.parseObject(verify, User.class);
        //将转换出来的json信息放入session中
        request.getSession().setAttribute("user",parse);
        return true;
    }
}
