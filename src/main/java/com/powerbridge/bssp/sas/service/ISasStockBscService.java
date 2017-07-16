package com.powerbridge.bssp.sas.service;


import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasStockBsc;

/**
 * <p>
 * 出入库单表 服务类
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
public interface ISasStockBscService extends IService<SasStockBsc> {
    /**
     * 根据seqNo删除表数据和子表数据
     *
     * @param seqNo 单据编号
     * @return
     */
    Boolean deleteBySeqNo(String seqNo) throws Exception;
}
