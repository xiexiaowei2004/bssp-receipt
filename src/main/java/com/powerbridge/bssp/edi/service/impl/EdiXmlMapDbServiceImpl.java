package com.powerbridge.bssp.edi.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.edi.dao.EdiXmlMapDbMapper;
import com.powerbridge.bssp.edi.entity.EdiXmlMapDb;
import com.powerbridge.bssp.edi.service.IEdiXmlMapDbService;
import com.powerbridge.bssp.receipt.util.ReceiptKindConstant;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Service("ediXmlMapDbService")
public class EdiXmlMapDbServiceImpl extends BaseServiceImpl<EdiXmlMapDbMapper, EdiXmlMapDb> implements IEdiXmlMapDbService {


    /**
     * 调用：Map map=BeanUtil.getXmlToBean(sasDclBsc,ediXmlMapDbService);
     * 其中参数sasDclBsc为实体类，ediXmlMapDbService为固定的service值，不要传其他值
     * 根据参数Object来，查询出map，object为实体类的名字
     * key:就是xml的节点
     * value：就是对应的实体类的字段
     */
    public Map<String, Map> getXmlMapDb() throws Exception {
        Map<String, Map> tableMap = new HashMap<>();

        String tableName = "*";

        Map<String, String> eleMap = null;//新建map集合，存储数据
        //取到字段关系表中该通道所有数据，根据表名转为map
        EntityWrapper<EdiXmlMapDb> wrapper = new EntityWrapper<>();
        wrapper.eq("PASSAGEWAY", ReceiptKindConstant.PASSAGEWAY);// 通道
        wrapper.orderBy("DB_TABLE");


        List<EdiXmlMapDb> list = selectList(wrapper);
        for (EdiXmlMapDb ediXmlMapDb : list) {//XML_NAME  DB_COLUMN

            if (!tableName.equalsIgnoreCase(ediXmlMapDb.getDbTable())) {
                if (!"*".equals(tableName)) {
                    tableMap.put(tableName.toUpperCase(), eleMap);
                }
                eleMap = new Hashtable<>();
                eleMap.put(ediXmlMapDb.getXmlName(), ediXmlMapDb.getDbColumn());
                tableName = ediXmlMapDb.getDbTable();
            } else {
                eleMap.put(ediXmlMapDb.getXmlName(), ediXmlMapDb.getDbColumn());
            }
            tableMap.put(tableName.toUpperCase(), eleMap);
        }

        return tableMap;
    }

}   