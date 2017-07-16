package com.powerbridge.bssp.sas.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 临时车辆登记表
 *
 * @pdOid fb45f904-3f02-4f6c-86ec-3bd094c7bd9c
 */
@TableName(value = "sas_vehicle_temp_reg")
public class SasVehicleTempReg implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     *
     * @pdOid 8bcffa9a-2c49-435f-96bd-58733ac00eb7
     */
    @TableId(value = "UID")
    private String uid;
    /**
     * 车牌号
     *
     * @pdOid ed581ec2-911c-46b7-8938-c8efce26033e
     */
    @TableField(value = "VEHICLE_NO")
    private String vehicleNo;
    /**
     * 车牌类型
     *
     * @pdOid 31f47fee-6d87-4a52-a545-269d301e2e6d
     */
    @TableField(value = "PLATE_TYPECD")
    private String plateTypecd;
    /**
     * 车辆类型
     *
     * @pdOid 169bf1a0-7ecb-4569-8f55-294fb9fa2950
     */
    @TableField(value = "VEHICLE_TYPECD")
    private String vehicleTypecd;
    /**
     * 企业社会信用代码
     *
     * @pdOid ab312397-ede7-42b1-a28f-f0889d06faf1
     */
    @TableField(value = "ETPS_SCCD")
    private String etpsSccd;
    /**
     * 车主名称
     *
     * @pdOid a2976024-85d6-4ea8-8528-60c0f09c0218
     */
    @TableField(value = "VEHICLE_I_REGNO")
    private String vehicleIRegno;
    /**
     * 车属企业名称
     *
     * @pdOid 10d31811-0907-40ed-9483-cfa4a1a9df94
     */
    @TableField(value = "VEHICLE_ENT_NAME")
    private String vehicleEntName;
    /**
     * 载重量
     *
     * @pdOid 72a71937-0fcf-45d8-9828-0454233cd630
     */
    @TableField(value = "VEHICLE_LOAD_WT")
    private double vehicleLoadWt;
    /**
     * 备注
     *
     * @pdOid d5e21342-1f6b-4f2f-b600-23beb47ae955
     */
    @TableField(value = "RMK")
    private String rmk;
    /**
     * 主管关区
     *
     * @pdOid 98782a80-45fb-4d1b-ad42-d65e9ada185e
     */
    @TableField(value = "MASTER_CUSCD")
    private String masterCuscd;
    /**
     * 场地代码
     *
     * @pdOid 2d1063b9-dda9-4423-ad0c-5450b7d56b68
     */
    @TableField(value = "AREA_CODE")
    private String areaCode;
    /**
     * 业务类型
     *
     * @pdOid 2790e4e6-ebba-4190-82ac-f285c90e2527
     */
    @TableField(value = "BIZ_TYPE")
    private String bizType;
    /**
     * 单据状态
     *
     * @pdOid 7fc4d1b8-a193-40a4-a5f5-d119d33f3e14
     */
    @TableField(value = "CHK_STATUS")
    private String chkStatus;
    /**
     * 单据类别
     *
     * @pdOid 77ba0b33-a41d-4d27-af50-40c2dc2ffd2d
     */
    @TableField(value = "DOC_TYPE")
    private String docType;
    /**
     * 回执状态
     *
     * @pdOid b9f6eaf7-58bd-47e9-94e6-808d6b18a498
     */
    @TableField(value = "RET_CHANNEL")
    private String retChannel;
    /**
     * 录入日期
     *
     * @pdOid 21b94a49-cbce-49bf-a73d-9e6641aa3718
     */
    @TableField(value = "DEC_TIME")
    private String decTime;
    /**
     * 审批时间
     *
     * @pdOid 9dc5d914-f209-4853-a31c-9b28ed56b8b3
     */
    @TableField(value = "CHK_TIME")
    private String chkTime;
    /**
     * 单据编号
     *
     * @pdOid 8b0375f7-0aa3-4696-bc4d-10cd9693b7f7
     */
    @TableField(value = "SEQ_NO")
    private String seqNo;
    /**
     * 申报方式(备案/变更)
     *
     * @pdOid 6be58078-446d-47b8-ad2c-28c6f47ffa21
     */
    @TableField(value = "DECLARE_MARK")
    private String declareMark;
    /**
     * 申报（录入）人代码
     *
     * @pdOid 02e06f12-77c7-4e25-98eb-ccf0e76c21e8
     */
    @TableField(value = "INPUTER_CODE")
    private String inputerCode;
    /**
     * 申报（录入）人名称
     *
     * @pdOid d76aa517-2d9f-401b-b023-df53d3b4f220
     */
    @TableField(value = "INPUTER_NAME")
    private String inputerName;
    /**
     * 申报（录入）企业代码
     *
     * @pdOid e6f497c5-79a8-40dc-b157-98ac0b3905df
     */
    @TableField(value = "INPUT_COP_NO")
    private String inputCopNo;
    /**
     * 申报（录入）企业名称
     *
     * @pdOid 7225d964-b2e5-4452-bbac-a382b9348a0a
     */
    @TableField(value = "INPUT_COP_NAME")
    private String inputCopName;
    /**
     * 创建人代码
     *
     * @pdOid edb774cc-7e09-409d-a6ca-c38f3ed30567
     */
    @TableField(value = "CREATE_BY")
    private String createBy;
    /**
     * 创建人名称
     *
     * @pdOid b8e32795-da3e-4214-9aae-d29562bf7dbb
     */
    @TableField(value = "CREATE_NAME")
    private String createName;
    /**
     * 创建时间
     *
     * @pdOid 8d6fd2b9-9ecc-43de-90c4-ee1c362d0328
     */
    @TableField(value = "CREATE_TIME")
    private String createTime;
    /**
     * 修改人代码
     *
     * @pdOid 0435cbba-976b-4a49-9ab8-0955bb36acd6
     */
    @TableField(value = "UPDATE_BY")
    private String upStringBy;
    /**
     * 修改人名称
     *
     * @pdOid 8d158e69-f15b-4345-9d44-6309bfecb5c6
     */
    @TableField(value = "UPDATE_NAME")
    private String upStringName;
    /**
     * 修改时间
     *
     * @pdOid 8fde1ab9-e1fc-4121-ad94-36167cb4e9f0
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

    public String getEtpsSccd() {
        return etpsSccd;
    }

    public void setEtpsSccd(String etpsSccd) {
        this.etpsSccd = etpsSccd;
    }

    public String getVehicleIRegno() {
        return vehicleIRegno;
    }

    public void setVehicleIRegno(String vehicleIRegno) {
        this.vehicleIRegno = vehicleIRegno;
    }

    public String getVehicleEntName() {
        return vehicleEntName;
    }

    public void setVehicleEntName(String vehicleEntName) {
        this.vehicleEntName = vehicleEntName;
    }

    public double getVehicleLoadWt() {
        return vehicleLoadWt;
    }

    public void setVehicleLoadWt(double vehicleLoadWt) {
        this.vehicleLoadWt = vehicleLoadWt;
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
        return "SasVehicleTempReg{" +
                "uid='" + uid + '\'' +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", plateTypecd='" + plateTypecd + '\'' +
                ", vehicleTypecd='" + vehicleTypecd + '\'' +
                ", etpsSccd='" + etpsSccd + '\'' +
                ", vehicleIRegno='" + vehicleIRegno + '\'' +
                ", vehicleEntName='" + vehicleEntName + '\'' +
                ", vehicleLoadWt=" + vehicleLoadWt +
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