package com.match.flyantschat.context.service.impl;

import com.match.common.context.User;
import com.match.flyantschat.context.service.UserService;
import com.match.user.client.UserClient;
import com.match.user.client.bean.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @Author zhangchao
 * @Date 2019/8/1 14:30
 * @Version v1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserClient userClient;


    @Override
    public Optional<String> getPeopleIdByAccessToken(String token) {
        return Optional.ofNullable(userClient.getUserIdByAccessToken(token));
    }

    @Override
    public User getUserByPeopleId(String peopleId) {
        UserInfoDTO people = userClient.getUser(peopleId);
        Assert.notNull(people,"用户不存在");
        User user = new User();
        user.setId(people.getId());
        user.setPhone(people.getPhone());
        user.setUsername(people.getNickName());
        user.setEncodedPrincipal(people.getEncodedPrincipal());
        return user;
    }
}
