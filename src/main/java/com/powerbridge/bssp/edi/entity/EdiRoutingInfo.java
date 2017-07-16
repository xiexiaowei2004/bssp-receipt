package com.powerbridge.bssp.edi.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 项目名称： bssp Maven Webapp
 * 类名称： EdiRoutingInfo
 * 类描述： 路由配置实体类
 * 创建人： LC
 * 创建时间： 2017年5月16日
 */
@TableName("edi_routing_info")
public class EdiRoutingInfo implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    @TableId(value = "UID")
    private String uid;//唯一主键

    @TableField(value = "AREA_CODE")
    private String areaCode;//监管场所

    @TableField(value = "TOKEN")
    private String toKen;//认证码

    @TableField(value = "DOC_TYPE")
    private String docType;//单据类型

    @TableField(value = "DOC_NAME")
    private String docName;//单据名称

    @TableField(value = "BIZ_TYPE")
    private String bizType;//业务类型

    @TableField(value = "BIZ_NAME")
    private String bizName;//业务名称

    @TableField(value = "ROUTE_TYPE")
    private String routeType;//路由标识

    @TableField(value = "PRONAME")
    private String proName;//报文头

    @TableField(value = "STATUS")
    private String status;//启用标识

    @TableField(value = "TRANSMODE")
    private String transMode;//传输方式

    @TableField(value = "REMARKS")
    private String remarks;//备注

    @TableField(value = "CREATE_BY")
    private String createBy;//创建人代码

    @TableField(value = "CREATE_NAME")
    private String createName;//创建人名称

    @TableField(value = "CREATE_TIME")
    private String createTime;//创建时间

    @TableField(value = "UPDATE_BY")
    private String updateBy;//修改人代码

    @TableField(value = "UPDATE_NAME")
    private String updateName;//修改人名称
    @TableField(value = "UPDATE_TIME")
    private String updateTime;//修改时间
    @TableField(value = "SENDER_ID")
    private String senderId;
    @TableField(value = "RECEIVER_ID")
    private String receiverId;
    @TableField(value = "RECL_URL")
    private String reclUrl;//接收url
    @TableField(value = "SEND_URL")
    private String sendUrl;//发送url

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReclUrl() {
        return reclUrl;
    }

    public void setReclUrl(String reclUrl) {
        this.reclUrl = reclUrl;
    }

    public String getSendUrl() {
        return sendUrl;
    }

    public void setSendUrl(String sendUrl) {
        this.sendUrl = sendUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getToKen() {
        return toKen;
    }

    public void setToKen(String toKen) {
        this.toKen = toKen;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransMode() {
        return transMode;
    }

    public void setTransMode(String transMode) {
        this.transMode = transMode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}
