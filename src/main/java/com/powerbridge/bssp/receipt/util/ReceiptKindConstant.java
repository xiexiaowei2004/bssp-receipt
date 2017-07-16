package com.powerbridge.bssp.receipt.util;

import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.receipt.entity.ManageResult;

import java.util.HashMap;
import java.util.Map;

public class ReceiptKindConstant {

    //回执类型1.内网（海关）2.外网（电子口岸），本项目固定为1
    public static final int PASSAGEWAY = 1;

    //监管场所，目前用来取路由表设置
    public static final String RECEIPTAREACODE = "490501";
    //账册单耗质疑/磋商/磋商记录回执<EMS212>
    public static final String BIZTYPE_EMS212 = "EMS212";
    //子报文入库回执<INV200>
    public static final String BIZTYPE_INV200 = "INV200";
    //子报文入库回执<BWS200>
    public static final String BIZTYPE_BWS200 = "BWS200";
    //子报文入库回执<SAS200>
    public static final String BIZTYPE_SAS200 = "SAS200";
    //子报文入库回执<EMS200>
    private static final String EDI_INTO_CUSTOM = "EMS200";
    //联网企业档案库审核回执<EMS201>
    private static final String BIZTYPE_EMS201 = "EMS201";
    //账册审核回执<EMS211>
    private static final String BIZTYPE_EMS211 = "EMS211";
    //账册核销审批回执<EMS221>
    private static final String BIZTYPE_EMS221 = "EMS221";
    //账册部分修改/暂停或者恢复回执<EMS241>
    private static final String BIZTYPE_EMS241 = "EMS241";
    //账册同步报文<EMS301>
    private static final String BIZTYPE_EMS301 = "EMS301";
    //清单审批回执<INV201>
    private static final String BIZTYPE_INV201 = "INV201";
    //清单记账回执<INV211>
    private static final String BIZTYPE_INV211 = "INV211";
    //耗料单审核回执<CMB201>
    private static final String BIZTYPE_CMB201 = "CMB201";
    //物流账册审核回执<BWS201>
    private static final String BIZTYPE_BWS201 = "BWS201";
    //业务申报表审核回执/回执补发送<SAS201>
    private static final String BIZTYPE_SAS201 = "SAS201";
    //申报表暂停恢复回执<SAS202>
    private static final String BIZTYPE_SAS202 = "SAS202";
    //业务申报表数据同步回执<SAS203>
    private static final String BIZTYPE_SAS203 = "SAS203";
    //出入库单审核回执<SAS211>
    private static final String BIZTYPE_SAS211 = "SAS211";
    //出入库单修改回执<SAS212>
    private static final String BIZTYPE_SAS212 = "SAS212";
    //出入库单数据同步回执<SAS213>
    private static final String BIZTYPE_SAS213 = "SAS213";
    //核放单审核回执/回执补发送<SAS221>
    private static final String BIZTYPE_SAS221 = "SAS221";
    //核放单修改回执<SAS222>
    private static final String BIZTYPE_SAS222 = "SAS222";
    //核放单过卡回执<SAS223>
    private static final String BIZTYPE_SAS223 = "SAS223";
    //车辆信息备案/变更审核回执<SAS231>
    private static final String BIZTYPE_SAS231 = "SAS231";
    private static final Map<String, ManageResult> manageResultMap = new HashMap<>();
    //子报文入库回执类型对照表
    private static final Map resultTypeMap = new HashMap<String, String>();
    //
    private static final Map docTypeMap = new HashMap<String, Doctype>();
    private static ReceiptKindConstant receiptKindConstant = new ReceiptKindConstant();

