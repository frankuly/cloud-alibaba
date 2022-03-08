package com.itmuch.contentcenter.service.content;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.itmuch.contentcenter.dao.content.ShareMapper;
import com.itmuch.contentcenter.domian.dto.content.ShareDTO;
import com.itmuch.contentcenter.domian.dto.user.UserDTO;
import com.itmuch.contentcenter.domian.entity.content.Share;
import com.itmuch.contentcenter.feignclient.UserCenterFeignClient;
import com.itmuch.contentcenter.utils.blockHanlerFactory;
import com.itmuch.contentcenter.utils.fallbackFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareService {
    private final ShareMapper shareMapper;
    private final UserCenterFeignClient userCenterFeignClient;

    public ShareDTO findById(Integer id){
        Share share=this.shareMapper.selectByPrimaryKey(id);
        Integer userId=share.getUserId();
        //http 声明式的Http客户端
        UserDTO userDTO= this.userCenterFeignClient.findById(userId);
        ShareDTO shareDTO=new ShareDTO();
        BeanUtils.copyProperties(share,shareDTO);
        shareDTO.setWxNickname(userDTO.getWxNickname());
        return shareDTO;
    }

    public static void main(String[] args){
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> entity=restTemplate.getForEntity(
                "http://localhost:8080/users/1",
                String.class,1);
        System.out.println(entity.getBody());
        //200 ok
        //500
        //502 bad gateway ...
        System.out.println(entity.getStatusCode());
    }
}
