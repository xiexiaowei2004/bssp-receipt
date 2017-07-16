package com.powerbridge.bssp.receipt.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.edi.entity.EdiMessageLog;
import com.powerbridge.bssp.edi.entity.EdiRoutingInfo;
import com.powerbridge.bssp.edi.service.IEdiMessageLogService;
import com.powerbridge.bssp.edi.service.IEdiRoutingInfoService;
import com.powerbridge.bssp.receipt.util.ReceiptKindConstant;
import com.powerbridge.bssp.receipt.util.ReceiptMsg;
import com.powerbridge.bssp.receipt.util.TransTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：ReciptParserRout
 * 类描述：路由控制器
 * 创建人：danagao
 * 创建时间：2017/6/22 14:14
 *
 * @version 1.0
 */
@Component
public class ReciptParserRout {

    //路由配置服务
    @Autowired
    private IEdiRoutingInfoService ediRoutingInfoService;
    //报文日志服务
    @Autowired
    private IEdiMessageLogService ediMessageLogService;
    //转入数据库
    @Autowired
    private ReceiptParserIntoDb receiptParserIntoDb;


    /**
     * 初始化
     */
    public ReciptParserRout() {
        // 通过容器 获取service
    }

    public void execute() {
        //场地代码
        String areaCode = ReceiptKindConstant.RECEIPTAREACODE;
        //传输类型
        int passageway = ReceiptKindConstant.PASSAGEWAY;
        //
        EdiRoutingInfo ediRoutingInfo = getEdiRoutingInfo(areaCode, passageway);
        if (ediRoutingInfo != null) {
            String reclUrl = ediRoutingInfo.getReclUrl();
            String transMode = ediRoutingInfo.getTransMode();
            ReceiptMsg.getInstance().publishInfo("transMode=" + transMode + ",reclUrl=" + reclUrl);
            switch (transMode) {
                case "LOCAL":
                    System.out.println("----------------------LOCAL--------------------");
                    receiptParserIntoDb.parse(reclUrl);
                    break;
                case "IBMMQ":
                    System.out.println("----------------------IBMMQ--------------------");
                    break;
                case "MSMQ":
                    System.out.println("----------------------MSMQ--------------------");
                    break;
                case "WEB":
                    System.out.println("----------------------WEB--------------------");
                    break;
                case "WCF":
                    System.out.println("----------------------WCF--------------------");
                    break;
            }


        }

    }


    /**
     * @param areaCode 监管场所
     * @return
     * @Description 查询路由配置表
     */
    private EdiRoutingInfo getEdiRoutingInfo(final String areaCode, final int passageway) {
        EntityWrapper entityWrapper = new EntityWrapper<EdiRoutingInfo>();
        entityWrapper.eq("AREA_CODE", areaCode);//监管场所
        entityWrapper.eq("PASSAGEWAY", passageway);//传输类型
//        entityWrapper.eq("DOC_TYPE", docType);//单据类型
//        entityWrapper.eq("BIZ_TYPE", bizType);//业务类型
        entityWrapper.eq("status", TransTypeEnum.Y);//启用标识
        EdiRoutingInfo ediRoutingInfo = ediRoutingInfoService.selectOne(entityWrapper);
        if (ediRoutingInfo == null) {
            String msg = "请检查路由配置表edi_routing_info";
            ReceiptMsg.getInstance().publishError(msg);
            byte[] bytes = null;
            try {
                bytes = msg.getBytes("utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            inserEdiMessageLog("zzzzzz", "zzzzzz", areaCode, "", bytes, "", TransTypeEnum.EDI_ROUTING_ERROR, "");
        }
        return ediRoutingInfo;
    }


    private void inserEdiMessageLog(String docType, String bizType, String areaCode, String fileName, byte[] msg, String checkInfo, String status, String messageDataUid) {
        EdiMessageLog ediMessageLog = new EdiMessageLog();
        ediMessageLog.setUid(UUIDGenerator.getUUID());
        ediMessageLog.setAreaCode(areaCode);
        ediMessageLog.setBizType(bizType);
        ediMessageLog.setDocType(docType);
        ediMessageLog.setFileName(fileName);
        ediMessageLog.setProcessingTime(DateUtil.now());
        ediMessageLog.setProcessingLog(msg);
        ediMessageLog.setCheckInfo(checkInfo.getBytes());
        ediMessageLog.setStatus(status);
        ediMessageLog.setTransType(TransTypeEnum.S);
        ediMessageLog.setMessageUid(messageDataUid);
        ediMessageLogService.insert(ediMessageLog);
    }

}
