package com.match.flyantschat.context.service;

import com.match.flyantschat.context.vo.ExpenseListVO;

import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/9/6 13:36
 * @Version v1.0
 */
public interface ExpenseService {
    List<ExpenseListVO> listByGroupId(String groupId, String month, int page, int size);
}
