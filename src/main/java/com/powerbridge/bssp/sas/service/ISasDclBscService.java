package com.powerbridge.bssp.sas.service;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasDclBsc;

/**
 * <p>
 * 业务申报表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-01
 */
public interface ISasDclBscService extends IService<SasDclBsc> {
    /**
     * 根据seqNo删除表数据和子表数据
     *
     * @param seqNo 单据编号
     * @return
     */
    Boolean deleteBySeqNo(String seqNo) throws Exception;
}
