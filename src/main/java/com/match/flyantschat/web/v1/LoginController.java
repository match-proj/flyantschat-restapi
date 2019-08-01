package com.match.flyantschat.web.v1;

import com.match.common.ResponseData;
import com.match.common.annotation.Anonymous;
import com.match.user.client.LoginClient;
import com.match.user.client.bean.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author zhangchao
 * @Date 2019/8/1 18:04
 * @Version v1.0
 */
@RestController
@RequestMapping
public class LoginController {

    @Autowired
    LoginClient loginClient;

    @Anonymous
    @PostMapping("/login")
    public ResponseData<Object> login(@Valid @RequestBody LoginDTO loginReq){
        return loginClient.login(loginReq);
    };

    @PostMapping("/logout")
    public ResponseData<Object> logout(){
        return loginClient.logout();
    };
}
