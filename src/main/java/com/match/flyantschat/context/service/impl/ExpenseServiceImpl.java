package com.match.flyantschat.context.service.impl;
import java.math.BigDecimal;
import com.google.common.collect.Lists;

import com.match.common.PageResult;
import com.match.common.utils.JsonUtils;
import com.match.expenses.client.ExpensesClient;
import com.match.expenses.client.bean.ExpenseDTO;
import com.match.expenses.client.bean.ExpenseType;
import com.match.flyantschat.context.service.ExpenseService;
import com.match.flyantschat.context.vo.ExpenseListVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author zhangchao
 * @Date 2019/9/6 13:36
 * @Version v1.0
 */
@Service
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {


    @Autowired
    ExpensesClient expensesClient;


    @Override
    public List<ExpenseListVO> listByGroupId(String groupId, String month, int page, int size) {

        PageResult<ExpenseDTO> pageResult = expensesClient.listByGroupId(groupId,month, page, size);
        Map<String,ExpenseListVO> listMap = new HashMap<>();
        log.info("pageResult:{}", JsonUtils.obj2json(pageResult));
        pageResult.getRows().stream().forEach(item ->{
            String date = DateFormatUtils.format(item.getConsumTime(), "yyyy-MM-dd");
            BigDecimal out = item.getExpenseType() == ExpenseType.SPENDING ? item.getAmount():BigDecimal.ZERO;
            BigDecimal in = item.getExpenseType() == ExpenseType.INCOME ? item.getAmount():BigDecimal.ZERO;
            if(listMap.containsKey(date)){
                ExpenseListVO listVO = listMap.get(date);
                listVO.getRows().add(item);
                listVO.setDayin(listVO.getDayin().add(in));
                listVO.setDayout(listVO.getDayout().add(out));
            }else{
                ExpenseListVO listVO = new ExpenseListVO();
                listVO.setDate(date);
                listVO.setDayin(in);
                listVO.setDayout(out);
                ArrayList<ExpenseDTO> arrayList = new ArrayList<>();
                arrayList.add(item);
                listVO.setRows(arrayList);
                listMap.put(date,listVO);
            }
        });
        log.info("listMap:{}", JsonUtils.obj2json(listMap));
        return listMap.values().stream().collect(Collectors.toList());
    }
}
