package com.fh.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServerExcuptiion {

    @ExceptionHandler(AjaxException.class)
    public ServerResponse handleAjaxExcuption(Exception e){
        return ServerResponse.login_error();
    }

    @ExceptionHandler(TokenExcuption.class)
    public ServerResponse handleTokenExcuption(TokenExcuption e){
        return ServerResponse.error("mtoken不存在，或已经失效");
    }

    @ExceptionHandler(Exception.class)
    public ServerResponse handleExcuption(Exception e){
        e.printStackTrace();
        return ServerResponse.error(e.getMessage());
    }
}
