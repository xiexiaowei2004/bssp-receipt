package com.powerbridge.bssp.receipt.util;

/**
 * @author Simon.xie
 * @version 1.0.0
 * @ClassName TransTypeEnum
 * @Description 动作状态
 * @Date 2017年6月4日 下午4:21:46
 */
public class TransTypeEnum {
    //S：发送
    public static final String S = "S";
    //R：接收
    public static final String R = "R";
    //生成
    public static final String C = "C";
    //处理
    public static final String D = "D";
    //已处理
    public static final String Y = "Y";
    //未处理
    public static final String N = "N";
    //已失败
    public static final String ONE = "1";
    //XML版本号
    public static final String CLASSVER = "1.0.0";

    public static final String EDI_ROUTING_ERROR = "获取路由信息失败！";

    public static final String EDI_INTODB_SUCCESS = "文件转入成功！";
    public static final String EDI_INTODB_ERROR = "文件转入异常！";

    public static final String EDI_SIMPLE_ERROR = "初步解析异常！";
    public static final String EDI_SIMPLE_SUCCESS = "初步解析成功！";

    public static final String EDI_DETAIL_ERROR = "处理回执异常！";
    public static final String EDI_DETAIL_SUCCESS = "处理回执成功！";


}
