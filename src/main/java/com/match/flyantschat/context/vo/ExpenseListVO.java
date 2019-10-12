package com.match.flyantschat.context.vo;

import com.match.expenses.client.bean.ExpenseDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/9/6 13:44
 * @Version v1.0
 */
public class ExpenseListVO {

    private String date;
    private BigDecimal dayin;
    private BigDecimal dayout;
    private List<ExpenseDTO> rows;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getDayin() {
        return dayin;
    }

    public void setDayin(BigDecimal dayin) {
        this.dayin = dayin;
    }

    public BigDecimal getDayout() {
        return dayout;
    }

    public void setDayout(BigDecimal dayout) {
        this.dayout = dayout;
    }

    public List<ExpenseDTO> getRows() {
        return rows;
    }

    public void setRows(List<ExpenseDTO> rows) {
        this.rows = rows;
    }
}
