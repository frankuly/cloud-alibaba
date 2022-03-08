package com.itmuch.contentcenter.feignclient;

import com.itmuch.contentcenter.domian.dto.user.UserDTO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FeignFallbackFactory implements FallbackFactory<UserCenterFeignClient> {
    @Override
    public UserCenterFeignClient create(Throwable throwable) {
        return new UserCenterFeignClient() {
            @Override
            public UserDTO findById(Integer id) {
                log.warn("远程调用被限流/降级了");
                UserDTO dto=new UserDTO();
                dto.setWxNickname("默认用户");
                return dto;
            }
        };
    }
}
