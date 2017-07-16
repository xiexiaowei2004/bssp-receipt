package com.powerbridge.bssp.receipt.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.edi.entity.EdiMessageData;
import com.powerbridge.bssp.edi.entity.EdiMessageLog;
import com.powerbridge.bssp.edi.entity.EdiRecvData;
import com.powerbridge.bssp.edi.service.IEdiMessageDataService;
import com.powerbridge.bssp.edi.service.IEdiMessageLogService;
import com.powerbridge.bssp.edi.service.IEdiRecvDataService;
import com.powerbridge.bssp.edi.service.IEdiXmlMapDbService;
import com.powerbridge.bssp.receipt.service.IReceiptParser;
import com.powerbridge.bssp.receipt.util.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

/**
 * 项目名称：bssp-receipt
 * 类名称：ReceiptParserDetail
 * 类描述：回执信息处理公用类
 * 创建人：willChen
 * 创建时间：2017/6/11 19:42
 * 修改人：willChen
 * 修改时间：2017/6/11 19:42
 */
@Component
public class ReceiptParserDetail {

    private static final Logger logger = LoggerFactory.getLogger(ReceiptParserDetail.class);
    @Autowired
    private
    IEdiXmlMapDbService ediXmlMapDbService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    //回执数据服务
    @Autowired
    private IEdiRecvDataService ediRecvDataService;
    //报文数据服务
    @Autowired
    private IEdiMessageDataService ediMessageDataService;
    //报文日志服务
    @Autowired
    private IEdiMessageLogService ediMessageLogService;
    //报文日志
    private EdiMessageLog ediMessageLog;

    /**
     * 根据pocketId对未处理的回执数据进行分组
     *
     * @return
     */
    private Map getEdiRecvDataMap() {
        //获取未处理的回执数据
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("STATUS", TransTypeEnum.N);
        entityWrapper.eq("PASSAGEWAY", ReceiptKindConstant.PASSAGEWAY);
        entityWrapper.orderBy("CUR_POCKET_NO");//包序号
        List<EdiRecvData> ediRecvDataList = ediRecvDataService.selectList(entityWrapper);
        //根据pocketId对数据进行分组
        Map<String, List<EdiRecvData>> map = new HashMap();
        for (EdiRecvData ediRecvData : ediRecvDataList) {
            String pocketId = ediRecvData.getPocketId();
            if (map.containsKey(pocketId)) {
                map.get(pocketId).add(ediRecvData);
            } else {
                List<EdiRecvData> ediRecvDatas = new ArrayList<>();
                ediRecvDatas.add(ediRecvData);
                map.put(pocketId, ediRecvDatas);
            }
        }
        return map;
    }

    //回执中的检查信息，可能不存在也可能有多条
    private void parseCheckInfo(Document document) {
        List<Element> CheckInfoList = document.getRootElement().elements("CheckInfo");
        if (CheckInfoList != null) {
            StringBuilder stringBuffer = new StringBuilder();
            for (Element CheckInfo : CheckInfoList) {
                stringBuffer.append(CheckInfo.elementText("note"));
            }
            //将检查信息放到日志表
            ediMessageLog.setCheckInfo(stringBuffer.toString().getBytes());
        }
    }

    //将回执数据表中信息存入日志表
    private void setMessageLog(EdiRecvData ediRecvData) {
        //获取报文数据对象
        EdiMessageData ediMessageData = ediMessageDataService.selectById(ediRecvData.getMessageUid());
        //写入报文日志表
        ediMessageLog = new EdiMessageLog();
        ediMessageLog.setSerialNo(ediRecvData.getSeqNo());
        ediMessageLog.setAreaCode(ediRecvData.getAreaCode());
        ediMessageLog.setDocType(ediRecvData.getDocType());
        ediMessageLog.setBizType(ediRecvData.getBizType());
        ediMessageLog.setMessageUid(ediRecvData.getMessageUid());
        ediMessageLog.setFileName(ediMessageData.getFileName());
        ediMessageLog.setTransType(ediMessageData.getTransType());
    }

    private void initTableMap() {
        try {
            if (ReceiptUtil.getTableMap() == null) {
                ReceiptUtil.setTableMap(ediXmlMapDbService.getXmlMapDb());
            }
        } catch (Exception e) {
            ReceiptMsg3.getInstance().publishError(e.toString());
            e.printStackTrace();
        }
    }


    /**
     * 回执处理方法
     *
     * @throws Exception
     */
    public void parse() {
        initTableMap();

        //获取根据pocketId进行分组的数据
        Map<String, List<EdiRecvData>> map = getEdiRecvDataMap();
        ReceiptMsg3.getInstance().publishInfo("获取回执数据完成，共" + map.size() + "条");
        for (String key : map.keySet()) {
            List<EdiRecvData> ediRecvDataList = map.get(key);
            for (EdiRecvData ediRecvData : ediRecvDataList) {
                //数据定义的查找到的包总数
                if (ediRecvData.getTotalPocketQty() == ediRecvDataList.size()) {
                    try {
                        ReceiptMsg3.getInstance().publishInfo("正在处理：seqNo=" + ediRecvData.getSeqNo() + ",bizType=" + ediRecvData.getBizType() + ",docType=" + ediRecvData.getDocType());
                        //获取回执数据
                        String xmlStr = new String(ediRecvData.getBigData());
                        Document document = DocumentHelper.parseText(xmlStr);
                        //获取回执类型
                        String xmlBizType = document.getRootElement().getName();

                        //将回执数据表中信息存入日志表
                        setMessageLog(ediRecvData);
                        //回执中的检查信息，可能不存在也可能有多条
                        parseCheckInfo(document);

                        //获取报文处理对象
                        Map dataMap = new HashMap();
                        dataMap.put("bizType", ediRecvData.getBizType());
                        dataMap.put("docType", ediRecvData.getDocType());
                        //是否是同一个包中最后一份数据
                        dataMap.put("isLastData", Objects.equals(ediRecvData.getCurPocketNo(), ediRecvData.getTotalPocketQty()));
                        IReceiptParser parser = (IReceiptParser) webApplicationContext.getBean(xmlBizType);
                        parser.setParam(document, ediRecvData.getSeqNo(), dataMap);
                        //处理报文
                        PostActionEnum postActionEnum = parser.execute();
                        ediMessageLog.setStatus(TransTypeEnum.EDI_DETAIL_SUCCESS);
                        ediMessageLog.setProcessingLog(postActionEnum.getResult().getBytes());
                        ediRecvData.setStatus(TransTypeEnum.Y);
                        ReceiptMsg3.getInstance().publishInfo("处理完成：seqNo=" + ediRecvData.getSeqNo() + ",bizType=" + ediRecvData.getBizType() + ",docType=" + ediRecvData.getDocType());
                    } catch (Exception e) {
                        //设置异常信息
                        ediMessageLog.setProcessingLog(e.toString().getBytes());
                        ediMessageLog.setStatus(TransTypeEnum.EDI_DETAIL_ERROR);
                        ediRecvData.setStatus(TransTypeEnum.ONE);
                        e.printStackTrace();
                        logger.error("receiptParserDetail---error", e);
                        ReceiptMsg3.getInstance().publishError(e.toString());
                    } finally {
                        //返填回执头和报文数据更改为已执行状态

                        ediRecvDataService.updateById(ediRecvData);
                        //写报文日志
                        ediMessageLog.setUid(UUIDGenerator.getUUID());
                        ediMessageLog.setProcessingTime(DateUtil.now());
                        ediMessageLogService.insert(ediMessageLog);
                    }
                }
            }
        }
    }
}
