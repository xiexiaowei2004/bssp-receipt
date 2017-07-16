package com.powerbridge.bssp.receipt.service.impl;


import com.powerbridge.bssp.receipt.util.PostActionEnum;
import com.powerbridge.bssp.sas.entity.SasVehicleCus;
import com.powerbridge.bssp.sas.entity.SasVehicleHis;
import com.powerbridge.bssp.sas.service.ISasVehicleBscService;
import com.powerbridge.bssp.sas.service.ISasVehicleCusService;
import com.powerbridge.bssp.sas.service.ISasVehicleHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：ReceiptParserEtArcrpBsc
 * 类描述：车辆信息备案/变更审核回执<SAS231>
 * 创建人：danagao
 * 创建时间：2017/6/7 17:59
 *
 * @version 1.0
 */
@Component("SAS231")
public class SAS231ReceiptParser extends AbstractReceiptParser {

    @Autowired
    private ISasVehicleBscService sasVehicleBscService;

    @Autowired
    private ISasVehicleCusService sasVehicleCusService;

    @Autowired
    private ISasVehicleHisService sasVehicleHisService;

    @Override
    public PostActionEnum postAction() throws Exception {
        //表头service
        bscService = sasVehicleBscService;
        //优先级比较
        if (compareToManageResult("SasVehicleBsc")) {
            try {
                //回执状态为成功时才修改正式表数据
                if (result.equals("1")) {
                    //保存正式表数据
                    saveBsc();

                    //写入历史表数据
                    insertHis();

                    //删除预录入表数据
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
     * 保存正式表数据
     *
     * @throws Exception
     */
    private void saveBsc() throws Exception {
        saveBsc(new SasVehicleCus(), sasVehicleCusService);
    }

    /**
     * 写入历史表，都是新增
     *
     * @throws Exception
     */
    private void insertHis() throws Exception {
        //写入历史表表头
        insertHis(new SasVehicleHis(), bscBeanObject, sasVehicleHisService);
    }

    /**
     * 删除预录入表头数据
     *
     * @throws Exception
     */
    private void deletePre() throws Exception {
        sasVehicleBscService.deleteBySeqNo(seqNo);
    }

}