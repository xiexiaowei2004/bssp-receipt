package com.powerbridge.bssp.receipt.controller;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.common.util.BeanKit;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.edi.entity.*;
import com.powerbridge.bssp.edi.service.*;
import com.powerbridge.bssp.receipt.util.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：ReceiptParser
 * 类描述：从报文数据表查询数据，初步解析回执，将回执存放到回执数据表，并补充相应的字段
 * 创建人：danagao
 * 创建时间：2017/6/5 20:59
 *
 * @version 1.0
 */
@Component
public class ReceiptParserSimple {

    private static final Logger logger = LoggerFactory.getLogger(ReceiptParserSimple.class);
    //获取容器（上下文环境）
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
    //路由配置服务
    @Autowired
    private IEdiRoutingInfoService ediRoutingInfoService;

    @Autowired
    private IEdiBusinessConfigService ediBusinessConfigService;
    //报文数据
    private EdiMessageData ediMessageData;
    //回执数据
    private EdiRecvData ediRecvData;
    //报文日志
    private EdiMessageLog ediMessageLog;

    /**
     * 报文简单解析
     *
     * @param document
     * @throws Exception
     */
    private void simpleParseXMLFile(Document document) throws Exception {
        //根节点
        Element root = document.getRootElement();
        //回执类型
        Element bizTypeElement = null;
        String pocketId = UUIDGenerator.getUUID();
        Integer totalPocketQty = 1;
        Integer curPocketNo = 1;
        String receiptBizType;
        //判断是否有加签
        if ("Signature".equals(root.getName())) {
            //包信息
            Element pocketInfoElement = XMLUtils.getElementByXpath("//PocketInfo", document);
            pocketId = pocketInfoElement.elementText("pocket_id");
            totalPocketQty = Integer.parseInt(pocketInfoElement.elementText("total_pocket_qty"));
            curPocketNo = Integer.parseInt(pocketInfoElement.elementText("cur_pocket_no"));

            Element BussinessData = XMLUtils.getElementByXpath("//BussinessData", document);
            //子报文入库回执根节点
            List dataTypeList = BussinessData.elements();
            if (dataTypeList != null) {
                bizTypeElement = (Element) dataTypeList.get(0);//如: <EMS200>
            }
            //报文类型
            receiptBizType = bizTypeElement.getName();//如：EMS200
        } else {
            receiptBizType = root.getName();
            bizTypeElement = root;
        }
        String docType;
        String bizType;
        //特殊的回执做特殊判断操作
        if (receiptBizType.equals("EMS200") || receiptBizType.equals("SAS200") || receiptBizType.equals("INV200") || receiptBizType.equals("BWS200")) {
            String resutType = XMLUtils.getElementByXpath("//resutType", document).getText();
            docType = ReceiptKindConstant.getDocType(receiptBizType, resutType);
            bizType = ReceiptKindConstant.getBizType(receiptBizType, resutType);
        } else {
            docType = ReceiptKindConstant.getDocType(receiptBizType);
            bizType = ReceiptKindConstant.getBizType(receiptBizType);
        }
        //获取企业内部编号（预录入编号）
        String etpsPreentNo = XMLUtils.getElementByXpath("//etpsPreentNo", document).getText();

        //获取数据 确定单据编号
        Map<String, String> map = getDataFromTable(docType, bizType, etpsPreentNo);
        //重新覆盖数据，保证数据正确
        String seqNo = null;
        String areaCode = null;
        if (map != null) {
            docType = map.getOrDefault("docType", docType);
            bizType = map.getOrDefault("bizType", bizType);
            seqNo = map.getOrDefault("seqNo", seqNo);
            areaCode = map.getOrDefault("areaCode", areaCode);
        }
        //保存报文数据
        setEdiMessageData(docType, bizType, areaCode, seqNo);
        //保存回执表头数据
        setEdiRecvData(docType, bizType, areaCode, etpsPreentNo, seqNo, bizTypeElement, pocketId, totalPocketQty, curPocketNo);
    }

