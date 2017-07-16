package com.powerbridge.bssp.receipt.service.impl;


import com.powerbridge.bssp.receipt.util.BeanUtil;
import com.powerbridge.bssp.receipt.util.PostActionEnum;
import com.powerbridge.bssp.receipt.util.XMLUtils;
import com.powerbridge.bssp.sas.service.ISasDclBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：SAS202ReceiptParser
 * 类描述：申报表暂停恢复回执<SAS202>
 * 创建人：xwq
 * 创建时间：2017-06-18 15:24:56
 *
 * @version 1.0
 */
@Component("SAS202")
public class SAS202ReceiptParser extends AbstractReceiptParser {

    @Autowired
    private ISasDclBscService sasDclBscService;

    /**
     * 申报表暂停恢复回执<SAS202>
     *
     * @throws Exception
     */
    @Override
    public PostActionEnum postAction() throws Exception {
        //获取回执xml数据
        bscElement = XMLUtils.getElementByXpath("//SasDclBsc", document);
        //获取表头数据
        chgTmsCnt = Integer.parseInt(bscElement.elementText("chgTmsCnt"));
        selectOneBsc();
        BeanUtil.setProperty(bscBeanObject, "dclTbStucd", bscElement.elementText("dclTbStucd"));
        bscService = sasDclBscService;
        //更新表头数据
        bscService.updateById(bscBeanObject);
        return PostActionEnum.SUCCESS_1;
    }

}



