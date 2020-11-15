package com.fh.inerceptor;

import com.fh.common.UserCustomAnnotations;
import com.fh.user.model.User;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huangp
 * @create 2020-11-10 10:23
 */
public class HanderUserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        UserCustomAnnotations methodAnnotation = parameter.getParameterAnnotation(UserCustomAnnotations.class);
        //带有这个注解的都进行获取session中的数据
        if(methodAnnotation != null){
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        User user = (User) request.getSession().getAttribute("user");
        return user;
    }
}
