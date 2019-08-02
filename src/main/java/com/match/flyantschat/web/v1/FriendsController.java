package com.match.flyantschat.web.v1;

import com.match.common.context.UserContext;
import com.match.oim.client.FriendsClient;
import com.match.oim.client.bean.FriendsApplyRecordDTO;
import com.match.oim.client.bean.FriendsApplyRecordStatus;
import com.match.oim.client.bean.MessageUserSimpleInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/6/13 11:42
 * @Version v1.0
 */
@RestController
@RequestMapping("/friends")
@Slf4j
public class FriendsController {


    @Autowired
    private FriendsClient friendsService;


    @PostMapping("/applyFriends")
    public void applyFriends(String messageUserId){
        String peopleId = UserContext.getUser().getId();
        friendsService.applyFriends(peopleId,messageUserId);
    }

    @PostMapping("/handlerFriendsApply")
    public void handlerFriendsApply(String id, FriendsApplyRecordStatus status){
        String peopleId = UserContext.getUser().getId();
        friendsService.handlerFriendsApply(peopleId,id,status);
    }

    @GetMapping("/getFriendsApplyList")
    public List<FriendsApplyRecordDTO> getFriendsApplyList(){
        String peopleId = UserContext.getUser().getId();
        return friendsService.getFriendsApplyList(peopleId);
    }

    @GetMapping("/getFriendsList")
    public List<MessageUserSimpleInfoDTO> getFriendsList(){
        String peopleId = UserContext.getUser().getId();
        return friendsService.getFriendsList(peopleId);
    }

}
