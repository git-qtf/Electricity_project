package com.fh.inerceptor;

import com.fh.common.AjaxException;
import com.fh.common.Idempotence;
import com.fh.common.TokenExcuption;
import com.fh.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author huangp
 * @create 2020-11-06 13:58
 */
public class IdempotenceInerceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //将加了idempotence注解的类进行拦截拦截
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Idempotence idempotence = handlerMethod.getMethodAnnotation(Idempotence.class);
        if(idempotence == null){
            return true;
        }

        String token = request.getHeader("mtoken");
        //如果前端传来的字符串为空直接跳转登录页面
        if(StringUtils.isBlank(token)){
            throw new AjaxException();
        }

        //判断token中有没有数据
        boolean exists = RedisUtil.exist(token);
        if(!exists){
            throw new TokenExcuption("每件商品只能生成一次订单");
        }

        //删除redis中的mtoken信息
        Long del = RedisUtil.del(token);
        if(del != 1){
            throw new TokenExcuption("请求重复！");
        }
        return true;
    }
}
