package com.fh.utils;

import javax.servlet.http.HttpServletRequest;

//request 存放工具类
public class RequestFilterUtil {
    //创建线程
    public static ThreadLocal<HttpServletRequest> threadLocal=new ThreadLocal<>();

    //把request放入线程中
    public static void setRequest(HttpServletRequest request){
        threadLocal.set(request);
    }
    //从线程中获取requset
    public static HttpServletRequest getRequest(){
        return  threadLocal.get();
    }
    //销毁request
    public static void romove(){
        threadLocal.remove();
    }


}