    /**
     * 解析xml的主流程
     *
     * @throws Exception 异常
     */
    public void parse() {
        //1.先获取到所有未处理的报文数据
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("STATUS", TransTypeEnum.N);
        entityWrapper.eq("TRANS_TYPE", TransTypeEnum.R);
        entityWrapper.eq("PASSAGEWAY", ReceiptKindConstant.PASSAGEWAY);
        List<EdiMessageData> ediMessageDataList = ediMessageDataService.selectList(entityWrapper);
        ReceiptMsg2.getInstance().publishInfo("获取回执数据完成，共" + ediMessageDataList.size() + "条");
        //2.遍历循环简单解析报文数据
        for (EdiMessageData ediMessageDataObj : ediMessageDataList) {
            ediMessageData = ediMessageDataObj;
            ReceiptMsg2.getInstance().publishInfo("正在处理：" + ediMessageData.getFileName());
            try {
                String strXmlData = new String(ediMessageData.getBigData());
                ediMessageLog = new EdiMessageLog();
                ediRecvData = new EdiRecvData();
                if (StringUtil.isNotEmpty(strXmlData)) {

                    simpleParseXMLFile(DocumentHelper.parseText(strXmlData));
                    updateEdiMessageLog();
                    //写入回执数据
                    if (ediRecvDataService.insert(ediRecvData)) {
                        ediMessageLog.setStatus(TransTypeEnum.EDI_SIMPLE_SUCCESS);
                    } else {
                        ediMessageLog.setStatus(TransTypeEnum.EDI_SIMPLE_ERROR);
                    }
                    ReceiptMsg2.getInstance().publishInfo("处理完成：" + ediMessageData.getFileName());
                }
            } catch (Exception e) {
                ediMessageLog.setProcessingLog(e.toString().getBytes());
                ediMessageLog.setStatus(TransTypeEnum.EDI_SIMPLE_ERROR);
                ediMessageData.setStatus(TransTypeEnum.ONE);
                e.printStackTrace();
                logger.error("receiptParserSimple---error", e);
                ReceiptMsg2.getInstance().publishError(e.toString());
            } finally {
                //修改报文数据
                ediMessageData.setProcessingTime(DateUtil.now());
                ediMessageDataService.updateById(ediMessageData);
                //写报文日志
                setEdiMessageLog();
                ediMessageLogService.insert(ediMessageLog);
            }


        }
    }

    /**
     * @param docType  监管场所
     * @param bizType  单据类型
     * @param areaCode 业务类型
     * @return
     * @Description 将报文保存到报文数据表
     */
    private void setEdiMessageData(final String docType, final String bizType, final String areaCode, final String seqNo) {
        ediMessageData.setSerialNo(seqNo);                                     //统一编号
        ediMessageData.setDocType(docType);                                    //单据类型
        ediMessageData.setAreaCode(areaCode);                                  //监管场所
        ediMessageData.setBizType(bizType);                                    //业务类型
        EdiRoutingInfo ediRoutingInfo = getEdiRoutingInfo(docType, bizType, areaCode);
        ediMessageData.setTransMode(ediRoutingInfo.getTransMode());            //传输方式
        ediMessageData.setStatus(TransTypeEnum.Y);                             //处理标识 Y:已处理 N:未处理
    }

    /**
     * @param docType  监管场所
     * @param bizType  单据类型
     * @param areaCode 业务类型
     * @return
     * @Description 将报文保存到回执表头表
     */
    private void setEdiRecvData(final String docType, final String bizType, final String areaCode, final String etpsPreentNo, final String seqNo,
                                Element element, String pocketId, Integer totalPocketQty, Integer curPocketNo) throws Exception {
        ediRecvData.setUid(UUIDGenerator.getUUID());                        //id
        ediRecvData.setDocId(etpsPreentNo);                                 //企业内部编号
        ediRecvData.setSeqNo(seqNo);                                        //统一编号
        ediRecvData.setDocType(docType);                                    //单据类型
        ediRecvData.setBizType(bizType);                                    //业务类型
        ediRecvData.setAreaCode(areaCode);                                  //监管场所
        ediRecvData.setBigData(element.asXML().getBytes());                 //大数据
        ediRecvData.setInputerTime(DateUtil.now());                         //录入时间
        ediRecvData.setStatus(TransTypeEnum.N);                             //处理标识 Y:已处理,N:未处理
        ediRecvData.setMessageUid(ediMessageData.getUid());                 //对应MESSAGE_DATA中的uid
        ediRecvData.setPocketId(pocketId);                                  //包的唯一标识，拆包时多个子包id相同
        ediRecvData.setTotalPocketQty(totalPocketQty);                      //包总数
        ediRecvData.setCurPocketNo(curPocketNo);                            //当前包序号
        ediRecvData.setPassageway(ReceiptKindConstant.PASSAGEWAY);                                       //通道
    }

