package com.powerbridge.bssp.receipt.util;

import org.apache.commons.beanutils.ConvertUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

/**
 * @author wangzs
 * @Title 支持对BigDecimal类null值得copy
 * @Description
 * @date 2017-1-5
 */
public class BeanUtilsEx extends org.apache.commons.beanutils.BeanUtils {
    static {
        ConvertUtils.register(new DateConvertNew(), java.util.Date.class);
        ConvertUtils.register(new DateConvertNew(), java.sql.Date.class);
        ConvertUtils.register(new BigDecimalConverterNew(), BigDecimal.class);
    }

    public static void copyProperties(Object target, Object source) {
        // 支持对BigDecimal类null值得copy
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);
        } catch (IllegalAccessException | InvocationTargetException e) {
            ReceiptMsg3.getInstance().publishError(e.toString());
            e.printStackTrace();
        }
    }


}
