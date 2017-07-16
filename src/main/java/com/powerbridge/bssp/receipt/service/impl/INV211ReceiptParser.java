package com.powerbridge.bssp.receipt.service.impl;


import com.powerbridge.bssp.inv.entity.InvHisBsc;
import com.powerbridge.bssp.inv.service.IInvBscService;
import com.powerbridge.bssp.inv.service.IInvCusBscService;
import com.powerbridge.bssp.inv.service.IInvHisBscService;
import com.powerbridge.bssp.receipt.util.PostActionEnum;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：INV211ReceiptParser
 * 类描述：清单记账回执<INV211>
 * 创建人：xwq
 * 创建时间：2017年6月15日19:39:30
 *
 * @version 1.0
 */
@Component("INV211")
public class INV211ReceiptParser extends AbstractReceiptParser {

    @Autowired
    private IInvBscService invBscService;

    @Autowired
    private IInvCusBscService invCusBscService;

    @Autowired
    private IInvHisBscService invHisBscService;

    /**
     * 清单审批回执<INV211>
     *
     * @throws Exception
     */
    @Override
    public PostActionEnum postAction() throws Exception {
        //表头service
        bscService = invBscService;
        if (compareToManageResult("BondInvtBsc")) {
            //回执状态为通过状态时更新本地表头表体数据
            if (result.equals("1")) {
                //保存正式表表体数据
                saveBsc();
                //保存表体EmsPutrecDt数据,节点为EmsPutrecDt
                saveEmsPutrecDt();
                //保存表体BwsDt数据,节点为BwsDt
                saveBwsDt();

                insertHisBsc();

            }
            return PostActionEnum.SUCCESS_1;
        } else {
            return PostActionEnum.SUCCESS_2;
        }
    }

    /**
     * 保存清单审批表头数据
     *
     * @throws Exception
     */
    private void saveBsc() throws Exception {
        //把xml中的值添加到对象中
        getEdiXmlInfoMap(bscBeanObject, bscElement);
        //更新表头数据
        bscService.updateById(bscBeanObject);
    }

    /**
     * @throws Exception
     */
    private void saveEmsPutrecDt() throws Exception {
        List<Element> emsPutrecDts = document.getRootElement().elements("EmsPutrecDt");
        if (null != emsPutrecDts || emsPutrecDts.size() != 0) {//加入判断
            for (Element emsPutrecDtElement : emsPutrecDts) {
                if (null != emsPutrecDtElement) {
                    //todo
                }
            }
        }
    }

    /**
     * @throws Exception
     */
    private void saveBwsDt() throws Exception {
        List<Element> bwsDts = document.getRootElement().elements("BwsDt");
        if (null != bwsDts || bwsDts.size() != 0) {//加入判断
            for (Element bwsDtElement : bwsDts) {
                if (null != bwsDtElement) {
                    //todo
                }
            }
        }
    }

    /**
     * 写入历史表表头，都是新增
     *
     * @throws Exception
     */
    private void insertHisBsc() throws Exception {
        //写入历史表表头
        insertHis(new InvHisBsc(), bscBeanObject, invHisBscService);
    }

}



