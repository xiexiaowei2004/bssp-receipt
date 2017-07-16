package com.powerbridge.bssp.inv.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 简单加工成品明细
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
@TableName("inv_dt_simple")
public class InvDtSimple implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 7166480950504266347L;
    /**
     * 主键
     */
    @TableId("UID")
    private String uid;
    /**
     * 保税清单编号
     */
    @TableField("BOND_INVT_NO")
    private String bondInvtNo;
    /**
     * 变更次数
     */
    @TableField("CHG_TMS_CNT")
    private BigDecimal chgTmsCnt;
    /**
     * 商品序号
     */
    @TableField("GDS_SEQNO")
    private BigDecimal gdsSeqno;
    /**
     * 备案序号
     */
    @TableField("PUTREC_SEQNO")
    private BigDecimal putrecSeqno;
    /**
     * 商品料号
     */
    @TableField("GDS_MTNO")
    private String gdsMtno;
    /**
     * 商品编码
     */
    @TableField("GDECD")
    private String gdecd;
    /**
     * 商品名称
     */
    @TableField("GDS_NM")
    private String gdsNm;
    /**
     * 商品规格型号描述
     */
    @TableField("GDS_SPCF_MODEL_DESC")
    private String gdsSpcfModelDesc;
    /**
     * 申报计量单位代码
     */
    @TableField("DCL_UNITCD")
    private String dclUnitcd;
    /**
     * 法定计量单位代码
     */
    @TableField("LAWF_UNITCD")
    private String lawfUnitcd;
    /**
     * 第二法定计量单位代码
     */
    @TableField("SECD_LAWF_UNITCD")
    private String secdLawfUnitcd;
    /**
     * 国别代码
     */
    @TableField("NATCD")
    private String natcd;
    /**
     * 申报单价金额
     */
    @TableField("DCL_UPRC_AMT")
    private BigDecimal dclUprcAmt;
    /**
     * 申报总金额
     */
    @TableField("DCL_TOTAL_AMT")
    private BigDecimal dclTotalAmt;
    /**
     * 美元统计总金额
     */
    @TableField("USD_STAT_TOTAL_AMT")
    private BigDecimal usdStatTotalAmt;
    /**
     * 申报币制代码
     */
    @TableField("DCL_CURRCD")
    private String dclCurrcd;
    /**
     * 法定数量
     */
    @TableField("LAWF_QTY")
    private BigDecimal lawfQty;
    /**
     * 第二法定数量
     */
    @TableField("SECD_LAWF_QTY")
    private BigDecimal secdLawfQty;
    /**
     * 重量比例因子值
     */
    @TableField("WT_SF_VAL")
    private BigDecimal wtSfVal;
    /**
     * 第一比例因子
     */
    @TableField("FST_SF_VAL")
    private BigDecimal fstSfVal;
    /**
     * 第二比例因子
     */
    @TableField("SECD_SF_VAL")
    private BigDecimal secdSfVal;
    /**
     * 申报数量
     */
    @TableField("DCL_QTY")
    private BigDecimal dclQty;
    /**
     * 毛重
     */
    @TableField("GROSS_WT")
    private BigDecimal grossWt;
    /**
     * 净重
     */
    @TableField("NET_WT")
    private BigDecimal netWt;
    /**
     * 用途代码
     */
    @TableField("USE_CD")
    private String useCd;
    /**
     * 征减免方式代码
     */
    @TableField("LVYRLF_MODECD")
    private String lvyrlfModecd;
    /**
     * 单耗版本号
     */
    @TableField("UCNS_VERNO")
    private String ucnsVerno;
    /**
     * 报关单商品序号
     */
    @TableField("ENTRY_GDS_SEQNO")
    private BigDecimal entryGdsSeqno;
    /**
     * 申请表序号
     */
    @TableField("APPLY_TB_SEQNO")
    private BigDecimal applyTbSeqno;
    /**
     * 归类标记代码
     */
    @TableField("CLY_MARKCD")
    private String clyMarkcd;
    /**
     * 版本号
     */
    @TableField("VERSION")
    private BigDecimal version;
    /**
     * 实际过卡数量
     */
    @TableField("ACTL_PASS_QTY")
    private BigDecimal actlPassQty;
    /**
     * 核放单已用数量
     */
    @TableField("PASSPORT_USED_QTY")
    private BigDecimal passportUsedQty;
    /**
     * 备注
     */
    @TableField("RMK")
    private String rmk;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBondInvtNo() {
        return bondInvtNo;
    }

    public void setBondInvtNo(String bondInvtNo) {
        this.bondInvtNo = bondInvtNo;
    }

    public BigDecimal getChgTmsCnt() {
        return chgTmsCnt;
    }

    public void setChgTmsCnt(BigDecimal chgTmsCnt) {
        this.chgTmsCnt = chgTmsCnt;
    }

    public BigDecimal getGdsSeqno() {
        return gdsSeqno;
    }

    public void setGdsSeqno(BigDecimal gdsSeqno) {
        this.gdsSeqno = gdsSeqno;
    }

    public BigDecimal getPutrecSeqno() {
        return putrecSeqno;
    }

    public void setPutrecSeqno(BigDecimal putrecSeqno) {
        this.putrecSeqno = putrecSeqno;
    }

    public String getGdsMtno() {
        return gdsMtno;
    }

    public void setGdsMtno(String gdsMtno) {
        this.gdsMtno = gdsMtno;
    }

    public String getGdecd() {
        return gdecd;
    }

    public void setGdecd(String gdecd) {
        this.gdecd = gdecd;
    }

    public String getGdsNm() {
        return gdsNm;
    }

    public void setGdsNm(String gdsNm) {
        this.gdsNm = gdsNm;
    }

    public String getGdsSpcfModelDesc() {
        return gdsSpcfModelDesc;
    }

    public void setGdsSpcfModelDesc(String gdsSpcfModelDesc) {
        this.gdsSpcfModelDesc = gdsSpcfModelDesc;
    }

    public String getDclUnitcd() {
        return dclUnitcd;
    }

    public void setDclUnitcd(String dclUnitcd) {
        this.dclUnitcd = dclUnitcd;
    }

    public String getLawfUnitcd() {
        return lawfUnitcd;
    }

    public void setLawfUnitcd(String lawfUnitcd) {
        this.lawfUnitcd = lawfUnitcd;
    }

    public String getSecdLawfUnitcd() {
        return secdLawfUnitcd;
    }

    public void setSecdLawfUnitcd(String secdLawfUnitcd) {
        this.secdLawfUnitcd = secdLawfUnitcd;
    }

    public String getNatcd() {
        return natcd;
    }

    public void setNatcd(String natcd) {
        this.natcd = natcd;
    }

    public BigDecimal getDclUprcAmt() {
        return dclUprcAmt;
    }

    public void setDclUprcAmt(BigDecimal dclUprcAmt) {
        this.dclUprcAmt = dclUprcAmt;
    }

    public BigDecimal getDclTotalAmt() {
        return dclTotalAmt;
    }

    public void setDclTotalAmt(BigDecimal dclTotalAmt) {
        this.dclTotalAmt = dclTotalAmt;
    }

    public BigDecimal getUsdStatTotalAmt() {
        return usdStatTotalAmt;
    }

    public void setUsdStatTotalAmt(BigDecimal usdStatTotalAmt) {
        this.usdStatTotalAmt = usdStatTotalAmt;
    }

    public String getDclCurrcd() {
        return dclCurrcd;
    }

    public void setDclCurrcd(String dclCurrcd) {
        this.dclCurrcd = dclCurrcd;
    }

    public BigDecimal getLawfQty() {
        return lawfQty;
    }

    public void setLawfQty(BigDecimal lawfQty) {
        this.lawfQty = lawfQty;
    }

    public BigDecimal getSecdLawfQty() {
        return secdLawfQty;
    }

    public void setSecdLawfQty(BigDecimal secdLawfQty) {
        this.secdLawfQty = secdLawfQty;
    }

    public BigDecimal getWtSfVal() {
        return wtSfVal;
    }

    public void setWtSfVal(BigDecimal wtSfVal) {
        this.wtSfVal = wtSfVal;
    }

    public BigDecimal getFstSfVal() {
        return fstSfVal;
    }

    public void setFstSfVal(BigDecimal fstSfVal) {
        this.fstSfVal = fstSfVal;
    }

    public BigDecimal getSecdSfVal() {
        return secdSfVal;
    }

    public void setSecdSfVal(BigDecimal secdSfVal) {
        this.secdSfVal = secdSfVal;
    }

    public BigDecimal getDclQty() {
        return dclQty;
    }

    public void setDclQty(BigDecimal dclQty) {
        this.dclQty = dclQty;
    }

    public BigDecimal getGrossWt() {
        return grossWt;
    }

    public void setGrossWt(BigDecimal grossWt) {
        this.grossWt = grossWt;
    }

    public BigDecimal getNetWt() {
        return netWt;
    }

    public void setNetWt(BigDecimal netWt) {
        this.netWt = netWt;
    }

    public String getUseCd() {
        return useCd;
    }

    public void setUseCd(String useCd) {
        this.useCd = useCd;
    }

    public String getLvyrlfModecd() {
        return lvyrlfModecd;
    }

    public void setLvyrlfModecd(String lvyrlfModecd) {
        this.lvyrlfModecd = lvyrlfModecd;
    }

    public String getUcnsVerno() {
        return ucnsVerno;
    }

    public void setUcnsVerno(String ucnsVerno) {
        this.ucnsVerno = ucnsVerno;
    }

    public BigDecimal getEntryGdsSeqno() {
        return entryGdsSeqno;
    }

    public void setEntryGdsSeqno(BigDecimal entryGdsSeqno) {
        this.entryGdsSeqno = entryGdsSeqno;
    }

    public BigDecimal getApplyTbSeqno() {
        return applyTbSeqno;
    }

    public void setApplyTbSeqno(BigDecimal applyTbSeqno) {
        this.applyTbSeqno = applyTbSeqno;
    }

    public String getClyMarkcd() {
        return clyMarkcd;
    }

    public void setClyMarkcd(String clyMarkcd) {
        this.clyMarkcd = clyMarkcd;
    }

    public BigDecimal getVersion() {
        return version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

    public BigDecimal getActlPassQty() {
        return actlPassQty;
    }

    public void setActlPassQty(BigDecimal actlPassQty) {
        this.actlPassQty = actlPassQty;
    }

    public BigDecimal getPassportUsedQty() {
        return passportUsedQty;
    }

    public void setPassportUsedQty(BigDecimal passportUsedQty) {
        this.passportUsedQty = passportUsedQty;
    }

    public String getRmk() {
        return rmk;
    }

    public void setRmk(String rmk) {
        this.rmk = rmk;
    }
}
