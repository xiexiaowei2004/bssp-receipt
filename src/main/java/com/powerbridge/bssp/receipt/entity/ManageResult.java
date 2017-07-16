package com.powerbridge.bssp.receipt.entity;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：ManageResult
 * 类描述：菜单控制器
 * 创建人：danagao
 * 创建时间：2017/6/13 15:16
 *
 * @version 1.0
 */
public class ManageResult implements Comparable {
    private final int priority;
    private String chkStatus;
    private String retChannel;

    public ManageResult(String chkStatus, String retChannel, int priority) {
        this.chkStatus = chkStatus;
        this.retChannel = retChannel;
        this.priority = priority;

    }

    public String getChkStatus() {
        return chkStatus;
    }

    public void setChkStatus(String chkStatus) {
        this.chkStatus = chkStatus;
    }

    public String getRetChannel() {
        return retChannel;
    }

    public void setRetChannel(String retChannel) {
        this.retChannel = retChannel;
    }

    @Override
    public int compareTo(Object o) {
        if (null == o) {
            return 1;
        } else {
            return this.priority - (((ManageResult) o).priority);
        }
    }
}

