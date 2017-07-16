package com.powerbridge.bssp.receipt.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.receipt.util.PostActionEnum;
import com.powerbridge.bssp.sas.entity.SasStockCus;
import com.powerbridge.bssp.sas.entity.SasStockCusDt;
import com.powerbridge.bssp.sas.entity.SasStockHis;
import com.powerbridge.bssp.sas.entity.SasStockHisDt;
import com.powerbridge.bssp.sas.service.*;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：SAS211ReceiptParser
 * 类描述：出入库单审核回执<SAS211>
 * 创建人：xwq
 * 创建时间：2017年6月13日09:45:13
 *
 * @version 1.0
 */
@Component("SAS211")
public class SAS211ReceiptParser extends AbstractReceiptParser {

    @Autowired
    private ISasStockBscService sasStockBscService;
    @Autowired
    private ISasStockDtService sasStockDtService;

    @Autowired
    private ISasStockCusService sasStockCusService;
    @Autowired
    private ISasStockCusDtService sasStockCusDtService;

    @Autowired
    private ISasStockHisService sasStockHisService;
    @Autowired
    private ISasStockHisDtService sasStockHisDtService;

    @Override
    public PostActionEnum postAction() throws Exception {
        //表头service
        bscService = sasStockBscService;
        //比较优先级
        if (compareToManageResult("SasStockBsc")) {
            try {
                //回执状态为成功时才修改正式表数据
                if (result.equals("1")) {
                    //保存正式表表体数据
                    saveBsc();
                    //保存出入库单表体
                    saveDt();

                    //写入历史表表头
                    insertHis();
                    //写入历史表表体
                    insertHisDt();

                    //删除预录入表表头和表身
                    deletePre();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            return PostActionEnum.SUCCESS_1;
        } else {
            return PostActionEnum.SUCCESS_2;
        }
    }

    /**
     * 返填正式表
     *
     * @throws Exception
     */
    private void saveBsc() throws Exception {
        saveBsc(new SasStockCus(), sasStockCusService);
    }

    /**
     * 保存出入库单表体
     *
     * @throws Exception
     */
    private void saveDt() throws Exception {
        // ** 出入库单表体 SAS_STOCK_DT 解析xml的SasStockDt节点，然后取值，set到 SAS_STOCK_DT表格的实体类中
        List<Element> elementList = getElementList("SasStockDt");
        if (null != elementList) {// 加入判断
            for (Element element : elementList) {
                String sasDclSeqno = element.elementText("sasDclSeqno").trim();
                EntityWrapper dtEntityWrapper = new EntityWrapper();
                dtEntityWrapper.eq("SEQ_NO", seqNo);
                dtEntityWrapper.eq("SAS_DCL_SEQNO", sasDclSeqno);// 申报序号
                String modfMarkcd;
                if (chgTmsCnt == 0) {
                    //新增
                    modfMarkcd = "3";
                } else {
                    //修改
                    modfMarkcd = "1";
                }
                //保存表体节点数据
                dataElement = element;
                saveDtDataElement(dtEntityWrapper, sasStockDtService, new SasStockCusDt(), sasStockCusDtService, modfMarkcd);
            }
        }
    }

    //写入历史表表头，都是新增
    private void insertHis() throws Exception {
        insertHis(new SasStockHis(), bscBeanObject, sasStockHisService);
    }

    //写入历史表表体，都是新增，从预录入表查询
    private void insertHisDt() throws Exception {
        insertHis(new SasStockHisDt(), sasStockDtService, sasStockHisDtService);

    }

    //删除预录入表头数据
    private void deletePre() throws Exception {
        sasStockBscService.deleteBySeqNo(seqNo);
    }
}
