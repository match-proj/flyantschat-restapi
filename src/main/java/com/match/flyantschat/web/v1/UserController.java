package com.match.flyantschat.web.v1;

import com.match.user.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangchao
 * @Date 2019/7/31 18:04
 * @Version v1.0
 */
@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserClient userClient;

    @GetMapping("/hello")
    public String hello(){
        return userClient.findNameByUserId("1212");
    }

}
