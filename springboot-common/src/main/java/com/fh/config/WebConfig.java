package com.fh.config;

import com.fh.inerceptor.HanderUserResolver;
import com.fh.inerceptor.IdempotenceInerceptor;
import com.fh.inerceptor.LoginInerceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author huangp
 * @create 2020-11-03 11:01
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Bean
    public LoginInerceptor loginInerceptor() {
        return new LoginInerceptor();
    }

    @Bean
    public IdempotenceInerceptor idempotenceInerceptor() {
        return new IdempotenceInerceptor();
    }

    @Bean
    public HanderUserResolver handerUserResolver() {
        return new HanderUserResolver();
    }

    /**
     * 拦截项目中所有请求
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInerceptor()).addPathPatterns("/**").excludePathPatterns("/rest/login.do/info")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
        registry.addInterceptor(idempotenceInerceptor()).excludePathPatterns("/rest/login.do/info")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");

    }


    /**
     * 自定义参数解析器
     *
     * @param argumentResolvers
     */
    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(handerUserResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

    /**
     * 解决swagger-ui.html 404无法访问的问题
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //swagger增加url映射
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }



}