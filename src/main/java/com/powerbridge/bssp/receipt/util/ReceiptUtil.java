package com.powerbridge.bssp.receipt.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：ReceiptUtil
 * 类描述：菜单控制器
 * 创建人：danagao
 * 创建时间：2017/6/12 11:08
 *
 * @version 1.0
 */
public class ReceiptUtil {

    private static Map<String, Map> tableMap;

    public static Map<String, Map> getTableMap() {
        return tableMap;
    }

    public static void setTableMap(Map<String, Map> tableMap) {
        ReceiptUtil.tableMap = tableMap;
    }

    public static Map<String, String> getXmlMapBean(Object obj) throws Exception {
        String nameTable = BeanUtil.getTableName(obj.getClass()).toUpperCase();//获取数据库表名sas_dcl_bsc

//        Map<String, Map> tableMap = getXmlMapDb();//获取xmlMapDb全表map

        Map<String, String> myTableMap = tableMap.get(nameTable);//获取该表中xml与field对照map

        Map<String, String> fieldbeanMap = BeanUtil.getBeanMap(obj);//{INPUT_COP_NAME=inputCopName, CHK_STATUS=chkStatus,...}


        Map<String, String> xmlMapBean = new HashMap<>();

        for (String key : myTableMap.keySet()) {
            xmlMapBean.put(key, fieldbeanMap.get(myTableMap.get(key)));
        }

        return xmlMapBean;

    }

}
