package com.match.flyantschat.web.v1;

import com.match.common.context.UserContext;
import com.match.user.client.UserConfigClient;
import com.match.user.client.bean.UserConfigDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zhangchao
 * @Date 2019/6/6 11:40
 * @Version v1.0
 */
@RestController
@RequestMapping("/user/config")
@Slf4j
public class UserConfigController {

    @Autowired
    UserConfigClient configClient;

    @GetMapping
    public UserConfigDTO getPeopleConfig() {
        String peopleId = UserContext.getUser().getId();
        return configClient.getPeopleConfig(peopleId);
    }

    @PutMapping
    public void updatePeopleConfig(@RequestBody UserConfigDTO peopleConfig) {
        String peopleId = UserContext.getUser().getId();
        configClient.updatePeopleConfig(peopleId, peopleConfig);
    }

}
