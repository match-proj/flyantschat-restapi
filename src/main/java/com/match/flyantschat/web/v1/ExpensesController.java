package com.match.flyantschat.web.v1;

import com.match.common.PageResult;
import com.match.common.context.UserContext;
import com.match.expenses.client.ExpensesClient;
import com.match.expenses.client.bean.ExpenseDTO;
import com.match.expenses.client.bean.SimpleGroupDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @Author zhangchao
 * @Date 2019/9/2 17:04
 * @Version v1.0
 */
@RestController
@RequestMapping("/expenses")
@Slf4j
public class ExpensesController {

    @Autowired
    ExpensesClient expensesService;



    @GetMapping("/listByGroupId")
    public PageResult<ExpenseDTO> listByGroupId(@RequestParam("groupId") String groupId,
                                                @RequestParam("page") int page,
                                                @RequestParam("size") int size) {
        return expensesService.listByGroupId(groupId,page,size);
    }

    @PostMapping("/addExpense")
    public void addExpense( @RequestBody ExpenseDTO expenseDTO) {
        String userId = UserContext.getUser().getId();
        expensesService.addExpense(userId,expenseDTO);
    }

    @PostMapping("/addGroupMember")
    public void addGroupMember( @RequestParam("groupId") String groupId) {
        String userId = UserContext.getUser().getId();
        expensesService.addGroupMember(userId,groupId);
    }

    @PutMapping("/updateGroupName")
    public void updateGroupName(
                                @RequestParam("groupId") String groupId,
                                @RequestParam("name") String name,
                                @RequestParam("remark") String remark) {
        String userId = UserContext.getUser().getId();
        expensesService.updateGroupName(userId,groupId,name,remark);
    }

    @PutMapping("/setDefaultGroup")
    public void setDefaultGroup( @RequestParam("groupId") String groupId) {
        String userId = UserContext.getUser().getId();
        expensesService.setDefaultGroup(userId,groupId);
    }

    @GetMapping("/payCostByGroupId")
    public void payCostByGroupId(@RequestParam("groupId") String groupId,
                                 @RequestParam("amount") BigDecimal amount) {
        String userId = UserContext.getUser().getId();
        expensesService.payCostByGroupId(userId,groupId,amount);
    }

    @GetMapping("/getDefaultGroup")
    public SimpleGroupDTO getDefaultGroup() {
        String userId = UserContext.getUser().getId();
        return expensesService.getDefaultGroup(userId);
    }

}
