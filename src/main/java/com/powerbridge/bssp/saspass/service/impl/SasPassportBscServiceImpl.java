package com.powerbridge.bssp.saspass.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.saspass.dao.SasPassportBscMapper;
import com.powerbridge.bssp.saspass.dao.SasPassportDtMapper;
import com.powerbridge.bssp.saspass.dao.SasPassportRltMapper;
import com.powerbridge.bssp.saspass.entity.SasPassportBsc;
import com.powerbridge.bssp.saspass.service.ISasPassportBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("sasPassportBscService")
public class SasPassportBscServiceImpl extends BaseServiceImpl<SasPassportBscMapper, SasPassportBsc> implements ISasPassportBscService {

    @Autowired
    private SasPassportBscMapper sasPassportBscMapper;
    @Autowired
    private SasPassportRltMapper sasPassportRltMapper;
    @Autowired
    private SasPassportDtMapper sasPassportDtMapper;

    /**
     * 根据seqNo删除表数据和子表数据
     *
     * @param seqNo 单据编号
     * @return
     */
    @Override
    @Transactional
    public Boolean deleteBySeqNo(String seqNo) throws Exception {
        //执行删除
        //先删除子表数据
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("SEQ_NO", seqNo);
        sasPassportRltMapper.delete(entityWrapper);
        sasPassportDtMapper.delete(entityWrapper);
        return sasPassportBscMapper.delete(entityWrapper) > 0;
    }

}
