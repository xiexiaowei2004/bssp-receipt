package com.powerbridge.bssp.inv.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.inv.dao.*;
import com.powerbridge.bssp.inv.entity.InvBsc;
import com.powerbridge.bssp.inv.entity.InvDt;
import com.powerbridge.bssp.inv.service.IInvBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：InvBscServiceImpl
 * 类描述：InvBsc 表业务逻辑层接口 实现类
 * 创建时间：2017年5月17日 下午10:51:17
 */
@Service("invBscService")
public class InvBscServiceImpl extends BaseServiceImpl<InvBscMapper, InvBsc> implements IInvBscService {

    @Autowired
    private InvBscMapper invBscMapper;

    @Autowired
    private InvDtMapper invDtMapper;

    @Autowired
    private InvDecDtMapper invDecDtMapper;

    @Autowired
    private InvDecBscMapper invDecBscMapper;

    @Autowired
    private InvAcmpFormDtMapper invAcmpFormDtMapper;


    /**
     * @param seqNo
     * @return boolean
     * @throws
     * @Description: 用于删除主表时，同时删除字表(批量删除)
     */
    @Override
    @Transactional
    public boolean deleteBySeqNo(String seqNo) throws Exception {
        EntityWrapper entityWrapper = new EntityWrapper<InvDt>();
        entityWrapper.eq("SEQ_NO", seqNo);
        invDtMapper.delete(entityWrapper);
        invDecDtMapper.delete(entityWrapper);
        invDecBscMapper.delete(entityWrapper);
        invAcmpFormDtMapper.delete(entityWrapper);
        return invBscMapper.delete(entityWrapper) > 0;
    }
}
