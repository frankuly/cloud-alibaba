package com.itmuch.contentcenter;


import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.nacos.shaded.io.opencensus.tags.unsafe.ContextUtils;
import com.itmuch.contentcenter.dao.content.ShareMapper;
import com.itmuch.contentcenter.domian.entity.content.Share;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {

    private final ShareMapper shareMapper;
    private final DiscoveryClient discoverClient;

    @GetMapping("/test")
    public  List<Share> testInsert(){
        Share share=new Share();
        share.setCreateTime(new Date());
        share.setUpdateTime(new Date());
        share.setTitle("xxx");
        share.setCover("xxx");
        share.setAuthor("wufan");
        share.setBuyCount(1);
        this.shareMapper.insertSelective(share);
        List<Share> shares=this.shareMapper.selectAll();
        return shares;
    }

    /**
     * 测试：服务发现，证明内容中心总能找到用户中心
     * @return 用户中心所有实例的地址信息
     */
    @GetMapping("test2")
    public List<ServiceInstance> setDiscoveryClient(){
        //当前服务发现组件中有哪些微服务
        //this.discoverClient.getServices();
        //查询application user-center 微服务
        //查询指定服务的所有实例的信息
        //consul/eureka/zookeeper
        //getServices
       return this.discoverClient.getInstances("user-center");
    }


    @GetMapping("test-hot")
    @SentinelResource("hot")
    public String testHost(@RequestParam(required = false) String a,@RequestParam(required = false) String b)
    {
        return  a+" "+b;
    }

    @GetMapping("/test-sentinel-api")
    @SentinelResource(value="/test-sentinel-api",
            blockHandler = "block",
            //blockHandlerClass ="",
            fallback = "fallback"
            //fallbackClass = =""
    )
    public  String  testSentinelApi(@RequestParam(required = false) String a) throws IllegalAccessException {
        if(StringUtils.isBlank(a)){
            throw new IllegalAccessException("a不能为空");
        }
        return a;
    }

    public String block(String a,BlockException e){
        log.warn("限流或者降级",e);
        return  "限流或者降级 block";
    }

    public String fallback(String a,Throwable e){
        //可以处理Throwable
        log.warn("降级异常",e);
        return  "降级了 fallback";
    }






}