    private ReceiptKindConstant() {

        //档案库审核回执
        docTypeMap.put(BIZTYPE_EMS201, new Doctype("A0302", "EMS101"));

        //账册审核回执
        docTypeMap.put(BIZTYPE_EMS211, new Doctype("A0401", "EMS111"));

        //账册核销审批回执
        docTypeMap.put(BIZTYPE_EMS221, new Doctype("A0801", "EMS121"));

        //账册部分修改/暂停或者恢复回执<EMS241>
        docTypeMap.put(BIZTYPE_EMS241, new Doctype("A0401", "EMS111"));

        //账册同步报文<EMS301>
        docTypeMap.put(BIZTYPE_EMS301, new Doctype("A0401", "EMS111"));

        //清单审批回执<INV201>
        docTypeMap.put(BIZTYPE_INV201, new Doctype("A0601", "INV101"));

        //清单记账回执<INV211>
        docTypeMap.put(BIZTYPE_INV211, new Doctype("A0601", "INV101"));

        //物流账册审核回执<BWS201>
        docTypeMap.put(BIZTYPE_BWS201, new Doctype("A0402", "BWS101"));

        //业务申报表审核回执/回执补发送<SAS201>
        docTypeMap.put(BIZTYPE_SAS201, new Doctype("A0501", "SAS101"));

        //申报表暂停恢复回执<SAS202>
        docTypeMap.put(BIZTYPE_SAS202, new Doctype("A0501", "SAS101"));

        //业务申报表数据同步回执<SAS203>
        docTypeMap.put(BIZTYPE_SAS203, new Doctype("A0501", "SAS101"));

        //出入库单审核回执<SAS211>
        docTypeMap.put(BIZTYPE_SAS211, new Doctype("A0502", "SAS111"));

        //出入库单修改回执<SAS212>
        docTypeMap.put(BIZTYPE_SAS212, new Doctype("A0502", "SAS111"));

        //出入库单数据同步回执<SAS213>
        docTypeMap.put(BIZTYPE_SAS213, new Doctype("A0502", "SAS111"));

        //核放单审核回执/回执补发送<SAS221>
        docTypeMap.put(BIZTYPE_SAS221, new Doctype("A0503", "SAS121"));

        //核放单修改回执<SAS222>
        docTypeMap.put(BIZTYPE_SAS222, new Doctype("A0503", "SAS121"));

        //核放单过卡回执<SAS223>
        docTypeMap.put(BIZTYPE_SAS223, new Doctype("A0503", "SAS121"));

        //车辆信息备案/变更审核回执<SAS231>
        docTypeMap.put(BIZTYPE_SAS231, new Doctype("A0504", "SAS131"));


        /*-----------------------------------------------------------------------------*/

        resultTypeMap.put(EDI_INTO_CUSTOM + "_1", BIZTYPE_EMS201);//联网企业档案库审核回执
        resultTypeMap.put(EDI_INTO_CUSTOM + "_2", BIZTYPE_EMS211);//联网监管电子账册入库回执
        resultTypeMap.put(EDI_INTO_CUSTOM + "_3", BIZTYPE_EMS221);//联网监管电子账册核销入库回执
        resultTypeMap.put(EDI_INTO_CUSTOM + "_4", BIZTYPE_INV201);//核注清单入库回执
        resultTypeMap.put(EDI_INTO_CUSTOM + "_5", BIZTYPE_CMB201);//耗料单入库回执
        resultTypeMap.put(EDI_INTO_CUSTOM + "_6", BIZTYPE_BWS201);//物流账册入库回执
        resultTypeMap.put(EDI_INTO_CUSTOM + "_7", BIZTYPE_SAS201);//业务申报表入库回执
        resultTypeMap.put(EDI_INTO_CUSTOM + "_8", BIZTYPE_SAS211);//出入库单入库回执
        resultTypeMap.put(EDI_INTO_CUSTOM + "_9", BIZTYPE_SAS221);//核放单入库回执
        resultTypeMap.put(EDI_INTO_CUSTOM + "_10", BIZTYPE_SAS231);//车辆信息入库回执

      /*-----------------------------------------------------------------------------*/


        manageResultMap.put("SAS201-1", new ManageResult(ChkStatusConstant.CHK_STATUS_P, "1-通过", 3));
        manageResultMap.put("SAS201-2", new ManageResult(null, "2-转人工", 2));
        manageResultMap.put("SAS201-3", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "3-退单", 2));
        manageResultMap.put("SAS201-Y", new ManageResult(null, "Y-入库成功", 1));
        manageResultMap.put("SAS201-Z", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "Z-入库失败", 1));


        manageResultMap.put("SAS211-1", new ManageResult(ChkStatusConstant.CHK_STATUS_P, "1-通过", 3));
        manageResultMap.put("SAS211-2", new ManageResult(null, "2-转人工", 2));
        manageResultMap.put("SAS211-3", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "3-退单", 2));
        manageResultMap.put("SAS211-Y", new ManageResult(null, "Y-入库成功", 1));
        manageResultMap.put("SAS211-Z", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "Z-入库失败", 1));

        manageResultMap.put("SAS221-1", new ManageResult(ChkStatusConstant.CHK_STATUS_P, "1-通过", 3));
        manageResultMap.put("SAS221-2", new ManageResult(null, "2-转人工", 2));
        manageResultMap.put("SAS221-3", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "3-退单", 2));
        manageResultMap.put("SAS221-Y", new ManageResult(null, "Y-入库成功", 1));
        manageResultMap.put("SAS221-Z", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "Z-入库失败", 1));

        manageResultMap.put("SAS223-1", new ManageResult(ChkStatusConstant.CHK_STATUS_P, "1-已过卡", 1));
        manageResultMap.put("SAS223-2", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "2-未过卡", 1));

        manageResultMap.put("SAS231-1", new ManageResult(ChkStatusConstant.CHK_STATUS_P, "1-通过", 3));
        manageResultMap.put("SAS231-2", new ManageResult(null, "2-转人工", 2));
        manageResultMap.put("SAS231-3", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "3-退单", 2));
        manageResultMap.put("SAS231-Y", new ManageResult(null, "Y-入库成功", 1));
        manageResultMap.put("SAS231-Z", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "Z-入库失败", 1));

        manageResultMap.put("EMS201-1", new ManageResult(ChkStatusConstant.CHK_STATUS_P, "1-通过", 3));
        manageResultMap.put("EMS201-2", new ManageResult(null, "2-转人工", 2));
        manageResultMap.put("EMS201-3", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "3-退单", 2));
        manageResultMap.put("EMS201-Y", new ManageResult(null, "Y-入库成功", 1));
        manageResultMap.put("EMS201-Z", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "Z-入库失败", 1));

        manageResultMap.put("EMS211-1", new ManageResult(ChkStatusConstant.CHK_STATUS_P, "1-通过", 3));
        manageResultMap.put("EMS211-2", new ManageResult(null, "2-转人工", 2));
        manageResultMap.put("EMS211-3", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "3-退单", 2));
        manageResultMap.put("EMS211-Y", new ManageResult(null, "Y-入库成功", 1));
        manageResultMap.put("EMS211-Z", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "Z-入库失败", 1));

        manageResultMap.put("EMS212-Y", new ManageResult(null, "Y-入库成功", 1));
        manageResultMap.put("EMS212-Z", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "Z-入库失败", 1));

        manageResultMap.put("INV201-1", new ManageResult(ChkStatusConstant.CHK_STATUS_P, "1-通过", 3));
        manageResultMap.put("INV201-2", new ManageResult(null, "2-转人工", 2));
        manageResultMap.put("INV201-3", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "3-退单", 2));
        manageResultMap.put("INV201-Y", new ManageResult(null, "Y-入库成功", 1));
        manageResultMap.put("INV201-Z", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "Z-入库失败", 1));

        manageResultMap.put("CMB201-1", new ManageResult(ChkStatusConstant.CHK_STATUS_P, "1-通过", 3));
        manageResultMap.put("CMB201-2", new ManageResult(null, "2-转人工", 2));
        manageResultMap.put("CMB201-3", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "3-退单", 2));
        manageResultMap.put("CMB201-Y", new ManageResult(null, "Y-入库成功", 1));
        manageResultMap.put("CMB201-Z", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "Z-入库失败", 1));

        manageResultMap.put("BWS201-1", new ManageResult(ChkStatusConstant.CHK_STATUS_P, "1-通过", 3));
        manageResultMap.put("BWS201-2", new ManageResult(null, "2-转人工", 2));
        manageResultMap.put("BWS201-3", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "3-退单", 2));
        manageResultMap.put("BWS201-Y", new ManageResult(null, "Y-入库成功", 1));
        manageResultMap.put("BWS201-Z", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "Z-入库失败", 1));

        manageResultMap.put("EMS221-2", new ManageResult(ChkStatusConstant.CHK_STATUS_P, "2-报核通过", 6));
        manageResultMap.put("EMS221-3", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "3-报核退单", 6));
        manageResultMap.put("EMS221-5", new ManageResult(null, "5-正式核算通过", 3));
        manageResultMap.put("EMS221-6", new ManageResult(null, "6-正式核算退单", 3));
        manageResultMap.put("EMS221-7", new ManageResult(null, "7-差异结果确认", 5));
        manageResultMap.put("EMS221-A", new ManageResult(null, "A-报核转人工确认", 4));
        manageResultMap.put("EMS221-B", new ManageResult(null, "B-正式核算转人工确认", 2));
        manageResultMap.put("EMS221-Y", new ManageResult(null, "Y-入库成功", 1));
        manageResultMap.put("EMS221-Z", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "Z-入库失败", 1));


        manageResultMap.put("BWS200-1", new ManageResult(null, "1-入库成功", 1));
        manageResultMap.put("BWS200-2", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "2-入库失败", 1));

        manageResultMap.put("EMS200-1", new ManageResult(null, "1-入库成功", 1));
        manageResultMap.put("EMS200-2", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "2-入库失败", 1));

        manageResultMap.put("INV200-1", new ManageResult(null, "1-入库成功", 1));
        manageResultMap.put("INV200-2", new ManageResult(ChkStatusConstant.CHK_STATUS_S, "2-入库失败", 1));

    }

    public static String getDocType(String receiptBizType) {
        return ((Doctype) docTypeMap.get(receiptBizType)).getDocType();
    }

    public static String getBizType(String receiptBizType) {
        return ((Doctype) docTypeMap.get(receiptBizType)).getBizType();
    }

    public static String getDocType(String receiptBizType, String resutType) {
        return ((Doctype) docTypeMap.get(resultTypeMap.get(receiptBizType + "_" + resutType))).getDocType();
    }

    public static String getBizType(String receiptBizType, String resutType) {
        return ((Doctype) docTypeMap.get(resultTypeMap.get(receiptBizType + "_" + resutType))).getBizType();
    }

    /**
     * 注意传入参数为 BWS201=key1  1=key2 自动合成    BWS201-1
     * key2的值可以为空
     *
     * @Title: getManageResult
     * @Description: TODO
     * @param: @param key
     * @param: @return
     * @return: String
     */
    public static ManageResult getManageResult(String bizType, String result) {
        if (StringUtil.isEmpty(result)) {
            return null;
        }
        return manageResultMap.get(bizType + "-" + result);
    }

    class Doctype {
        private String docType;
        private String bizType;

        public Doctype(String docType, String bizType) {
            this.docType = docType;
            this.bizType = bizType;
        }

        public String getDocType() {
            return docType;
        }

        public void setDocType(String docType) {
            this.docType = docType;
        }

        public String getBizType() {
            return bizType;
        }

        public void setBizType(String bizType) {
            this.bizType = bizType;
        }
    }
}
