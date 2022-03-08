package com.itmuch.contentcenter.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class fallbackFactory {
    public String fallback(String a,Throwable e){
        //可以处理Throwable
        log.warn("全局异常类",e);
        return  "全局异常类 fallback";
    }

}
