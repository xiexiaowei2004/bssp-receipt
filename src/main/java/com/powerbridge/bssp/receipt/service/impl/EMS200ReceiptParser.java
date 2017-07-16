package com.powerbridge.bssp.receipt.service.impl;


import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.edi.entity.EdiBusinessConfig;
import com.powerbridge.bssp.edi.service.IEdiBusinessConfigService;
import com.powerbridge.bssp.receipt.util.PostActionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：EMS200ReceiptParser
 * 类描述：子报文入库回执
 * 创建人：willChen
 * 创建时间：2017/6/27 16:59
 *
 * @version 1.0
 */
@Component("EMS200")
public class EMS200ReceiptParser extends AbstractReceiptParser {

    @Autowired
    private
    IEdiBusinessConfigService ediBusinessConfigService;


    /**
     * 子报文入库回执
     *
     * @throws Exception
     */
    @Override
    public PostActionEnum postAction() throws Exception {
        //获取单据类别和业务类型
        String docType = infoMap.get("docType").toString();
        String bizType = infoMap.get("bizType").toString();
        //获取数据 拿到service名
        EntityWrapper entityWrapper = new EntityWrapper<EdiBusinessConfig>();
        entityWrapper.like("DOC_TYPE", docType, SqlLike.RIGHT);//单据类型
        entityWrapper.eq("BIZ_TYPE", bizType);//业务类型
        entityWrapper.eq("STATUS", "Y");//处理标识
        entityWrapper.orderBy("ORDER_NO");

        EdiBusinessConfig ediBusinessConfig = ediBusinessConfigService.selectOne(entityWrapper);
        String tableService = ediBusinessConfig.getTableService();
        //表头service
        bscService = (IService) webApplicationContext.getBean(tableService);

        //判断优先级
        if (compareToManageResult()) {
            return PostActionEnum.SUCCESS_1;
        } else {
            return PostActionEnum.SUCCESS_2;
        }
    }

}



