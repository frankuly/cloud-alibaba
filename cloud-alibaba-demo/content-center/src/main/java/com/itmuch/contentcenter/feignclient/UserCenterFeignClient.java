package com.itmuch.contentcenter.feignclient;

import com.itmuch.contentcenter.domian.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//@FeignClient(name="user-center",configuration = FeignConfiguration.class)
@FeignClient(name="user-center",
        //fallback = xxxx.class
        fallbackFactory =FeignFallbackFactory.class
)
public interface UserCenterFeignClient {
   @GetMapping("/users/{id}")
    UserDTO findById(@PathVariable Integer id);
}
