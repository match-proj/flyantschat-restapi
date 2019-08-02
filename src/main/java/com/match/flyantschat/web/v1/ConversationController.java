package com.match.flyantschat.web.v1;

import com.match.common.ResponseData;
import com.match.common.context.UserContext;
import com.match.oim.client.ConversationClient;
import com.match.oim.client.bean.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * App会话
 * @Author zhangchao
 * @Date 2019/5/28 11:16
 * @Version v1.0
 */
@RestController
@RequestMapping
@Slf4j
public class ConversationController {


    @Autowired
    ConversationClient conversationClient;


    @PostMapping("/createSingleConversation")
    public ResponseData createSingleConversation(@Valid @RequestBody CreateConversationDTO createConversationDTO){
        String peopleId = UserContext.getUser().getId();
        return conversationClient.createSingleConversation(peopleId, createConversationDTO);
    }

    @PostMapping("/createGroupConversation")
    public ResponseData createGroupConversation(@Valid @RequestBody CreateGroupConversationDTO createConversationDTO){
        String peopleId = UserContext.getUser().getId();
        return conversationClient.createGroupConversation(peopleId,createConversationDTO);
    }


    @PostMapping("/editConversation/{conversationId}")
    public void editConversation(@PathVariable("conversationId") String conversationId , @RequestBody EditConversationDTO editConversationDTO){
        String peopleId = UserContext.getUser().getId();
        conversationClient.editConversation(peopleId,conversationId ,editConversationDTO);
    }

    @PostMapping("/list")
    public List<ConversationListDTO> list(){
        String peopleId = UserContext.getUser().getId();
        return conversationClient.list(peopleId);
    }

    @GetMapping("/getConversation")
    public ConversationDTO getConversation(@RequestParam("conversationId") String conversationId){
        return conversationClient.getConversation(conversationId);
    }

}
