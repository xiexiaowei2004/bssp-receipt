package com.powerbridge.bssp.receipt.util;

import org.apache.commons.beanutils.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：DateConvertNew
 * 类描述：日期转化器
 * 创建人：danagao
 * 创建时间：2017/6/17 11:50
 *
 * @version 1.0
 */
class DateConvertNew implements Converter {

    @Override
    public Object convert(Class arg0, Object arg1) {
        String p = (String) arg1;
        if (p == null || p.trim().length() == 0) {
            return null;
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.parse(p.trim());
        } catch (Exception e) {
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                return df.parse(p.trim());
            } catch (ParseException ex) {
                return null;
            }
        }

    }

}

