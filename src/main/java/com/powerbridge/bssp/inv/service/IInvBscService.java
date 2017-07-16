package com.powerbridge.bssp.inv.service;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.inv.entity.InvBsc;

import javax.transaction.Transactional;

/**
 * <p>
 * 保税核注清单表  服务类
 * </p>
 *
 * @author zsl
 * @since 2017-06-03
 */
public interface IInvBscService extends IService<InvBsc> {

    @Transactional
    boolean deleteBySeqNo(String seqNo) throws Exception;
}
