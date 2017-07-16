package com.powerbridge.bssp.sas.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 车辆入区登记表
 *
 * @pdOid 63874752-464d-4fa6-b471-00b16605e815
 */
@TableName(value = "sas_vehicle_i_reg")
public class SasVehicleIReg implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     *
     * @pdOid 07406374-8700-4a21-8892-0d653d6c7ad2
     */
    @TableId(value = "UID")
    private String uid;
    /**
     * 车牌号
     *
     * @pdOid 68985f4b-de86-49ca-9c6c-4547ebc667c8
     */
    @TableField(value = "VEHICLE_NO")
    private String vehicleNo;
    /**
     * 车牌类型
     *
     * @pdOid ab6591fb-a2ef-4134-981d-daa22362290a
     */
    @TableField(value = "PLATE_TYPECD")
    private String plateTypecd;
    /**
     * 车辆类型
     *
     * @pdOid 08a29fbb-0791-432d-bfca-6ee08b3e7e52
     */
    @TableField(value = "VEHICLE_TYPECD")
    private String vehicleTypecd;
    /**
     * 通关业务类型
     *
     * @pdOid a56d4d42-4257-4108-8fcb-880e7013c85d
     */
    @TableField(value = "ClEARANCE_TYPE")
    private String clEARANCEType;
    /**
     * 车辆入区登记编号
     *
     * @pdOid 1055af27-e5e4-4945-bfd9-9c17a0c9981a
     */
    @TableField(value = "VEHICLE_I_REGNO")
    private String vehicleIRegno;
    /**
     * 车辆备案标识
     *
     * @pdOid 0319d7a0-75d5-4880-9a87-130ff0d93985
     */
    @TableField(value = "VEHICLE_REG_FLAG")
    private String vehicleRegFlag;
    /**
     * 车辆自重
     *
     * @pdOid a58db532-7f5f-498a-8246-31f473f42d03
     */
    @TableField(value = "VEHICLE_WT")
    private double vehicleWt;
    /**
     * 电子车牌
     *
     * @pdOid fa8bebbc-7814-4d77-abba-919173328c2b
     */
    @TableField(value = "VEHICLE_IC_NO")
    private String vehicleIcNo;
    /**
     * IC卡编号
     *
     * @pdOid 9f88ed93-df22-48ca-b9ed-e8d4cad09700
     */
    @TableField(value = "IC_NO")
    private String icNo;
    /**
     * IC卡类型
     *
     * @pdOid d3ad26e1-5fba-4224-ba32-5046aee37abd
     */
    @TableField(value = "IC_TYPECD")
    private String icTypecd;
    /**
     * 运输类型代码
     *
     * @pdOid ca1107cb-16d0-40d9-a311-ec1a76c550f8
     */
    @TableField(value = "TRANS_TYPECD")
    private String transTypecd;
    /**
     * 入区登记日期
     *
     * @pdOid 061182b1-1206-468c-855f-1f9f9dcb4f47
     */
    @TableField(value = "I_REG_DATE")
    private String iRegString;
    /**
     * 本次有效日期
     *
     * @pdOid c62c491b-e71a-4e83-92f5-41a6360abca0
     */
    @TableField(value = "THIS_VALIDATE_DATE")
    private String thisValiStringString;
    /**
     * 入区运输单类型代码
     *
     * @pdOid c3afd925-6d66-46e9-a833-9499f79ef335
     */
    @TableField(value = "I_TRANS_TYPE_CODE")
    private String iTransTypeCode;
    /**
     * 入区运输单编号
     *
     * @pdOid 3c2ed754-2226-40cb-8cf2-1cc4de4f3b21
     */
    @TableField(value = "I_TRANS_NO")
    private String iTransNo;
    /**
     * 入区运输单编号1
     *
     * @pdOid 1cfae249-88aa-4b6d-9d47-eb3ce4e82eb0
     */
    @TableField(value = "I_TRANS_NO1")
    private String iTransNo1;
    /**
     * 入区车架编号
     *
     * @pdOid 0caacbcc-c6b6-4095-9d4c-2087453b7d26
     */
    @TableField(value = "I_VEHICLE_FRAME_NO")
    private String iVehicleFrameNo;
    /**
     * 入区车架重量
     *
     * @pdOid fbf288d6-0889-458f-9a31-abad5fa295bc
     */
    @TableField(value = "I_VEHICLE_FRAME_WT")
    private double iVehicleFrameWt;
    /**
     * 入区区内企业代码
     *
     * @pdOid 762c226f-c0df-44c8-b5eb-04830982d297
     */
    @TableField(value = "I_AREAIN_ETPSNO")
    private String iAreainEtpsno;
    /**
     * 入区场地代码
     *
     * @pdOid 208ecd4b-baff-488e-83fd-c62858549e4e
     */
    @TableField(value = "I_AREA_CODE")
    private String iAreaCode;
    /**
     * 入区企业备案号
     *
     * @pdOid 919bc216-6ee4-4cab-9411-91d78be02fbf
     */
    @TableField(value = "I_COP_ENT_NO")
    private String iCopEntNo;
    /**
     * 入区流向
     *
     * @pdOid 209a163f-8868-49d1-afe4-64fd0d4c638a
     */
    @TableField(value = "I_FLOW")
    private String iFlow;
    /**
     * 入区核放单审批标识
     *
     * @pdOid 91d716da-7bdb-4ae5-b2d8-12bb21fc2cfe
     */
    @TableField(value = "I_CHK_EMAPV_MARKCD")
    private String iChkEmapvMarkcd;
    /**
     * 收卡时间
     *
     * @pdOid d938f9da-c6a1-4b23-8932-ea83d1345761
     */
    @TableField(value = "IC_RECYCE_TIME")
    private String icRecyceTime;
    /**
     * 出区运输单类型代码
     *
     * @pdOid ec0c085d-fdca-4c03-a32f-53ce21e308e6
     */
    @TableField(value = "E_TRANS_TYPE")
    private String eTransType;
    /**
     * 出区运输单编号
     *
     * @pdOid fc532dc1-781c-4beb-b9ad-c83eea4ff48a
     */
    @TableField(value = "E_TRANS_NO")
    private String eTransNo;
    /**
     * 出区运输单编号1
     *
     * @pdOid 164aed2c-f1b9-4340-8613-201e57e6ed94
     */
    @TableField(value = "E_TRANS_NO1")
    private String eTransNo1;
    /**
     * 出区车架重量
     *
     * @pdOid dcd188f5-5e3e-47b0-a338-102bc4c494a1
     */
    @TableField(value = "E_VEHICLE_FRAME_WT")
    private double eVehicleFrameWt;
    /**
     * 出区区内企业代码
     *
     * @pdOid 53967188-41be-440d-9546-a98382fd94a1
     */
    @TableField(value = "E_AREAIN_ETPSNO")
    private String eAreainEtpsno;
    /**
     * 出区场地代码
     *
     * @pdOid 7919ff6f-c1f6-4910-84b1-20e54a29b8f0
     */
    @TableField(value = "E_AREA_CODE")
    private String eAreaCode;
    /**
     * 出区企业备案号
     *
     * @pdOid 4b9b0195-9990-41f1-bab9-d163ae094b2f
     */
    @TableField(value = "EI_COP_ENT_NO")
    private String eiCopEntNo;
    /**
     * 出区流向
     *
     * @pdOid 00e8efa8-3553-40f7-80af-1ab626ba7e47
     */
    @TableField(value = "E_FLOW")
    private String eFlow;
    /**
     * 出区核放单审批标识
     *
     * @pdOid d2c2278e-6c40-47c5-82ee-e91b456f5dad
     */
    @TableField(value = "E_CHK_EMAPV_MARKCD")
    private String eChkEmapvMarkcd;
    /**
     * 作业单标识代码
     *
     * @pdOid 43b2042b-2b05-40d7-972b-d21d5b63b3a7
     */
    @TableField(value = "WORK_FLAG_CODE")
    private String workFlagCode;
    /**
     * 备注
     *
     * @pdOid b5b55ad1-ca22-44aa-9804-59084c547260
     */
    @TableField(value = "RMK")
    private String rmk;
    /**
     * 主管关区
     *
     * @pdOid 760778d2-7674-4e21-88a1-bf5608e5fba2
     */
    @TableField(value = "MASTER_CUSCD")
    private String masterCuscd;
    /**
     * 场地代码
     *
     * @pdOid 27da0108-4233-4e13-bdeb-d8e90f48885e
     */
    @TableField(value = "AREA_CODE")
    private String areaCode;
    /**
     * 业务类型
     *
     * @pdOid 6f62b294-7dba-48e3-bc00-5ed055131d3d
     */
    @TableField(value = "BIZ_TYPE")
    private String bizType;
    /**
     * 单据状态
     *
     * @pdOid abade051-e517-4896-9ca7-4799e3d998ae
     */
    @TableField(value = "CHK_STATUS")
    private String chkStatus;
    /**
     * 单据类别
     *
     * @pdOid 924031c9-6242-4da2-b704-2a776aac2216
     */
    @TableField(value = "DOC_TYPE")
    private String docType;
    /**
     * 回执状态
     *
     * @pdOid c47f1f4f-f7d3-4b6f-95b7-1325e5563ff9
     */
    @TableField(value = "RET_CHANNEL")
    private String retChannel;
    /**
     * 录入日期
     *
     * @pdOid 9de48674-8369-4c96-9319-0e538af4664d
     */
    @TableField(value = "DEC_TIME")
    private String decTime;
    /**
     * 审批时间
     *
     * @pdOid f22c4e04-f164-491b-a91b-bf9bcedc7bcf
     */
    @TableField(value = "CHK_TIME")
    private String chkTime;
    /**
     * 单据编号
     *
     * @pdOid 8ae777c3-dbff-4c40-8e5c-04668932e81c
     */
    @TableField(value = "SEQ_NO")
    private String seqNo;
    /**
     * 申报方式(备案/变更)
     *
     * @pdOid 7231940b-0730-4d99-958b-d53589493bb0
     */
    @TableField(value = "DECLARE_MARK")
    private String declareMark;
    /**
     * 申报（录入）人代码
     *
     * @pdOid bae7abb0-2920-4b4b-aa53-fb83e52239d7
     */
    @TableField(value = "INPUTER_CODE")
    private String inputerCode;
    /**
     * 申报（录入）人名称
     *
     * @pdOid fa126388-a93e-4e7a-90d4-fcbcd124ab02
     */
    @TableField(value = "INPUTER_NAME")
    private String inputerName;
    /**
     * 申报（录入）企业代码
     *
     * @pdOid cd731986-80e8-40c5-9a92-c167fb72cd7b
     */
    @TableField(value = "INPUT_COP_NO")
    private String inputCopNo;
    /**
     * 申报（录入）企业名称
     *
     * @pdOid 68358cce-840a-4774-bf59-60906e5a334e
     */
    @TableField(value = "INPUT_COP_NAME")
    private String inputCopName;
    /**
     * 创建人代码
     *
     * @pdOid 5a52056f-c7cb-4c52-a0b0-6d5665a2cd0a
     */
    @TableField(value = "CREATE_BY")
    private String createBy;
    /**
     * 创建人名称
     *
     * @pdOid c8e2634a-e0af-4525-bd38-f627f074206a
     */
    @TableField(value = "CREATE_NAME")
    private String createName;
    /**
     * 创建时间
     *
     * @pdOid 71aa8bdb-5cbb-442d-a883-debfaa33d8f9
     */
    @TableField(value = "CREATE_TIME")
    private String createTime;
    /**
     * 修改人代码
     *
     * @pdOid 0ceaba45-1ed4-4941-87e0-58f55b106b2e
     */
    @TableField(value = "UPDATE_BY")
    private String upStringBy;
    /**
     * 修改人名称
     *
     * @pdOid f72cc36f-e749-4d8b-b6bb-4c29f5189bd0
     */
    @TableField(value = "UPDATE_NAME")
    private String upStringName;
    /**
     * 修改时间
     *
     * @pdOid dc6bc2e8-d662-46fa-ae5c-a4bc76a596d7
     */
    @TableField(value = "UPDATE_TIME")
    private String upStringTime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getPlateTypecd() {
        return plateTypecd;
    }

    public void setPlateTypecd(String plateTypecd) {
        this.plateTypecd = plateTypecd;
    }

    public String getVehicleTypecd() {
        return vehicleTypecd;
    }

    public void setVehicleTypecd(String vehicleTypecd) {
        this.vehicleTypecd = vehicleTypecd;
    }

    public String getClEARANCEType() {
        return clEARANCEType;
    }

    public void setClEARANCEType(String clEARANCEType) {
        this.clEARANCEType = clEARANCEType;
    }

    public String getVehicleIRegno() {
        return vehicleIRegno;
    }

    public void setVehicleIRegno(String vehicleIRegno) {
        this.vehicleIRegno = vehicleIRegno;
    }

    public String getVehicleRegFlag() {
        return vehicleRegFlag;
    }

    public void setVehicleRegFlag(String vehicleRegFlag) {
        this.vehicleRegFlag = vehicleRegFlag;
    }

    public double getVehicleWt() {
        return vehicleWt;
    }

    public void setVehicleWt(double vehicleWt) {
        this.vehicleWt = vehicleWt;
    }

    public String getVehicleIcNo() {
        return vehicleIcNo;
    }

    public void setVehicleIcNo(String vehicleIcNo) {
        this.vehicleIcNo = vehicleIcNo;
    }

    public String getIcNo() {
        return icNo;
    }

    public void setIcNo(String icNo) {
        this.icNo = icNo;
    }

    public String getIcTypecd() {
        return icTypecd;
    }

    public void setIcTypecd(String icTypecd) {
        this.icTypecd = icTypecd;
    }

    public String getTransTypecd() {
        return transTypecd;
    }

    public void setTransTypecd(String transTypecd) {
        this.transTypecd = transTypecd;
    }

    public String getiRegString() {
        return iRegString;
    }

    public void setiRegString(String iRegString) {
        this.iRegString = iRegString;
    }

    public String getThisValiStringString() {
        return thisValiStringString;
    }

    public void setThisValiStringString(String thisValiStringString) {
        this.thisValiStringString = thisValiStringString;
    }

    public String getiTransTypeCode() {
        return iTransTypeCode;
    }

    public void setiTransTypeCode(String iTransTypeCode) {
        this.iTransTypeCode = iTransTypeCode;
    }

    public String getiTransNo() {
        return iTransNo;
    }

    public void setiTransNo(String iTransNo) {
        this.iTransNo = iTransNo;
    }

    public String getiTransNo1() {
        return iTransNo1;
    }

    public void setiTransNo1(String iTransNo1) {
        this.iTransNo1 = iTransNo1;
    }

    public String getiVehicleFrameNo() {
        return iVehicleFrameNo;
    }

    public void setiVehicleFrameNo(String iVehicleFrameNo) {
        this.iVehicleFrameNo = iVehicleFrameNo;
    }

    public double getiVehicleFrameWt() {
        return iVehicleFrameWt;
    }

    public void setiVehicleFrameWt(double iVehicleFrameWt) {
        this.iVehicleFrameWt = iVehicleFrameWt;
    }

    public String getiAreainEtpsno() {
        return iAreainEtpsno;
    }

    public void setiAreainEtpsno(String iAreainEtpsno) {
        this.iAreainEtpsno = iAreainEtpsno;
    }

    public String getiAreaCode() {
        return iAreaCode;
    }

    public void setiAreaCode(String iAreaCode) {
        this.iAreaCode = iAreaCode;
    }

    public String getiCopEntNo() {
        return iCopEntNo;
    }

    public void setiCopEntNo(String iCopEntNo) {
        this.iCopEntNo = iCopEntNo;
    }

    public String getiFlow() {
        return iFlow;
    }

    public void setiFlow(String iFlow) {
        this.iFlow = iFlow;
    }

    public String getiChkEmapvMarkcd() {
        return iChkEmapvMarkcd;
    }

    public void setiChkEmapvMarkcd(String iChkEmapvMarkcd) {
        this.iChkEmapvMarkcd = iChkEmapvMarkcd;
    }

    public String getIcRecyceTime() {
        return icRecyceTime;
    }

    public void setIcRecyceTime(String icRecyceTime) {
        this.icRecyceTime = icRecyceTime;
    }

    public String geteTransType() {
        return eTransType;
    }

    public void seteTransType(String eTransType) {
        this.eTransType = eTransType;
    }

    public String geteTransNo() {
        return eTransNo;
    }

    public void seteTransNo(String eTransNo) {
        this.eTransNo = eTransNo;
    }

    public String geteTransNo1() {
        return eTransNo1;
    }

    public void seteTransNo1(String eTransNo1) {
        this.eTransNo1 = eTransNo1;
    }

    public double geteVehicleFrameWt() {
        return eVehicleFrameWt;
    }

    public void seteVehicleFrameWt(double eVehicleFrameWt) {
        this.eVehicleFrameWt = eVehicleFrameWt;
    }

    public String geteAreainEtpsno() {
        return eAreainEtpsno;
    }

    public void seteAreainEtpsno(String eAreainEtpsno) {
        this.eAreainEtpsno = eAreainEtpsno;
    }

    public String geteAreaCode() {
        return eAreaCode;
    }

    public void seteAreaCode(String eAreaCode) {
        this.eAreaCode = eAreaCode;
    }

    public String getEiCopEntNo() {
        return eiCopEntNo;
    }

    public void setEiCopEntNo(String eiCopEntNo) {
        this.eiCopEntNo = eiCopEntNo;
    }

    public String geteFlow() {
        return eFlow;
    }

    public void seteFlow(String eFlow) {
        this.eFlow = eFlow;
    }

    public String geteChkEmapvMarkcd() {
        return eChkEmapvMarkcd;
    }

    public void seteChkEmapvMarkcd(String eChkEmapvMarkcd) {
        this.eChkEmapvMarkcd = eChkEmapvMarkcd;
    }

    public String getWorkFlagCode() {
        return workFlagCode;
    }

    public void setWorkFlagCode(String workFlagCode) {
        this.workFlagCode = workFlagCode;
    }

    public String getRmk() {
        return rmk;
    }

    public void setRmk(String rmk) {
        this.rmk = rmk;
    }

    public String getMasterCuscd() {
        return masterCuscd;
    }

    public void setMasterCuscd(String masterCuscd) {
        this.masterCuscd = masterCuscd;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getChkStatus() {
        return chkStatus;
    }

    public void setChkStatus(String chkStatus) {
        this.chkStatus = chkStatus;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getRetChannel() {
        return retChannel;
    }

    public void setRetChannel(String retChannel) {
        this.retChannel = retChannel;
    }

    public String getDecTime() {
        return decTime;
    }

    public void setDecTime(String decTime) {
        this.decTime = decTime;
    }

    public String getChkTime() {
        return chkTime;
    }

    public void setChkTime(String chkTime) {
        this.chkTime = chkTime;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getDeclareMark() {
        return declareMark;
    }

    public void setDeclareMark(String declareMark) {
        this.declareMark = declareMark;
    }

    public String getInputerCode() {
        return inputerCode;
    }

    public void setInputerCode(String inputerCode) {
        this.inputerCode = inputerCode;
    }

    public String getInputerName() {
        return inputerName;
    }

    public void setInputerName(String inputerName) {
        this.inputerName = inputerName;
    }

    public String getInputCopNo() {
        return inputCopNo;
    }

    public void setInputCopNo(String inputCopNo) {
        this.inputCopNo = inputCopNo;
    }

    public String getInputCopName() {
        return inputCopName;
    }

    public void setInputCopName(String inputCopName) {
        this.inputCopName = inputCopName;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpStringBy() {
        return upStringBy;
    }

    public void setUpStringBy(String upStringBy) {
        this.upStringBy = upStringBy;
    }

    public String getUpStringName() {
        return upStringName;
    }

    public void setUpStringName(String upStringName) {
        this.upStringName = upStringName;
    }

    public String getUpStringTime() {
        return upStringTime;
    }

    public void setUpStringTime(String upStringTime) {
        this.upStringTime = upStringTime;
    }

    @Override
    public String toString() {
        return "SasVehicleIReg2{" +
                "uid='" + uid + '\'' +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", plateTypecd='" + plateTypecd + '\'' +
                ", vehicleTypecd='" + vehicleTypecd + '\'' +
                ", clEARANCEType='" + clEARANCEType + '\'' +
                ", vehicleIRegno='" + vehicleIRegno + '\'' +
                ", vehicleRegFlag='" + vehicleRegFlag + '\'' +
                ", vehicleWt=" + vehicleWt +
                ", vehicleIcNo='" + vehicleIcNo + '\'' +
                ", icNo='" + icNo + '\'' +
                ", icTypecd='" + icTypecd + '\'' +
                ", transTypecd='" + transTypecd + '\'' +
                ", iRegString='" + iRegString + '\'' +
                ", thisValiStringString='" + thisValiStringString + '\'' +
                ", iTransTypeCode='" + iTransTypeCode + '\'' +
                ", iTransNo='" + iTransNo + '\'' +
                ", iTransNo1='" + iTransNo1 + '\'' +
                ", iVehicleFrameNo='" + iVehicleFrameNo + '\'' +
                ", iVehicleFrameWt=" + iVehicleFrameWt +
                ", iAreainEtpsno='" + iAreainEtpsno + '\'' +
                ", iAreaCode='" + iAreaCode + '\'' +
                ", iCopEntNo='" + iCopEntNo + '\'' +
                ", iFlow='" + iFlow + '\'' +
                ", iChkEmapvMarkcd='" + iChkEmapvMarkcd + '\'' +
                ", icRecyceTime='" + icRecyceTime + '\'' +
                ", eTransType='" + eTransType + '\'' +
                ", eTransNo='" + eTransNo + '\'' +
                ", eTransNo1='" + eTransNo1 + '\'' +
                ", eVehicleFrameWt=" + eVehicleFrameWt +
                ", eAreainEtpsno='" + eAreainEtpsno + '\'' +
                ", eAreaCode='" + eAreaCode + '\'' +
                ", eiCopEntNo='" + eiCopEntNo + '\'' +
                ", eFlow='" + eFlow + '\'' +
                ", eChkEmapvMarkcd='" + eChkEmapvMarkcd + '\'' +
                ", workFlagCode='" + workFlagCode + '\'' +
                ", rmk='" + rmk + '\'' +
                ", masterCuscd='" + masterCuscd + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", bizType='" + bizType + '\'' +
                ", chkStatus='" + chkStatus + '\'' +
                ", docType='" + docType + '\'' +
                ", retChannel='" + retChannel + '\'' +
                ", decTime='" + decTime + '\'' +
                ", chkTime='" + chkTime + '\'' +
                ", seqNo='" + seqNo + '\'' +
                ", declareMark='" + declareMark + '\'' +
                ", inputerCode='" + inputerCode + '\'' +
                ", inputerName='" + inputerName + '\'' +
                ", inputCopNo='" + inputCopNo + '\'' +
                ", inputCopName='" + inputCopName + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createName='" + createName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", upStringBy='" + upStringBy + '\'' +
                ", upStringName='" + upStringName + '\'' +
                ", upStringTime='" + upStringTime + '\'' +
                '}';
    }
}