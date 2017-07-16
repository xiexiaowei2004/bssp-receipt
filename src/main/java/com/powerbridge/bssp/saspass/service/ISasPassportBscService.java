package com.powerbridge.bssp.saspass.service;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.saspass.entity.SasPassportBsc;

public interface ISasPassportBscService extends IService<SasPassportBsc> {

    /**
     * 根据seqNo删除表数据和子表数据
     *
     * @param seqNo 单据编号
     * @return
     */
    Boolean deleteBySeqNo(String seqNo) throws Exception;
}
