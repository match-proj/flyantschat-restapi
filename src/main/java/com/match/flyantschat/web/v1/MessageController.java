package com.match.flyantschat.web.v1;

import com.match.common.PageResult;
import com.match.common.context.UserContext;
import com.match.oim.client.MessageClient;
import com.match.oim.client.bean.MessageDTO;
import com.match.oim.client.bean.MessageUserSimpleInfoDTO;
import com.match.oim.client.bean.PublishMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zhangchao
 * @Date 2019/5/28 17:11
 * @Version v1.0
 */
@RestController
@RequestMapping("/message")
@Slf4j
public class MessageController {


    @Autowired
    private MessageClient messageClient;


    @GetMapping("/getPeopleSimpleInfoByPeopleId")
    public MessageUserSimpleInfoDTO getPeopleSimpleInfoByPeopleId(String peopleId){
        return messageClient.getPeopleSimpleInfoByPeopleId(peopleId);
    }


    @GetMapping("/getMessageUserSimpleInfo")
    public MessageUserSimpleInfoDTO getPeopleSimpleInfo(String messageUserId){
        return messageClient.getPeopleSimpleInfo(messageUserId);
    }


    @PostMapping("/publish")
    public void publishMessage(@RequestBody PublishMessageDTO publishMessageDto){
        String peopleId = UserContext.getUser().getId();
        messageClient.publishMessage(peopleId,publishMessageDto);
    }


    @GetMapping("/{conversationId}/list")
    public PageResult<MessageDTO> list(@RequestParam(required = false,name = "page",defaultValue = "1") Integer page,
                                       @RequestParam(required = false,name = "size",defaultValue = "10") Integer size,
                                       @PathVariable("conversationId") String conversationId) {
        return messageClient.list(page, size, conversationId);
    }

}
