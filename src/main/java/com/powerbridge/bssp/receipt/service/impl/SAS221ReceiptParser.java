package com.powerbridge.bssp.receipt.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.receipt.util.PostActionEnum;
import com.powerbridge.bssp.saspass.entity.*;
import com.powerbridge.bssp.saspass.service.*;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：SAS221ReceiptParser
 * 类描述：核放单审核回执/回执补发送<SAS221>
 * 创建人：xwq
 * 创建时间：2017-06-16 11:25:30
 *
 * @version 1.0
 */
@Component("SAS221")
public class SAS221ReceiptParser extends AbstractReceiptParser {

    //通过容器 获取service
    @Autowired
    private ISasPassportDtService sasPassportDtService;
    @Autowired
    private ISasPassportRltService sasPassportRltService;
    @Autowired
    private ISasPassportBscService sasPassportBscService;

    @Autowired
    private ISasPassportCusBscService sasPassportCusBscService;
    @Autowired
    private ISasPassportCusDtService sasPassportCusDtService;
    @Autowired
    private ISasPassportCusRltService sasPassportCusRltService;

    @Autowired
    private ISasPassportHisBscService sasPassportHisBscService;
    @Autowired
    private ISasPassportHisDtService sasPassportHisDtService;
    @Autowired
    private ISasPassportHisRltService sasPassportHisRltService;

    @Override
    public PostActionEnum postAction() throws Exception {
        //表头service
        bscService = sasPassportBscService;
        //比较优先级
        if (compareToManageResult("SasPassportBsc")) {
            try {
                //回执状态为成功时才修改正式表数据
                if (result.equals("1")) {
                    //保存核放单表数据
                    saveBsc();
                    //保存核放单货物表数据
                    saveDt();
                    //保存核放单关联单证表数据
                    saveRlt();

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
     * 保存核放单表数据
     *
     * @param
     * @throws Exception
     */
    private void saveBsc() throws Exception {
        saveBsc(new SasPassportCusBsc(), sasPassportCusBscService);
    }


    /**
     * 保存核放单货物表数据
     *
     * @throws Exception
     */
    private void saveDt() throws Exception {
        // ** 核放单表体 sas_passport_dt 解析xml的SasPassportDt节点，然后取值，set到sas_passport_dt表格的实体类中
        List<Element> elementList = getElementList("SasPassportDt");
        if (null != elementList) {// 加入判断
            for (Element element : elementList) {
                //序号
                String passportSeqno = element.elementText("passportSeqno").trim();
                EntityWrapper dtEntityWrapper = new EntityWrapper();
                dtEntityWrapper.eq("SEQ_NO", seqNo);
                dtEntityWrapper.eq("PASSPORT_SEQNO", passportSeqno);
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
                saveDtDataElement(dtEntityWrapper, sasPassportDtService, new SasPassportDt(), sasPassportCusDtService, modfMarkcd);
            }
        }
    }

    /**
     * 保存核放单关联单证表数据
     *
     * @throws Exception
     */
    private void saveRlt() throws Exception {
        // **  SAS_PASSPORT_RLT解析xml的SasPassportRlt节点，然后取值，set到SAS_PASSPORT_RLT表格的实体类中
        List<Element> elementList = document.getRootElement().elements("SasPassportRlt");
        if (elementList != null) {// 加入判断
            for (Element element : elementList) {
                //关联单证编号
                String rltNo = element.elementText("rltNo").trim();
                EntityWrapper dtEntityWrapper = new EntityWrapper();
                dtEntityWrapper.eq("SEQ_NO", seqNo);
                dtEntityWrapper.eq("RLT_NO", rltNo);
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
                saveDtDataElement(dtEntityWrapper, sasPassportRltService, new SasPassportRlt(), sasPassportCusRltService, modfMarkcd);
            }
        }
    }

    //写入历史表表头，都是新增
    private void insertHis() throws Exception {
        //写入历史表表头
        insertHis(new SasPassportHisBsc(), bscBeanObject, sasPassportHisBscService);
    }

    //写入历史表表体，都是新增，从预录入表查询
    private void insertHisDt() throws Exception {
        insertHis(new SasPassportHisDt(), sasPassportDtService, sasPassportHisDtService);

        insertHis(new SasPassportHisRlt(), sasPassportRltService, sasPassportHisRltService);

    }

    //删除预录入表头数据
    private void deletePre() throws Exception {
        sasPassportBscService.deleteBySeqNo(seqNo);
    }
}
