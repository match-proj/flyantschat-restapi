package com.match.flyantschat.web.v1;

import com.match.common.PageResult;
import com.match.common.context.UserContext;
import com.match.oim.client.DynamicClient;
import com.match.oim.client.bean.DynamicAddDTO;
import com.match.oim.client.bean.DynamicDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jagua on 2019/5/27.
 */
@RestController
@RequestMapping
@Slf4j
public class DynamicController {


    @Autowired
    private DynamicClient dynamicService;


    @PostMapping("/publish")
    public void publishDynamic(@RequestBody DynamicAddDTO dynamic){
        String peopleId = UserContext.getUser().getId();
        dynamicService.publishDynamic(peopleId,dynamic);
    }


    @GetMapping("/list/self")
    public PageResult<DynamicDTO> listSelf(@RequestParam(required = false,name = "page",defaultValue = "1") Integer page,
                                           @RequestParam(required = false,name = "size",defaultValue = "10") Integer size) {
        String peopleId = UserContext.getUser().getId();
        return dynamicService.listSelf(peopleId,page, size );
    }


    @GetMapping("/list/friend")
    public PageResult<DynamicDTO> listFriend(@RequestParam(required = false,name = "page",defaultValue = "1") Integer page,
                                       @RequestParam(required = false,name = "size",defaultValue = "10") Integer size) {
        String peopleId = UserContext.getUser().getId();
        return dynamicService.listFriend(peopleId,page, size);
    }

}
