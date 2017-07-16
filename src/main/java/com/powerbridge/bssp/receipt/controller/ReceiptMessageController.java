package com.powerbridge.bssp.receipt.controller;

import com.powerbridge.bssp.receipt.util.ReceiptMsg;
import com.powerbridge.bssp.receipt.util.ReceiptMsg2;
import com.powerbridge.bssp.receipt.util.ReceiptMsg3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：ReceiptMessageController
 * 类描述：回执信息
 * 创建人：danagao
 * 创建时间：2017/7/2 11:30
 *
 * @version 1.0
 */
@Controller
public class ReceiptMessageController {
    private Logger logger = LoggerFactory.getLogger(ReceiptMessageController.class);

    /**
     * 回执调试信息
     *
     * @return
     */
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String hello(Model model) {
        model.addAttribute("msgList1", ReceiptMsg.getInstance().printStack());
        model.addAttribute("msgList2", ReceiptMsg2.getInstance().printStack());
        model.addAttribute("msgList3", ReceiptMsg3.getInstance().printStack());
        return "message";
    }

}
