package com.match.flyantschat.web.v1;

import com.match.common.PageResult;
import com.match.common.annotation.Anonymous;
import com.match.common.context.UserContext;
import com.match.user.client.UserClient;
import com.match.user.client.bean.UserInfoDTO;
import com.match.user.client.bean.SettingPasswordDTO;
import com.match.user.client.bean.SimpleUserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author zhangchao
 * @Date 2019/7/31 18:04
 * @Version v1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserClient userClient;


    @Anonymous
    @GetMapping("/hello")
    public String hello(){
        return userClient.hello();
    };

    @GetMapping("/info")
    public UserInfoDTO info(){
        String userId = UserContext.getUser().getId();
        return userClient.info(userId);
    };

    @PutMapping("/setPassword")
    public void setPassword(@RequestBody @Valid SettingPasswordDTO settingPassword){
        String userId = UserContext.getUser().getId();
        userClient.setPassword(userId,settingPassword);
    };

    @PutMapping("/updateUserInfo")
    public void updateUserInfo(@RequestBody UserInfoDTO userInfoDto){
        String userId = UserContext.getUser().getId();
        userClient.updateUserInfo(userId,userInfoDto);
    };

    @PutMapping("/editUserIntroduction")
    public void editUserIntroduction(String introduction){
        String userId = UserContext.getUser().getId();
        userClient.editUserIntroduction(userId,introduction);
    };

    @GetMapping("/assistUser")
    public void assistUser(String assistUserId){
        String userId = UserContext.getUser().getId();
        userClient.assistUser(userId,assistUserId);
    };

    @GetMapping("/{userId}/info")
    public UserInfoDTO getUser(@PathVariable("userId") String userId){
        return userClient.getUser(userId);
    };

    @GetMapping("/list/search")
    public PageResult<SimpleUserInfoDTO> listSearch(@RequestParam(required = false,name = "page",defaultValue = "1") Integer page,
                                                    @RequestParam(required = false,name = "size",defaultValue = "10") Integer size,
                                                    @RequestParam(required = false,name = "word") String word){
        String userId = UserContext.getUser().getId();
        return userClient.listSearch(userId,page,size,word);
    };

}
