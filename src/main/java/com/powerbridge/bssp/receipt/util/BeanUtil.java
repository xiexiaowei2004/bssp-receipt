package com.powerbridge.bssp.receipt.util;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：BeanUtil
 * 类描述：报文回执处理Bean工具类
 * 创建人：danagao
 * 创建时间：2017/6/9 12:00
 *
 * @version 1.0
 */
public class BeanUtil {

    /**
     * 获取一个Bean中所有能和数据库字段匹配的属性名称的map
     */
    public static Map getBeanMap(Object beanObject) {
        Class clazz = beanObject.getClass();
        Map BeanMap = new HashMap<String, String>();

        Field[] fs = clazz.getDeclaredFields();
        for (Field f : fs) {
            String sqlFiledName = getField(f);
            String beanName = f.getName();
            if (StringUtil.isNotEmpty(sqlFiledName)) {
                BeanMap.put(sqlFiledName, beanName);
            }
        }
        return BeanMap;
    }

    /**
     * 给bean中的某个属性赋值
     */
    public static void setProperty(Object object, String propertyName, Object propertyValue) throws Exception {
        Class clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        String methodName = "set" + propertyName;

        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(methodName)) {
                Class[] methodParamTypes = getMethodParamTypes(object, method.getName());
                method.invoke(object, getCastValue(methodParamTypes[0], String.valueOf(propertyValue), method.getName()));
            }
        }
    }


    /**
     * 根据Bean中属性注解的数据库字段名称获取对应的属性名称
     */
    private static String getField(Field f) {
        boolean present = f.isAnnotationPresent(TableField.class);
        if (present) {
            //得到requestMapping注释
            TableField tableField = f.getAnnotation(TableField.class);
            if (tableField != null) {
                return tableField.value();
            }
        }
        return "";
    }


    /**
     * 根据Bean类名获取对应的表名称
     */
    public static String getTableName(Class clazz) {
        boolean present = clazz.isAnnotationPresent(TableName.class);
        if (present) {
            //得到requestMapping注释
            TableName tableName = (TableName) clazz.getAnnotation(TableName.class);
            if (tableName != null) {
                return tableName.value();
            }
        }
        return "";
    }

    public static Object selectOne(Object tableService, EntityWrapper entityWrapper) throws Exception {
        Class clazz = tableService.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            if (method.getName().equals("selectOne")) {
                return method.invoke(tableService, entityWrapper);
            }
        }

        return null;
    }

    /**
     * 根据方法名称取得反射方法的参数类型(没有考虑同名重载方法使用时注意)
     *
     * @param classInstance 类实例
     * @param methodName    方法名
     * @return
     * @throws ClassNotFoundException
     */
    private static Class[] getMethodParamTypes(Object classInstance,
                                               String methodName) throws ClassNotFoundException {
        Class[] paramTypes = null;
        Method[] methods = classInstance.getClass().getMethods();//全部方法
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {//和传入方法名匹配
                Class[] params = method.getParameterTypes();
                paramTypes = new Class[params.length];
                for (int j = 0; j < params.length; j++) {
                    paramTypes[j] = Class.forName(params[j].getName());
                }
                break;
            }
        }
        return paramTypes;
    }

    public static String getMethodReturnType(Object classInstance, String propertyName) {
        Class clzz = classInstance.getClass();
        String returnType = "";
        String methodName = "get" + propertyName;
        //获取该类的所有方法
        Method[] methods = clzz.getMethods();
        //遍历方法
        for (Method m : methods) {
            if (m.getName().equalsIgnoreCase(methodName)) {
                //获取返回类型
                Type type = m.getGenericReturnType();
                returnType = type.getTypeName();
            }
        }
        return returnType;
    }


    private static Object getCastValue(Class clazz, String value, String str) {
        if (StringUtil.isEmpty(value) || "null".equals(value)) {
            return null;
        }
        String clazzName = clazz.getName();
        switch (clazzName) {
            case "java.math.BigDecimal":
                return new BigDecimal(value);
            case "java.lang.Integer":
                return Integer.parseInt(value);
            case "java.lang.Double":
                return Double.parseDouble(value);
        }

        return value;
    }

    /**
     * 空值设定
     *
     * @param str 字段类型
     * @param val 字段值
     * @return
     */
    public static String getNullTypeValue(String str, Object val) {
        if (str.equals("java.lang.Double") && val == null) {
            return "0";
        } else if (str.equals("java.lang.Integer") && val.equals("null")) {
            return "0";
        } else if (str.equals("java.math.BigDecimal") && val.equals("null")) {
            return "0";
        } else if (str.equals("java.util.Date") && val == null) {
            return DateUtil.now();
        } else if (str.equals("java.lang.String") && val.equals("null")) {
            return "";
        } else if (str.equals("java.lang.Double") && val.equals("null")) {
            val = 0.0;
        }
        return val.toString();
    }

}
