package com.powerbridge.bssp.sas.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas.dao.SasDclAcmpFormDtMapper;
import com.powerbridge.bssp.sas.dao.SasDclBscMapper;
import com.powerbridge.bssp.sas.dao.SasDclDtMapper;
import com.powerbridge.bssp.sas.dao.SasDclUcnsDtMapper;
import com.powerbridge.bssp.sas.entity.SasDclBsc;
import com.powerbridge.bssp.sas.service.ISasDclBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * <p>
 * 业务申报表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-01
 */
@Service("sasDclBscService")
public class SasDclBscServiceImpl extends BaseServiceImpl<SasDclBscMapper, SasDclBsc> implements ISasDclBscService {

    @Autowired
    private SasDclBscMapper sasDclBscMapper;
    @Autowired
    private SasDclDtMapper sasDclDtMapper;
    @Autowired
    private SasDclUcnsDtMapper sasDclUcnsDtMapper;
    @Autowired
    private SasDclAcmpFormDtMapper sasDclAcmpFormDtMapper;

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
        sasDclDtMapper.delete(entityWrapper);
        sasDclUcnsDtMapper.delete(entityWrapper);
        sasDclAcmpFormDtMapper.delete(entityWrapper);
        return sasDclBscMapper.delete(entityWrapper) > 0;
    }
}
