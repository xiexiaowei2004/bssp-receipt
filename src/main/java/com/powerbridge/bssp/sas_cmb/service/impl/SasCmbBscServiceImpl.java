package com.powerbridge.bssp.sas_cmb.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas_cmb.dao.SasCmbBscMapper;
import com.powerbridge.bssp.sas_cmb.dao.SasCmbImgMapper;
import com.powerbridge.bssp.sas_cmb.dao.SasCmbInvtMapper;
import com.powerbridge.bssp.sas_cmb.dao.SasCmbRbgMapper;
import com.powerbridge.bssp.sas_cmb.entity.SasCmbBsc;
import com.powerbridge.bssp.sas_cmb.service.ISasCmbBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 耗料单基本信息 服务实现类
 *
 * @author huanliu
 * @since 2017-06-18
 */
@Service("sasCmbBscService")
public class SasCmbBscServiceImpl extends BaseServiceImpl<SasCmbBscMapper, SasCmbBsc> implements ISasCmbBscService {


    @Autowired
    private SasCmbBscMapper sasCmbBscMapper;
    @Autowired
    private SasCmbInvtMapper sasCmbInvtMapper;
    @Autowired
    private SasCmbImgMapper sasCmbImgMapper;
    @Autowired
    private SasCmbRbgMapper sasCmbRbgMapper;

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
        sasCmbInvtMapper.delete(entityWrapper);
        sasCmbImgMapper.delete(entityWrapper);
        sasCmbRbgMapper.delete(entityWrapper);
        return sasCmbBscMapper.delete(entityWrapper) > 0;
    }
}