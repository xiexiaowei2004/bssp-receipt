package com.powerbridge.bssp.receipt.util;

import java.util.Queue;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：ReceiptMsg
 * 类描述：菜单控制器
 * 创建人：danagao
 * 创建时间：2017/6/18 20:18
 *
 * @version 1.0
 */
public class ReceiptMsg extends AbstractReceiptMsg {
    private static final ReceiptMsg receiptMsg = new ReceiptMsg();

    public static ReceiptMsg getInstance() {
        return receiptMsg;
    }

    public Queue<String> getQueue() {
        return queue;
    }

}
