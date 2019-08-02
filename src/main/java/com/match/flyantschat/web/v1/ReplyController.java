package com.match.flyantschat.web.v1;

import com.match.common.PageResult;
import com.match.common.annotation.Anonymous;
import com.match.reply.client.ReplyClient;
import com.match.reply.client.bean.ReplyPublishDto;
import com.match.reply.client.bean.ReplySimpleDto;
import com.match.reply.client.bean.ReplyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author zhangchao
 * @Date 2019/8/2 10:45
 * @Version v1.0
 */
@RestController
@RequestMapping("/reply")
public class ReplyController {


    @Autowired
    ReplyClient replyClient;

    @PostMapping("/publish")
    public void publish(@Valid @RequestBody ReplyPublishDto publishDto){
        replyClient.publish(publishDto);
    }

    @Anonymous
    @GetMapping("/list")
    public PageResult<ReplySimpleDto> list(@RequestParam(required = false,name = "page",defaultValue = "1") Integer page,
                                           @RequestParam(required = false,name = "size",defaultValue = "10") Integer size,
                                           @RequestParam(name = "replyType" ) ReplyType replyType,
                                           @RequestParam(name = "resourceId" ) String resourceId
    ) {
        return replyClient.list(page, size, replyType,resourceId);
    }
}
