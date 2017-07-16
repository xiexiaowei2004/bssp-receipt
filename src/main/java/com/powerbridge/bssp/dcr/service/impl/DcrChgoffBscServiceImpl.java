package com.powerbridge.bssp.dcr.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.dcr.dao.DcrChgoffBscMapper;
import com.powerbridge.bssp.dcr.dao.DcrChgoffExgMapper;
import com.powerbridge.bssp.dcr.dao.DcrChgoffImgMapper;
import com.powerbridge.bssp.dcr.dao.DcrChgoffInvtLtMapper;
import com.powerbridge.bssp.dcr.entity.DcrChgoffBsc;
import com.powerbridge.bssp.dcr.service.IDcrChgoffBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * <p>
 * 账册报核基础表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-05-22
 */
@Service("dcrChgoffBscService")
public class DcrChgoffBscServiceImpl extends BaseServiceImpl<DcrChgoffBscMapper, DcrChgoffBsc> implements IDcrChgoffBscService {

    @Autowired
    private DcrChgoffBscMapper dcrChgoffBscMapper;
    @Autowired
    private DcrChgoffExgMapper dcrChgoffExgMapper;
    @Autowired
    private DcrChgoffImgMapper dcrChgoffImgMapper;
    @Autowired
    private DcrChgoffInvtLtMapper dcrChgoffInvtLtMapper;

    /**
     * 根据seqNo删除表数据和子表数据
     *
     * @param seqNo 单据编号
     * @return
     */
    @Override
    @Transactional
    public boolean deleteBySeqNo(String seqNo) throws Exception {
        //执行删除
        //先删除子表数据
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("SEQ_NO", seqNo);
        dcrChgoffExgMapper.delete(entityWrapper);
        dcrChgoffImgMapper.delete(entityWrapper);
        dcrChgoffInvtLtMapper.delete(entityWrapper);
        //再删除主表数据
        return dcrChgoffBscMapper.delete(entityWrapper) > 0;
    }
}
