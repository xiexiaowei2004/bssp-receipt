package com.powerbridge.bssp.receipt.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.receipt.util.PostActionEnum;
import com.powerbridge.bssp.receipt.util.XMLUtils;
import com.powerbridge.bssp.sas.entity.SasDclBsc;
import com.powerbridge.bssp.sas.entity.SasDclCusBsc;
import com.powerbridge.bssp.sas.entity.SasDclDt;
import com.powerbridge.bssp.sas.entity.SasDclUcnsDt;
import com.powerbridge.bssp.sas.service.*;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：SAS203ReceiptParser
 * 类描述：业务申报表数据同步回执<SAS203>
 * 创建人：xwq
 * 创建时间：2017/6/7 17:59
 *
 * @version 1.0
 */
@Component("SAS203")
public class SAS203ReceiptParser extends AbstractReceiptParser {

    private SasDclBsc sasDclBsc;
    @Autowired
    private ISasDclBscService sasDclBscService;
    @Autowired
    private ISasDclDtService sasDclDtService;
    @Autowired
    private ISasDclUcnsDtService sasDclUcnsDtService;

    @Autowired
    private ISasDclCusBscService sasDclCusBscService;
    @Autowired
    private ISasDclCusDtService sasDclCusDtService;
    @Autowired
    private ISasDclCusUcnsDtService sasDclCusUcnsDtService;

    /**
     * 业务申报表数据同步回执<SAS203>
     *
     * @throws Exception
     */
    @Override
    public PostActionEnum postAction() throws Exception {
        try {
            //保存正式表表头数据
            saveBsc();
            //保存正式表表体数据
            saveDt();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return PostActionEnum.SUCCESS_1;
    }

    /**
     * 同步表头数据
     *
     * @throws Exception
     */
    private void saveBsc() throws Exception {
        bscBeanObject = new SasDclCusBsc();
        //获取回执xml数据
        bscElement = XMLUtils.getElementByXpath("//SasDclBsc", document);
        saveBsc(new SasDclCusBsc(), sasDclCusBscService, 1);
    }

    /**
     * 先找到表头中的seqNo的值，然后取表体的list集合，并且删除，取得xml中的字段全部新增
     *
     * @throws Exception
     */
    private void saveDt() throws Exception {
        SasDclDt sasDclDt = new SasDclDt();
        SasDclUcnsDt sasDclUcnsDt = new SasDclUcnsDt();
        seqNo = sasDclBsc.getSeqNo();//用来查询表体
        String etpsPreentNo = sasDclBsc.getEtpsPreentNo();//获取主表中的企业预录入编号
        //** 业务申报表  表体 sas_dcl_dt  先判断SasDclDts不为空，然后再按照seqNo查询出List<SasDclDt>,删除
        List<Element> SasDclDts = getElementList("//SasDclDt");
        if (null != SasDclDts) {
            EntityWrapper<SasDclDt> wrapper = new EntityWrapper<>();
            wrapper.eq("SEQ_NO", seqNo);//单据编号

            sasDclDtService.delete(wrapper);//批量删除
            for (Element sasDclDtElement : SasDclDts) {
                //把xml中的值添加到对象中
                getEdiXmlInfoMap(sasDclDt, sasDclDtElement);
                sasDclDt.setUid(UUIDGenerator.getUUID());
                sasDclDt.setEtpsPreentNo(etpsPreentNo);
                sasDclDt.setSeqNo(seqNo);
                sasDclDtService.insert(sasDclDt);//新增
            }
        }

        //	 *业务申报单耗表  表体 sas_dcl_ucns_dt
        List<Element> SasDclUcnsDts = getElementList("//SasDclUcnsDt");
        if (null != SasDclUcnsDts) {
            EntityWrapper<SasDclUcnsDt> wrapper = new EntityWrapper<>();
            wrapper.eq("SEQ_NO", seqNo);//单据编号

            sasDclUcnsDtService.delete(wrapper);//批量删除

            for (Element sasDclUcnsDtElement : SasDclUcnsDts) {
                if (null != sasDclUcnsDtElement) {
                    //把xml中的值添加到对象中
                    getEdiXmlInfoMap(sasDclUcnsDt, sasDclUcnsDtElement);
                    sasDclUcnsDt.setUid(UUIDGenerator.getUUID());
                    sasDclUcnsDt.setEtpsPreentNo(etpsPreentNo);
                    sasDclUcnsDt.setSeqNo(seqNo);
                    sasDclUcnsDtService.insert(sasDclUcnsDt);//新增
                }
            }
        }
    }
}



