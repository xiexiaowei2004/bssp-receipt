package com.powerbridge.bssp.receipt.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.receipt.util.PostActionEnum;
import com.powerbridge.bssp.sas.entity.*;
import com.powerbridge.bssp.sas.service.*;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：SAS201ReceiptParser
 * 类描述：业务申报表审核回执/回执补发送<SAS201>
 * 创建人：xwq
 * 创建时间：2017/6/7 17:59
 *
 * @version 1.0
 */
@Component("SAS201")
public class SAS201ReceiptParser extends AbstractReceiptParser {

    @Autowired
    private ISasDclBscService sasDclBscService;
    @Autowired
    private ISasDclDtService sasDclDtService;
    @Autowired
    private ISasDclUcnsDtService sasDclUcnsDtService;
    @Autowired
    private ISasDclAcmpFormDtService sasDclAcmpFormDtService;

    @Autowired
    private ISasDclCusBscService sasDclCusBscService;
    @Autowired
    private ISasDclCusDtService sasDclCusDtService;
    @Autowired
    private ISasDclCusUcnsDtService sasDclCusUcnsDtService;
    @Autowired
    private ISasDclCusAcmpFormDtService sasDclCusAcmpFormDtService;

    @Autowired
    private ISasDclHisBscService sasDclHisBscService;
    @Autowired
    private ISasDclHisDtService sasDclHisDtService;
    @Autowired
    private ISasDclHisUcnsDtService sasDclHisUcnsDtService;
    @Autowired
    private ISasDclHisAcmpFormDtService sasDclHisAcmpFormDtService;

    /**
     * 申报表SAS201
     *
     * @throws Exception
     */
    @Override
    public PostActionEnum postAction() throws Exception {
        //表头service
        bscService = sasDclBscService;
        //优先级比较
        if (compareToManageResult("SasDclBsc")) {
            try {
                //回执状态为成功时才修改正式表数据
                if (result.equals("1")) {
                    //保存正式表表头数据
                    saveBsc();
                    //保存正式表商品数据
                    saveDt();
                    //保存单耗表数据
                    saveUcnsDt();
                    //保存随附单证表数据
                    saveAcmpFormDt();

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
     * 保存业务申报表表头数据
     *
     * @throws Exception
     */
    private void saveBsc() throws Exception {
        saveBsc(new SasDclCusBsc(), sasDclCusBscService);
    }


    /**
     * 保存正式表商品数据
     *
     * @throws Exception
     */
    private void saveDt() throws Exception {
        //** 业务申报表  表体 sas_dcl_dt  解析xml的SasDclDt节点，然后取值，set到sas_dcl_dt表格的实体类中
        List<Element> SasDclDts = getElementList("SasDclDt");
        if (null != SasDclDts) {//加入判断
            for (Element element : SasDclDts) {
                String sasDclSeqno = element.elementText("sasDclSeqno").trim();
                EntityWrapper dtEntityWrapper = new EntityWrapper();
                dtEntityWrapper.eq("SEQ_NO", seqNo);
                dtEntityWrapper.eq("SAS_DCL_SEQNO", sasDclSeqno);//申报序号
                //保存表体节点数据
                dataElement = element;
                saveDtDataElement(dtEntityWrapper, sasDclDtService, new SasDclCusDt(), sasDclCusDtService);
            }
        }
    }

    /**
     * 业务申报单耗表
     *
     * @throws Exception
     */
    private void saveUcnsDt() throws Exception {
        //	 *业务申报单耗表  表体 sas_dcl_ucns_dt  解析xml的SasDclDt节点，然后取值，set到sas_dcl_ucns_dt表格的实体类中
        List<Element> elementList = getElementList("SasDclUcnsDt");
        if (null != elementList) {
            for (Element element : elementList) {
                String endprdSeqno = element.elementText("endprdSeqno").trim();
                Integer mtpckSeqno = Integer.valueOf(element.elementText("mtpckSeqno").trim());
                //获取预录入对象
                EntityWrapper dtEntityWrapper = new EntityWrapper();
                dtEntityWrapper.eq("SEQ_NO", seqNo);
                dtEntityWrapper.eq("ENDPRD_SEQNO", endprdSeqno);//成品申报序号
                dtEntityWrapper.eq("MTPCK_SEQNO", mtpckSeqno);//料件申报序号
                //保存表体节点数据
                dataElement = element;
                saveDtDataElement(dtEntityWrapper, sasDclUcnsDtService, new SasDclCusUcnsDt(), sasDclCusUcnsDtService);
            }
        }
    }

    /**
     * 保存随附单证表数据
     *
     * @throws Exception
     */
    private void saveAcmpFormDt() throws Exception {
        // * 业务申报随附单证表  表体 sas_dcl_acmp_form_dt  解析xml的SasDclAcmpFormDt节点，然后取值，set到sas_dcl_acmp_form_dt表格的实体类中
        List<Element> elementList = getElementList("SasDclAcmpFormDt");
        if (null != elementList) {//加入判断
            for (Element element : elementList) {
                String acmpFormSeqno = element.elementText("acmpFormSeqno").trim();
                //获取预录入对象
                EntityWrapper dtEntityWrapper = new EntityWrapper();
                dtEntityWrapper.eq("SEQ_NO", seqNo);
                dtEntityWrapper.eq("ACMP_FORM_SEQNO", acmpFormSeqno);//随附单证序号
                //保存表体节点数据
                dataElement = element;
                saveDtDataElement(dtEntityWrapper, sasDclAcmpFormDtService, new SasDclCusAcmpFormDt(), sasDclCusAcmpFormDtService, "3");
            }
        }
    }

    //写入历史表表头，都是新增
    private void insertHis() throws Exception {
        //写入历史表表头
        insertHis(new SasDclHisBsc(), bscBeanObject, sasDclHisBscService);
    }


    //写入历史表表体，都是新增，从预录入表查询
    private void insertHisDt() throws Exception {
        insertHis(new SasDclHisDt(), sasDclDtService, sasDclHisDtService);

        insertHis(new SasDclHisUcnsDt(), sasDclUcnsDtService, sasDclHisUcnsDtService);

        insertHis(new SasDclHisAcmpFormDt(), sasDclAcmpFormDtService, sasDclHisAcmpFormDtService);

    }


    //删除预录入表头数据
    private void deletePre() throws Exception {
        sasDclBscService.deleteBySeqNo(seqNo);
    }

}