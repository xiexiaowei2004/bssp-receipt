package com.powerbridge.bssp.receipt.service;

import com.powerbridge.bssp.receipt.util.PostActionEnum;
import org.dom4j.Document;

import java.util.Map;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：IReceiptParser
 * 类描述：菜单控制器
 * 创建人：danagao
 * 创建时间：2017/6/7 18:13
 *
 * @version 1.0
 */
public interface IReceiptParser {
    void before();

    void setParam(Document document, String seqNo, Map<String, String> infoMap);

    PostActionEnum execute() throws Exception;

    void after();
}
