package com.itmuch.contentcenter.controller.content;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.itmuch.contentcenter.domian.dto.content.ShareDTO;
import com.itmuch.contentcenter.service.content.ShareService;
import com.itmuch.contentcenter.utils.blockHanlerFactory;
import com.itmuch.contentcenter.utils.fallbackFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shares")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareController {
    private final ShareService shareService;

    @GetMapping("{id}")
//    @SentinelResource(
//            value = "/shares/findById",
//            blockHandlerClass = blockHanlerFactory.class,
//            blockHandler = "block",
//            fallbackClass = fallbackFactory.class,
//            fallback = "fallback"
//    )
    public ShareDTO findById(@PathVariable Integer id) {
        return this.shareService.findById(id);
    }
}
