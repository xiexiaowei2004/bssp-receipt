package com.powerbridge.bssp.receipt.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.dcr.entity.*;
import com.powerbridge.bssp.dcr.service.*;
import com.powerbridge.bssp.receipt.util.PostActionEnum;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：EMS221ReceiptParser
 * 类描述：账册核销审批回执<EMS221>
 * 创建人：xwq
 * 创建时间：2017-06-18 11:31:36
 *
 * @version 1.0
 */

@Component("EMS221")
public class EMS221ReceiptParser extends AbstractReceiptParser {
    @Autowired
    private IDcrChgoffBscService dcrChgoffBscService;
    @Autowired
    private IDcrChgoffExgService dcrChgoffExgService;
    @Autowired
    private IDcrChgoffImgService dcrChgoffImgService;
    @Autowired
    private IDcrChgoffInvtLtService dcrChgoffInvtLtService;
    @Autowired
    private IDcrAdjaccAcmpFormDtService dcrAdjaccAcmpFormDtService;

    @Autowired
    private IDcrChgoffCusBscService dcrChgoffCusBscService;
    @Autowired
    private IDcrChgoffCusExgService dcrChgoffCusExgService;
    @Autowired
    private IDcrChgoffCusImgService dcrChgoffCusImgService;

    @Autowired
    private IDcrChgoffHisBscService dcrChgoffHisBscService;
    @Autowired
    private IDcrChgoffHisExgService dcrChgoffHisExgService;
    @Autowired
    private IDcrChgoffHisImgService dcrChgoffHisImgService;
    @Autowired
    private IDcrChgoffHisInvtLtService dcrChgoffHisInvtLtService;
    @Autowired
    private IDcrAdjaccHisAcmpFormDtService dcrAdjaccHisAcmpFormDtService;

    /**
     * 账册核销审批回执<EMS221>
     *
     * @throws Exception
     */
    @Override
    public PostActionEnum postAction() throws Exception {
        //表头service
        bscService = dcrChgoffBscService;
        if (compareToManageResult("EmsChgoffBsc", "chgoffTmsCnt")) {
            try {
                //回执状态为通过状态时更新本地表头表体数据
                if (result.equals("2")) {
                    //保存正式表头数据
                    saveBsc();
                    //保存正式表体数据
                    saveDt();

                    //写入历史表表头
                    insertHisBsc();
                    //写入历史表表体
                    insertHisDt();
                    //写入历史账册报核清单
                    insertHisInvtLt();
                    //写入历史随附单证表
                    insertHisAcmpFormDt();

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
     * 保存账册报核表头数据
     *
     * @throws Exception
     */
    private void saveBsc() throws Exception {
        //报核没有变更，都是新增 0：新增
        saveBsc(new DcrChgoffCusBsc(), dcrChgoffCusBscService, 0);
    }

    /**
     * 保存账册报核料件 成品
     *
     * @throws Exception
     */
    private void saveDt() throws Exception {
        List<Element> elementList = getElementList("EmsChgoffDt");
        if (null != elementList) {//加入判断
            for (Element element : elementList) {
                //料件成品类型代码 E：成品 I：料件
                String typecd = element.elementText("typecd");
                //根据不同类型代码编辑不同数据
                if (typecd == null) {
                    throw new Exception("料件成品标记代码为空");
                }
                Boolean isE = typecd.equals("E");
                //预录入表服务
                IService arcrpService = isE ? dcrChgoffExgService : dcrChgoffImgService;
                //正式表对象
                Object cusDtObject = isE ? new DcrChgoffCusExg() : new DcrChgoffCusImg();
                //正式表服务
                IService cusDtService = isE ? dcrChgoffCusExgService : dcrChgoffCusImgService;
                //商品序号
                BigDecimal gdsSeqno = new BigDecimal(element.elementText("seqno"));
                //查询条件
                EntityWrapper dtEntityWrapper = new EntityWrapper();
                dtEntityWrapper.eq("SEQ_NO", seqNo);
                dtEntityWrapper.eq("GDS_SEQNO", gdsSeqno);
                //没有变更都是新增 3:新增
                dataElement = element;
                saveDtDataElement(dtEntityWrapper, arcrpService, cusDtObject, cusDtService, "3");
            }
        }
    }

    //写入历史表表头，都是新增
    private void insertHisBsc() throws Exception {
        //写入历史表表头
        insertHis(new DcrChgoffHisBsc(), bscBeanObject, dcrChgoffHisBscService);
    }


    //写入历史表表体，都是新增，从预录入表查询
    private void insertHisDt() throws Exception {
        //成品历史表
        insertHis(new DcrChgoffHisExg(), dcrChgoffExgService, dcrChgoffHisExgService);
        //料件历史表
        insertHis(new DcrChgoffHisImg(), dcrChgoffImgService, dcrChgoffHisImgService);
    }

    //写入历史账册报核清单，都是新增，从预录入表查询
    private void insertHisInvtLt() throws Exception {
        insertHis(new DcrChgoffHisInvtLt(), dcrChgoffInvtLtService, dcrChgoffHisInvtLtService);
    }

    //写入历史随附单证表，都是新增，从预录入表查询
    private void insertHisAcmpFormDt() throws Exception {
        insertHis(new DcrAdjaccHisAcmpFormDt(), dcrAdjaccHisAcmpFormDtService, dcrAdjaccHisAcmpFormDtService);
    }

    //删除预录入表头和表体数据
    private void deletePre() throws Exception {
        dcrChgoffBscService.deleteBySeqNo(seqNo);
    }

}
