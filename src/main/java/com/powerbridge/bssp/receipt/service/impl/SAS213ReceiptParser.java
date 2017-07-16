package com.powerbridge.bssp.receipt.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.receipt.util.PostActionEnum;
import com.powerbridge.bssp.receipt.util.XMLUtils;
import com.powerbridge.bssp.sas.entity.SasStockBsc;
import com.powerbridge.bssp.sas.entity.SasStockDt;
import com.powerbridge.bssp.sas.service.ISasStockBscService;
import com.powerbridge.bssp.sas.service.ISasStockDtService;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：SAS213ReceiptParser
 * 类描述：出入库单数据同步回执
 * 创建人：willChen
 * 创建时间：2017-06-28 14:30:15
 *
 * @version 1.0
 */
@Component("SAS213")
public class SAS213ReceiptParser extends AbstractReceiptParser {

    //通过容器 获取service
    @Autowired
    private
    ISasStockDtService sasStockDtService;
    @Autowired
    private
    ISasStockBscService sasStockBscService;

    /**
     * 出入库单数据同步回执
     *
     * @throws Exception
     */
    @Override
    public PostActionEnum postAction() throws Exception {
        //手动切换数据源并处理事务
//        TransactionStatus status = transactionManagerStatus(DBTypeEnum.sas);
        try {
            //保存正式表表头数据
            saveBsc();
            //保存正式表表体数据
            saveDt();
//            transactionManager.commit(status);
        } catch (Exception e) {
//            transactionManager.rollback(status);
            e.printStackTrace();
            throw new Exception(e);
        }
        return PostActionEnum.SUCCESS_1;
    }

    /**
     * 同步表头
     *
     * @throws Exception
     */
    private void saveBsc() throws Exception {
        bscElement = XMLUtils.getElementByXpath("//SasStockBsc", document);

        bscService = sasStockBscService;
        bscBeanObject = new SasStockBsc();
        //把xml中的值添加到对象中
        getEdiXmlInfoMap(bscBeanObject, bscElement);
        //更新表头数据
        bscService.updateById(bscBeanObject);

    }

    /**
     * 先取表体的list集合，并且删除，取得xml中的字段全部新增
     *
     * @throws Exception
     */
    private void saveDt() throws Exception {

        List<Element> SasStockDts = getElementList("//SasStockDt");
        if (SasStockDts != null) {
            EntityWrapper<SasStockDt> wrapper = new EntityWrapper<>();
            wrapper.eq("SEQ_NO", seqNo);//单据编号

            sasStockDtService.delete(wrapper);//批量删除
            for (Element sasStockDtElement : SasStockDts) {
                //把xml中的值添加到对象中
                SasStockDt sasStockDt = new SasStockDt();
                getEdiXmlInfoMap(sasStockDt, sasStockDtElement);
                sasStockDt.setUid(UUIDGenerator.getUUID());
                sasStockDt.setSeqNo(seqNo);
                sasStockDtService.insert(sasStockDt);//新增
            }
        }
    }
}



