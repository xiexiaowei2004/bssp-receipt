package com.powerbridge.bssp.receipt.service.impl;


import com.powerbridge.bssp.receipt.util.BeanUtil;
import com.powerbridge.bssp.receipt.util.PostActionEnum;
import com.powerbridge.bssp.receipt.util.XMLUtils;
import com.powerbridge.bssp.sas.service.ISasStockBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：SAS212ReceiptParser
 * 类描述：出入库单暂停恢复回执<SAS212>
 * 创建人：willChen
 * 创建时间：2017-06-28 11:47:26
 *
 * @version 1.0
 */
@Component("SAS212")
public class SAS212ReceiptParser extends AbstractReceiptParser {

    @Autowired
    private ISasStockBscService sasStockBscService;

    /**
     * 出入库单暂停恢复回执<SAS212>
     *
     * @throws Exception
     */
    @Override
    public PostActionEnum postAction() throws Exception {

        //获取回执xml数据
        bscElement = XMLUtils.getElementByXpath("//SasStockBsc", document);
        chgTmsCnt = Integer.parseInt(bscElement.elementText("chgTmsCnt"));
        selectOneBsc();
        BeanUtil.setProperty(bscBeanObject, "dclTbStucd", bscElement.elementText("dclTbStucd"));
        bscService = sasStockBscService;
        //更新表头数据
        bscService.updateById(bscBeanObject);
        return PostActionEnum.SUCCESS_1;
    }

}



