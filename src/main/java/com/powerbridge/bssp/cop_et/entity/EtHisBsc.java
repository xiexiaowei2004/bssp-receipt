package com.powerbridge.bssp.cop_et.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 联网企业档案库基本历史表
 *
 * @author willChen
 * @since 2017-07-03 09:41:45
 */
@TableName("et_his_bsc")
public class EtHisBsc implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 8018730152760871286L;
    /**
     * 主键
     */
    @TableId("UID")
    private String uid;
    /**
     * 联网企业档案库编号
     */
    @TableField("ET_ARCRP_NO")
    private String etArcrpNo;
    /**
     * 变更次数
     */
    @TableField("CHG_TMS_CNT")
    private Integer chgTmsCnt;
    /**
     * 企业预录入编号
     */
    @TableField("ETPS_PREENT_NO")
    private String etpsPreentNo;
    /**
     * 经营企业社会信用代码
     */
    @TableField("BIZOP_ETPS_SCCD")
    private String bizopEtpsSccd;
    /**
     * 经营企业编号
     */
    @TableField("BIZOP_ETPSNO")
    private String bizopEtpsno;
    /**
     * 经营企业名称
     */
    @TableField("BIZOP_ETPS_NM")
    private String bizopEtpsNm;
    /**
     * 加工企业社会信用代码
     */
    @TableField("PRCS_ETPS_SCCD")
    private String prcsEtpsSccd;
    /**
     * 加工企业编号
     */
    @TableField("PRCS_ETPSNO")
    private String prcsEtpsno;
    /**
     * 加工企业名称
     */
    @TableField("PRCS_ETPS_NM")
    private String prcsEtpsNm;
    /**
     * 申报企业社会信用代码
     */
    @TableField("DCL_ETPS_SCCD")
    private String dclEtpsSccd;
    /**
     * 申报企业编号
     */
    @TableField("DCL_ETPSNO")
    private String dclEtpsno;
    /**
     * 申报企业名称
     */
    @TableField("DCL_ETPS_NM")
    private String dclEtpsNm;
    /**
     * 申报企业类型代码
     */
    @TableField("DECLARE_ER_TYPE")
    private String declareErType;
    /**
     * 申报企业类型名称
     */
    @TableField("DECLARE_ER_TYPENM")
    private String declareErTypenm;
    /**
     * 联系地址
     */
    @TableField("CONC_ADDR")
    private String concAddr;
    /**
     * 电话号码
     */
    @TableField("TELNUM")
    private String telnum;
    @TableField("FINISH_VALID_DATE")
    private Date finishValidDate;
    /**
     * 批准证编号
     */
    @TableField("APCRET_NO")
    private String apcretNo;
    /**
     * 加工生产能力金额
     */
    @TableField("PRCS_PRDC_ABLT_AMT")
    private BigDecimal prcsPrdcAbltAmt;
    /**
     * 风险担保标记代码
     */
    @TableField("RISK_ASSURE_MARKCD")
    private String riskAssureMarkcd;
    /**
     * 风险担保标记名称
     */
    @TableField("RISK_ASSURE_MARKNM")
    private String riskAssureMarknm;
    /**
     * 申报来源标记代码
     */
    @TableField("DCL_SOURCE_MARKCD")
    private String dclSourceMarkcd;
    /**
     * 申报来源标记名称
     */
    @TableField("DCL_SOURCE_MARKNM")
    private String dclSourceMarknm;
    /**
     * 申报时间
     */
    @TableField("DCL_TIME")
    private Date dclTime;
    /**
     * 申报类型代码
     */
    @TableField("DCL_TYPECD")
    private String dclTypecd;
    /**
     * 申报类型名称
     */
    @TableField("DCL_TYPENAME")
    private String dclTypename;
    /**
     * 执行标记代码
     */
    @TableField("EXE_MARKCD")
    private String exeMarkcd;
    /**
     * 执行标记名称
     */
    @TableField("EXE_MARKNM")
    private String exeMarknm;
    /**
     * 关联单证编号
     */
    @TableField("RLT_FORM_NO")
    private String rltFormNo;
    /**
     * 备案批准时间
     */
    @TableField("PUTREC_APPR_TIME")
    private Date putrecApprTime;
    /**
     * 变更批准时间
     */
    @TableField("CHG_APPR_TIME")
    private Date chgApprTime;
    /**
     * 审批状态代码
     */
    @TableField("EMAPV_STUCD")
    private String emapvStucd;
    /**
     * 审批状态名称
     */
    @TableField("EMAPV_STUNM")
    private String emapvStunm;
    /**
     * 备注
     */
    @TableField("RMK")
    private String rmk;
    /**
     * 主管关区代码
     */
    @TableField("MASTER_CUSCD")
    private String masterCuscd;
    /**
     * 主管关区名称
     */
    @TableField("MASTER_CUS_NAME")
    private String masterCusName;
    /**
     * 场地代码
     */
    @TableField("AREA_CODE")
    private String areaCode;
    /**
     * 场地名称
     */
    @TableField("AREA_NAME")
    private String areaName;
    /**
     * 业务类型
     */
    @TableField("BIZ_TYPE")
    private String bizType;
    /**
     * 单据状态
     */
    @TableField("CHK_STATUS")
    private String chkStatus;
    /**
     * 单据类别
     */
    @TableField("DOC_TYPE")
    private String docType;
    @TableField("RET_CHANNEL")
    private String retChannel;
    /**
     * 业务类型名称
     */
    @TableField("BIZ_TYPE_NAME")
    private String bizTypeName;
    /**
     * 单据状态名称
     */
    @TableField("CHK_STATUS_NAME")
    private String chkStatusName;
    /**
     * 单据类别名称
     */
    @TableField("DOC_TYPE_NAME")
    private String docTypeName;
    /**
     * 回执状态名称
     */
    @TableField("RET_CHANNEL_NAME")
    private String retChannelName;
    /**
     * 录入日期
     */
    @TableField("DEC_TIME")
    private Date decTime;
    /**
     * 单据编号
     */
    @TableField("SEQ_NO")
    private String seqNo;
    /**
     * 审批时间
     */
    @TableField("CHK_TIME")
    private Date chkTime;
    /**
     * 申报（录入）人代码
     */
    @TableField("INPUTER_CODE")
    private String inputerCode;
    /**
     * 申报（录入）人名称
     */
    @TableField("INPUTER_NAME")
    private String inputerName;
    /**
     * 申报（录入）企业社会信用代码
     */
    @TableField("INPUT_ETPS_SCCD")
    private String inputEtpsSccd;
    /**
     * 申报（录入）企业代码
     */
    @TableField("INPUT_COP_NO")
    private String inputCopNo;
    /**
     * 申报（录入）企业名称
     */
    @TableField("INPUT_COP_NAME")
    private String inputCopName;
    /**
     * 申报方式(备案/变更)
     */
    @TableField("DECLARE_MARK")
    private String declareMark;
    /**
     * 创建人代码
     */
    @TableField("CREATE_BY")
    private String createBy;
    /**
     * 创建人名称
     */
    @TableField("CREATE_NAME")
    private String createName;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;
    /**
     * 修改人代码
     */
    @TableField("UPDATE_BY")
    private String updateBy;
    /**
     * 修改人名称
     */
    @TableField("UPDATE_NAME")
    private String updateName;
    /**
     * 修改时间
     */
    @TableField("UPDATE_TIME")
    private Date updateTime;
    /**
     * 入库日期
     */
    @TableField("ADD_TIME")
    private Date addTime;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEtArcrpNo() {
        return etArcrpNo;
    }

    public void setEtArcrpNo(String etArcrpNo) {
        this.etArcrpNo = etArcrpNo;
    }

    public Integer getChgTmsCnt() {
        return chgTmsCnt;
    }

    public void setChgTmsCnt(Integer chgTmsCnt) {
        this.chgTmsCnt = chgTmsCnt;
    }

    public String getEtpsPreentNo() {
        return etpsPreentNo;
    }

    public void setEtpsPreentNo(String etpsPreentNo) {
        this.etpsPreentNo = etpsPreentNo;
    }

    public String getBizopEtpsSccd() {
        return bizopEtpsSccd;
    }

    public void setBizopEtpsSccd(String bizopEtpsSccd) {
        this.bizopEtpsSccd = bizopEtpsSccd;
    }

    public String getBizopEtpsno() {
        return bizopEtpsno;
    }

    public void setBizopEtpsno(String bizopEtpsno) {
        this.bizopEtpsno = bizopEtpsno;
    }

    public String getBizopEtpsNm() {
        return bizopEtpsNm;
    }

    public void setBizopEtpsNm(String bizopEtpsNm) {
        this.bizopEtpsNm = bizopEtpsNm;
    }

    public String getPrcsEtpsSccd() {
        return prcsEtpsSccd;
    }

    public void setPrcsEtpsSccd(String prcsEtpsSccd) {
        this.prcsEtpsSccd = prcsEtpsSccd;
    }

    public String getPrcsEtpsno() {
        return prcsEtpsno;
    }

    public void setPrcsEtpsno(String prcsEtpsno) {
        this.prcsEtpsno = prcsEtpsno;
    }

    public String getPrcsEtpsNm() {
        return prcsEtpsNm;
    }

    public void setPrcsEtpsNm(String prcsEtpsNm) {
        this.prcsEtpsNm = prcsEtpsNm;
    }

    public String getDclEtpsSccd() {
        return dclEtpsSccd;
    }

    public void setDclEtpsSccd(String dclEtpsSccd) {
        this.dclEtpsSccd = dclEtpsSccd;
    }

    public String getDclEtpsno() {
        return dclEtpsno;
    }

    public void setDclEtpsno(String dclEtpsno) {
        this.dclEtpsno = dclEtpsno;
    }

    public String getDclEtpsNm() {
        return dclEtpsNm;
    }

    public void setDclEtpsNm(String dclEtpsNm) {
        this.dclEtpsNm = dclEtpsNm;
    }

    public String getDeclareErType() {
        return declareErType;
    }

    public void setDeclareErType(String declareErType) {
        this.declareErType = declareErType;
    }

    public String getDeclareErTypenm() {
        return declareErTypenm;
    }

    public void setDeclareErTypenm(String declareErTypenm) {
        this.declareErTypenm = declareErTypenm;
    }

    public String getConcAddr() {
        return concAddr;
    }

    public void setConcAddr(String concAddr) {
        this.concAddr = concAddr;
    }

    public String getTelnum() {
        return telnum;
    }

    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }

    public Date getFinishValidDate() {
        return finishValidDate;
    }

    public void setFinishValidDate(Date finishValidDate) {
        this.finishValidDate = finishValidDate;
    }

    public String getApcretNo() {
        return apcretNo;
    }

    public void setApcretNo(String apcretNo) {
        this.apcretNo = apcretNo;
    }

    public BigDecimal getPrcsPrdcAbltAmt() {
        return prcsPrdcAbltAmt;
    }

    public void setPrcsPrdcAbltAmt(BigDecimal prcsPrdcAbltAmt) {
        this.prcsPrdcAbltAmt = prcsPrdcAbltAmt;
    }

    public String getRiskAssureMarkcd() {
        return riskAssureMarkcd;
    }

    public void setRiskAssureMarkcd(String riskAssureMarkcd) {
        this.riskAssureMarkcd = riskAssureMarkcd;
    }

    public String getRiskAssureMarknm() {
        return riskAssureMarknm;
    }

    public void setRiskAssureMarknm(String riskAssureMarknm) {
        this.riskAssureMarknm = riskAssureMarknm;
    }

    public String getDclSourceMarkcd() {
        return dclSourceMarkcd;
    }

    public void setDclSourceMarkcd(String dclSourceMarkcd) {
        this.dclSourceMarkcd = dclSourceMarkcd;
    }

    public String getDclSourceMarknm() {
        return dclSourceMarknm;
    }

    public void setDclSourceMarknm(String dclSourceMarknm) {
        this.dclSourceMarknm = dclSourceMarknm;
    }

    public Date getDclTime() {
        return dclTime;
    }

    public void setDclTime(Date dclTime) {
        this.dclTime = dclTime;
    }

    public String getDclTypecd() {
        return dclTypecd;
    }

    public void setDclTypecd(String dclTypecd) {
        this.dclTypecd = dclTypecd;
    }

    public String getDclTypename() {
        return dclTypename;
    }

    public void setDclTypename(String dclTypename) {
        this.dclTypename = dclTypename;
    }

    public String getExeMarkcd() {
        return exeMarkcd;
    }

    public void setExeMarkcd(String exeMarkcd) {
        this.exeMarkcd = exeMarkcd;
    }

    public String getExeMarknm() {
        return exeMarknm;
    }

    public void setExeMarknm(String exeMarknm) {
        this.exeMarknm = exeMarknm;
    }

    public String getRltFormNo() {
        return rltFormNo;
    }

    public void setRltFormNo(String rltFormNo) {
        this.rltFormNo = rltFormNo;
    }

    public Date getPutrecApprTime() {
        return putrecApprTime;
    }

    public void setPutrecApprTime(Date putrecApprTime) {
        this.putrecApprTime = putrecApprTime;
    }

    public Date getChgApprTime() {
        return chgApprTime;
    }

    public void setChgApprTime(Date chgApprTime) {
        this.chgApprTime = chgApprTime;
    }

    public String getEmapvStucd() {
        return emapvStucd;
    }

    public void setEmapvStucd(String emapvStucd) {
        this.emapvStucd = emapvStucd;
    }

    public String getEmapvStunm() {
        return emapvStunm;
    }

    public void setEmapvStunm(String emapvStunm) {
        this.emapvStunm = emapvStunm;
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

    public String getMasterCusName() {
        return masterCusName;
    }

    public void setMasterCusName(String masterCusName) {
        this.masterCusName = masterCusName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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

    public String getBizTypeName() {
        return bizTypeName;
    }

    public void setBizTypeName(String bizTypeName) {
        this.bizTypeName = bizTypeName;
    }

    public String getChkStatusName() {
        return chkStatusName;
    }

    public void setChkStatusName(String chkStatusName) {
        this.chkStatusName = chkStatusName;
    }

    public String getDocTypeName() {
        return docTypeName;
    }

    public void setDocTypeName(String docTypeName) {
        this.docTypeName = docTypeName;
    }

    public String getRetChannelName() {
        return retChannelName;
    }

    public void setRetChannelName(String retChannelName) {
        this.retChannelName = retChannelName;
    }

    public Date getDecTime() {
        return decTime;
    }

    public void setDecTime(Date decTime) {
        this.decTime = decTime;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public Date getChkTime() {
        return chkTime;
    }

    public void setChkTime(Date chkTime) {
        this.chkTime = chkTime;
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

    public String getInputEtpsSccd() {
        return inputEtpsSccd;
    }

    public void setInputEtpsSccd(String inputEtpsSccd) {
        this.inputEtpsSccd = inputEtpsSccd;
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

    public String getDeclareMark() {
        return declareMark;
    }

    public void setDeclareMark(String declareMark) {
        this.declareMark = declareMark;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

}
