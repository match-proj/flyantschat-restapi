package com.match.flyantschat.context.service;

import com.match.common.context.User;

import java.util.Optional;

/**
 * @Author zhangchao
 * @Date 2019/8/1 14:30
 * @Version v1.0
 */
public interface UserService {

    Optional<String> getPeopleIdByAccessToken(String token);

    User getUserByPeopleId(String peopleId);
}
