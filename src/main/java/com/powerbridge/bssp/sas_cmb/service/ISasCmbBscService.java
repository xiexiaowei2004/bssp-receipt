package com.powerbridge.bssp.sas_cmb.service;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas_cmb.entity.SasCmbBsc;

/**
 * 耗料单基本信息 服务类
 *
 * @author huanliu
 * @since 2017-06-18
 */
public interface ISasCmbBscService extends IService<SasCmbBsc> {
    /**
     * 根据seqNo删除表数据和子表数据
     *
     * @param seqNo 单据编号
     * @return
     */
    Boolean deleteBySeqNo(String seqNo) throws Exception;
}
