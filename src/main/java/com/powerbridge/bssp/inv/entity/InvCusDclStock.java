package com.powerbridge.bssp.inv.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 清单随附出入库单明细
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
@TableName("inv_cus_dcl_stock")
public class InvCusDclStock implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 6583891416644619832L;
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
     * 出入库单编号
     */
    @TableField("SAS_STOCK_NO")
    private String sasStockNo;


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

    public String getSasStockNo() {
        return sasStockNo;
    }

    public void setSasStockNo(String sasStockNo) {
        this.sasStockNo = sasStockNo;
    }

}
