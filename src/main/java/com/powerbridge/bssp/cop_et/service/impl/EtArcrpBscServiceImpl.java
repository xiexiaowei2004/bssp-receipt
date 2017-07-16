package com.powerbridge.bssp.cop_et.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cop_et.dao.EtArcrpAcmpFormDtMapper;
import com.powerbridge.bssp.cop_et.dao.EtArcrpBscMapper;
import com.powerbridge.bssp.cop_et.dao.EtArcrpExgMapper;
import com.powerbridge.bssp.cop_et.dao.EtArcrpImgMapper;
import com.powerbridge.bssp.cop_et.entity.EtArcrpBsc;
import com.powerbridge.bssp.cop_et.service.IEtArcrpBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 联网企业档案库基本表 服务实现类
 *
 * @author willChen
 * @since 2017-05-22
 */
@Service("etArcrpBscService")
public class EtArcrpBscServiceImpl extends BaseServiceImpl<EtArcrpBscMapper, EtArcrpBsc> implements IEtArcrpBscService {

    @Autowired
    private EtArcrpBscMapper etArcrpBscMapper;
    @Autowired
    private EtArcrpExgMapper etArcrpExgMapper;
    @Autowired
    private EtArcrpImgMapper etArcrpImgMapper;
    @Autowired
    private EtArcrpAcmpFormDtMapper etArcrpAcmpFormDtMapper;

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
        etArcrpExgMapper.delete(entityWrapper);
        etArcrpImgMapper.delete(entityWrapper);
        etArcrpAcmpFormDtMapper.delete(entityWrapper);
        //再删除主表数据
        return etArcrpBscMapper.delete(entityWrapper) > 0;
    }

}
