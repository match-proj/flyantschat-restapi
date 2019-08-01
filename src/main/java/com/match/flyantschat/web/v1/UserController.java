package com.match.flyantschat.web.v1;

import com.match.common.PageResult;
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


    @GetMapping("/info")
    public UserInfoDTO info(){
        String userId = UserContext.getUser().getId();
        return userClient.info(userId);
    };

    @PutMapping("/setPassword")
    public void setPassword(@RequestBody @Valid SettingPasswordDTO settingPassword){
        userClient.setPassword(settingPassword);
    };

    @PutMapping("/updateUserInfo")
    public void updateUserInfo(@RequestBody UserInfoDTO userInfoDto){
        userClient.updateUserInfo(userInfoDto);
    };

    @PutMapping("/editUserIntroduction")
    public void editUserIntroduction(String introduction){
        userClient.editUserIntroduction(introduction);
    };

    @GetMapping("/assistUser")
    public void assistUser(String assistUserId){
        userClient.assistUser(assistUserId);
    };

    @GetMapping("/{userId}/info")
    public UserInfoDTO getUser(@PathVariable("userId") String userId){
        return userClient.getUser(userId);
    };

    @GetMapping("/list/search")
    public PageResult<SimpleUserInfoDTO> listSearch(@RequestParam(required = false,name = "page",defaultValue = "1") Integer page,
                                                    @RequestParam(required = false,name = "size",defaultValue = "10") Integer size,
                                                    @RequestParam(required = false,name = "word") String word){
        return userClient.listSearch(page,size,word);
    };

}
