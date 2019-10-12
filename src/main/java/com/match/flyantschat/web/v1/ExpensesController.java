package com.match.flyantschat.web.v1;

import com.match.common.context.UserContext;
import com.match.expenses.client.ExpensesClient;
import com.match.expenses.client.bean.ExpenseDTO;
import com.match.expenses.client.bean.GroupMemberDTO;
import com.match.expenses.client.bean.SimpleGroupDTO;
import com.match.flyantschat.context.service.ExpenseService;
import com.match.flyantschat.context.vo.ExpenseListVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/9/2 17:04
 * @Version v1.0
 */
@RestController
@RequestMapping("/expenses")
@Slf4j
@ApiModel("记账本")
public class ExpensesController {

    @Autowired
    ExpensesClient expensesService;


    @Autowired
    ExpenseService expenseService;

    @ApiOperation(value = "获取流水")
    @GetMapping("/expense/{groupId}/{month}")
    public List<ExpenseListVO> listByGroupId(@PathVariable("groupId") String groupId,
                                             @PathVariable("month") String month,
                                             @RequestParam("page") int page,
                                             @RequestParam("size") int size) {
        return expenseService.listByGroupId(groupId,month,page,size);
    }

    @ApiOperation(value = "添加流水")
    @PostMapping("/expense")
    public void addExpense( @RequestBody ExpenseDTO expenseDTO) {
        String userId = UserContext.getUser().getId();
        expensesService.addExpense(userId,expenseDTO);
    }

    @ApiOperation(value = "创建小组")
    @PostMapping("/group")
    public void createGroup( @RequestParam("name") String name) {
        String userId = UserContext.getUser().getId();
        expensesService.createGroup(userId,name);
    }

    @ApiOperation(value = "加入小组")
    @PostMapping("/group/{groupId}/input")
    public void addGroupMember( @RequestParam("groupId") String groupId) {
        String userId = UserContext.getUser().getId();
        expensesService.addGroupMember(userId,groupId);
    }

    @ApiOperation(value = "更新小组名称")
    @PutMapping("/group/{groupId}")
    public void updateGroupName(
                                @PathVariable("groupId") String groupId,
                                @RequestParam("name") String name,
                                @RequestParam("remark") String remark) {
        String userId = UserContext.getUser().getId();
        expensesService.updateGroupName(userId,groupId,name,remark);
    }

    @ApiOperation(value = "设置默认小组")
    @PutMapping("/group/{groupId}/default")
    public void setDefaultGroup( @PathVariable("groupId") String groupId) {
        String userId = UserContext.getUser().getId();
        expensesService.setDefaultGroup(userId,groupId);
    }

    @ApiOperation(value = "添加金额向小组里面 - 只有小组所有者可以添加")
    @GetMapping("/group/{groupId}/add")
    public void payCostByGroupId(@PathVariable("groupId") String groupId,
                                 @RequestParam("amount") BigDecimal amount) {
        String userId = UserContext.getUser().getId();
        expensesService.payCostByGroupId(userId,groupId,amount);
    }

    @ApiOperation(value = "获取用户默认分组")
    @GetMapping("/group/default")
    public SimpleGroupDTO getDefaultGroup() {
        String userId = UserContext.getUser().getId();
        return expensesService.getDefaultGroup(userId);
    }


    @ApiOperation(value = "获取分组列表")
    @GetMapping("/group")
    public List<SimpleGroupDTO> getGroupList(){
        String userId = UserContext.getUser().getId();
        return expensesService.getGroupList(userId);
    }


    @ApiOperation(value = "根据ID获取小组")
    @GetMapping("/group/{groupId}")
    public SimpleGroupDTO getGroup(@PathVariable("groupId") String groupId){
        return expensesService.getGroup(groupId);
    };

    @ApiOperation(value = "获取小组成员")
    @GetMapping("/group/{groupId}/member")
    public List<GroupMemberDTO> getGroupMember(@PathVariable("groupId") String groupId ){
        return expensesService.getGroupMember(groupId);
    }
}
