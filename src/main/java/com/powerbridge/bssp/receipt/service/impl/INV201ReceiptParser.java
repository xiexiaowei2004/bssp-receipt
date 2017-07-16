package com.powerbridge.bssp.receipt.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.inv.entity.*;
import com.powerbridge.bssp.inv.service.*;
import com.powerbridge.bssp.receipt.util.PostActionEnum;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：INV201ReceiptParser
 * 类描述：保税核注清单审批回执<INV201>
 * 创建人：xwq
 * 创建时间：2017年6月15日19:39:30
 *
 * @version 1.0
 */
@Component("INV201")
public class INV201ReceiptParser extends AbstractReceiptParser {

    @Autowired
    private IInvBscService invBscService;
    @Autowired
    private IInvDtService invDtService;
    @Autowired
    private IInvDclStockService invDclStockService;
    @Autowired
    private IInvDtSimpleService invDtSimpleService;
    @Autowired
    private IInvAcmpFormDtService invAcmpFormDtService;

    @Autowired
    private IInvCusBscService invCusBscService;
    @Autowired
    private IInvCusDtService invCusDtService;
    @Autowired
    private IInvCusDclStockService invCusDclStockService;
    @Autowired
    private IInvCusDtSimpleService invCusDtSimpleService;

    @Autowired
    private IInvHisBscService invHisBscService;
    @Autowired
    private IInvHisDtService invHisDtService;
    @Autowired
    private IInvHisDclStockService invHisDclStockService;
    @Autowired
    private IInvHisDtSimpleService invHisDtSimpleService;
    @Autowired
    private IInvHisAcmpFormDtService invHisAcmpFormDtService;

    /**
     * 保税核注清单审批回执<INV201>
     *
     * @throws Exception
     */
    @Override
    public PostActionEnum postAction() throws Exception {
        //表头service
        bscService = invBscService;
        if (compareToManageResult("BondInvtBsc")) {
            try {
                //回执状态为通过状态时更新本地表头表体数据
                if (result.equals("1")) {
                    //保存正式表表头数据
                    saveBsc();
                    //保存表体保税核注清单商品数据,节点为BondInvtDt
                    saveDt();
                    //保存表体清单随附出入库单明细数据,节点为SasCentDclStock
                    saveDclStock();
                    //保存表体简单加工成品明细数据,节点为BondInvtDtSimple
                    saveDtSimple();

                    //写入历史表表头
                    insertHisBsc();
                    //写入历史成品料件表数据
                    insertHisDt();
                    //写入历史清单随附出入库单明细数据
                    insertHisDclStock();
                    //写入简单加工成品明细数据
                    insertHisDtSimple();
                    //写入历史随附单证表
                    insertHisAcmpFormDt();

                    //删除预录入表表头和表身
                    deletePre();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
            return PostActionEnum.SUCCESS_1;
        } else {
            return PostActionEnum.SUCCESS_2;
        }
    }

    /**
     * 保存保税核注清单审批表头数据
     *
     * @throws Exception
     */
    private void saveBsc() throws Exception {
        //没有变更，都是新增
        saveBsc(new InvCusBsc(), invCusBscService, 0);
    }

    /**
     * 保存保税核注清单审批料件数据
     *
     * @throws Exception
     */
    private void saveDt() throws Exception {
        //获取数据节点集合
        List<Element> elementList = getElementList("BondInvtDt");
        if (null != elementList) {//加入判断
            for (Element element : elementList) {
                //商品序号
                String gdsSeqno = element.elementText("gdsSeqno").trim();
                //查询条件
                EntityWrapper dtEntityWrapper = new EntityWrapper();
                dtEntityWrapper.eq("SEQ_NO", seqNo);
                dtEntityWrapper.eq("GDS_SEQNO", gdsSeqno);//商品序号
                //没有变更都是新增 3:新增
                dataElement = element;
                saveDtDataElement(dtEntityWrapper, invDtService, new InvCusDt(), invCusDtService, "3");
            }
        }
    }

    /**
     * 保税核注清单清单随附出入库单明细(集报专用）
     *
     * @throws Exception
     */
    private void saveDclStock() throws Exception {
        List<Element> elementList = getElementList("SasCentDclStock");
        if (null != elementList) {
            for (Element element : elementList) {
                //出入库单编号
                String sasStockNo = element.elementText("sasStockNo");
                //查询条件
                EntityWrapper dtEntityWrapper = new EntityWrapper();
                dtEntityWrapper.eq("SEQ_NO", seqNo);
                dtEntityWrapper.eq("SAS_STOCK_NO", sasStockNo);
                //没有变更都是新增 3:新增
                dataElement = element;
                saveDtDataElement(dtEntityWrapper, invDclStockService, new InvCusDclStock(), invCusDclStockService, "3");
            }
        }
    }

    /**
     * 简单加工成品明细
     *
     * @throws Exception
     */
    private void saveDtSimple() throws Exception {
        List<Element> elementList = getElementList("BondInvtDtSimple");
        if (null != elementList) {
            for (Element element : elementList) {
                //商品序号
                String gdsSeqno = element.elementText("gdsSeqno").trim();
                //查询条件
                EntityWrapper dtEntityWrapper = new EntityWrapper();
                dtEntityWrapper.eq("SEQ_NO", seqNo);
                dtEntityWrapper.eq("GDS_SEQNO", gdsSeqno);
                //没有变更都是新增 3:新增
                dataElement = element;
                saveDtDataElement(dtEntityWrapper, invDtSimpleService, new InvCusDtSimple(), invCusDtSimpleService, "3");
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


    /**
     * 写入历史清单商品表，都是新增，从预录入表查询
     *
     * @throws Exception
     */
    private void insertHisDt() throws Exception {
        //历史清单商品表
        insertHis(new InvHisDt(), invDtService, invHisDtService);
    }

    /**
     * 写入历史清单随附出入库单明细数据，都是新增，从预录入表查询
     *
     * @throws Exception
     */
    private void insertHisDclStock() throws Exception {
        //历史清单随附出入库单明细数据
        insertHis(new InvHisDclStock(), invDclStockService, invHisDclStockService);
    }

    /**
     * 写入历史简单加工成品明细表，都是新增，从预录入表查询
     *
     * @throws Exception
     */
    private void insertHisDtSimple() throws Exception {
        //历史简单加工成品明细表
        insertHis(new InvHisDtSimple(), invDtSimpleService, invHisDtSimpleService);
    }

    /**
     * 写入历史随附单证表，都是新增，从预录入表查询
     *
     * @throws Exception
     */
    private void insertHisAcmpFormDt() throws Exception {
        //历史简单加工成品明细表
        insertHis(new InvHisAcmpFormDt(), invAcmpFormDtService, invHisAcmpFormDtService);
    }

    //删除预录入表头和表体数据
    private void deletePre() throws Exception {
        invBscService.deleteBySeqNo(seqNo);
    }
}



