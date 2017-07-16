package com.powerbridge.bssp.receipt.service.impl;


import com.powerbridge.bssp.inv.service.IInvBscService;
import com.powerbridge.bssp.receipt.util.PostActionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：INV200ReceiptParser
 * 类描述：子报文入库回执
 * 创建人：willChen
 * 创建时间：2017/6/27 16:59
 *
 * @version 1.0
 */
@Component("INV200")
public class INV200ReceiptParser extends AbstractReceiptParser {


    @Autowired
    private IInvBscService invBscService;


    /**
     * 清单审批回执<INV201>
     *
     * @throws Exception
     */
    @Override
    public PostActionEnum postAction() throws Exception {
        //表头service
        bscService = invBscService;
        //判断优先级
        if (compareToManageResult()) {
            return PostActionEnum.SUCCESS_1;
        } else {
            return PostActionEnum.SUCCESS_2;
        }
    }

}



