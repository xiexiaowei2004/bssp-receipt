package com.powerbridge.bssp.receipt.util;

/**
 * 项目名称：bssp-receipt
 * 类名称：PostActionEnum
 * 类描述：
 * 创建人：willChen
 * 创建时间：2017/7/10 10:39
 * 修改人：willChen
 * 修改时间：2017/7/10 10:39
 */
public enum PostActionEnum {
    SUCCESS_1(1, "回执数据更新成功"),
    SUCCESS_2(2, "回执优先级低或相同，不更新数据");

    private int postAction;
    private String result;

    PostActionEnum(int postAction, String result) {
        this.postAction = postAction;
        this.result = result;
    }

    public int getPostAction() {
        return postAction;
    }

    public String getResult() {
        return result;
    }

}
