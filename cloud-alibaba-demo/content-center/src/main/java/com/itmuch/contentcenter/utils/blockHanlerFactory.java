package com.itmuch.contentcenter.utils;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class blockHanlerFactory {
    public static String block(String a, BlockException e){
        log.warn("流控/降级/热点拦截",e);
        return "流控/降级/热点拦截 block";
    }
}
