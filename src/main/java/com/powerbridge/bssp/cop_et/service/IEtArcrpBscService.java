package com.powerbridge.bssp.cop_et.service;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cop_et.entity.EtArcrpBsc;

/**
 * 联网企业档案库基本表 服务类
 *
 * @author willChen
 * @since 2017-05-22
 */
public interface IEtArcrpBscService extends IService<EtArcrpBsc> {

    /**
     * 根据seqNo删除表数据和子表数据
     *
     * @param seqNo 单据编号
     * @return
     */
    boolean deleteBySeqNo(String seqNo) throws Exception;

}
