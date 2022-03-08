package com.itmuch.contentcenter.feignclient;

import com.itmuch.contentcenter.domian.dto.user.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class FeignFallback implements  UserCenterFeignClient {
    @Override
    public UserDTO findById(Integer id){
        UserDTO dto=new UserDTO();
        dto.setWxNickname("默认用户");
        return dto;
    }
}