    /**
     * @Description 将报文数据保存到报文日志对象
     */
    private void setEdiMessageLog() {
        ediMessageLog.setUid(UUIDGenerator.getUUID());
        if (ediRecvData != null) {
            ediMessageLog.setSerialNo(ediRecvData.getSeqNo());
            ediMessageLog.setBizType(ediRecvData.getBizType());
            ediMessageLog.setDocType(ediRecvData.getDocType());
            ediMessageLog.setAreaCode(ediRecvData.getAreaCode());
        }
        ediMessageLog.setMessageUid(ediMessageData.getUid());
        ediMessageLog.setFileName(ediMessageData.getFileName());
        ediMessageLog.setTransType(ediMessageData.getTransType());
        ediMessageLog.setProcessingTime(DateUtil.now());
    }

    /**
     * @Description 将报文数据更新到到存储回执的报文日志中
     */
    private void updateEdiMessageLog() {
        EdiMessageLog intoDbEdiMessageLog = new EdiMessageLog();
        intoDbEdiMessageLog.setBizType(ediRecvData.getBizType());
        intoDbEdiMessageLog.setDocType(ediRecvData.getDocType());
        intoDbEdiMessageLog.setAreaCode(ediRecvData.getAreaCode());
        intoDbEdiMessageLog.setSerialNo(ediRecvData.getSeqNo());
        intoDbEdiMessageLog.setTransType(ediMessageData.getTransType());
        //将报文数据更新到文件转入的报文日志中
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("MESSAGE_UID", ediMessageData.getUid());
        ediMessageLogService.update(intoDbEdiMessageLog, entityWrapper);

    }

    /**
     * @param docType  监管场所
     * @param bizType  单据类型
     * @param areaCode 业务类型
     * @return 路由配置表信息
     * @Description 查询路由配置表 用于获取文件传输方式
     */
    private EdiRoutingInfo getEdiRoutingInfo(final String docType, final String bizType, String areaCode) {
        EntityWrapper entityWrapper = new EntityWrapper();
        //entityWrapper.eq("AREA_CODE", areaCode);                     //监管场所
        entityWrapper.eq("DOC_TYPE", docType);                       //单据类型
        entityWrapper.eq("BIZ_TYPE", bizType);                       //业务类型
        entityWrapper.eq("status", TransTypeEnum.Y);                 //启用标识
        return ediRoutingInfoService.selectOne(entityWrapper);
    }


    /**
     * @param docType           监管场所
     * @param bizType           单据类型
     * @param etpsPreentNoValue 企业内部编号
     * @return 路由配置表信息
     * @Description 查询路由配置表 用于获取文件传输方式
     */

    private Map<String, String> getDataFromTable(String docType, String bizType, String etpsPreentNoValue) throws Exception {
        Map<String, String> dataMap = new HashMap<>();

        EntityWrapper entityWrapper = new EntityWrapper<EdiBusinessConfig>();
        entityWrapper.like("DOC_TYPE", docType, SqlLike.RIGHT);//单据类型
        entityWrapper.eq("BIZ_TYPE", bizType);//业务类型
        entityWrapper.eq("STATUS", "Y");//处理标识
        entityWrapper.orderBy("ORDER_NO");
        EdiBusinessConfig ediBusinessConfig = ediBusinessConfigService.selectOne(entityWrapper);
        String etpsPreentNo = ediBusinessConfig.getEtpsPreentNo();
        String tableService = ediBusinessConfig.getTableService();

        EntityWrapper entityWrapper2 = new EntityWrapper();
        entityWrapper2.eq(etpsPreentNo, etpsPreentNoValue);
        Object oneBean = BeanUtil.selectOne(webApplicationContext.getBean(tableService), entityWrapper2);

        if (oneBean == null) {
//            ReceiptMsg2.getInstance().publishError("没找到对应单据：DOC_TYPE=" + stringEmpty(docType) + ",BIZ_TYPE=" + stringEmpty(bizType) + "," + stringEmpty(etpsPreentNo) + "=" + stringEmpty(etpsPreentNoValue));
            throw new Exception("没找到对应单据：DOC_TYPE=" + stringEmpty(docType) + ",BIZ_TYPE=" + stringEmpty(bizType) + "," + stringEmpty(etpsPreentNo) + "=" + stringEmpty(etpsPreentNoValue));
        }

        dataMap.put("seqNo", (String) BeanKit.getProperty(oneBean, "seqNo"));
        dataMap.put("docType", (String) BeanKit.getProperty(oneBean, "docType"));
        dataMap.put("bizType", (String) BeanKit.getProperty(oneBean, "bizType"));
        dataMap.put("areaCode", (String) BeanKit.getProperty(oneBean, "areaCode"));
        dataMap.put("serviceName", tableService);


        return dataMap;
    }

    private String stringEmpty(String str) {
        if (str == null) {
            return "null";
        } else {
            return str;
        }
    }
}
