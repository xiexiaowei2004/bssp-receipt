package com.powerbridge.bssp.dcr.service;


import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.dcr.entity.DcrChgoffBsc;

/**
 * <p>
 * 账册报核基础表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-05-22
 */
public interface IDcrChgoffBscService extends IService<DcrChgoffBsc> {

    boolean deleteBySeqNo(String seqNo) throws Exception;
}
