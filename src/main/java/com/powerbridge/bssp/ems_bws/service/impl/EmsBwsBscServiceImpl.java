package com.powerbridge.bssp.ems_bws.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.ems_bws.dao.EmsBwsAcmpFormDtMapper;
import com.powerbridge.bssp.ems_bws.dao.EmsBwsBscMapper;
import com.powerbridge.bssp.ems_bws.dao.EmsBwsDtMapper;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsBsc;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 项目名称：bssp-receipt
 * 类名称：EmsBwsBscServiceImpl
 * 类描述：账册备案申请表服务实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:23
 * 修改人：jokylao
 * 修改时间：2017/6/1 22:18
 */
@Service("emsBwsBscService")
public class EmsBwsBscServiceImpl extends BaseServiceImpl<EmsBwsBscMapper, EmsBwsBsc> implements IEmsBwsBscService {

    @Autowired
    private EmsBwsBscMapper emsBwsBscMapper;
    @Autowired
    private EmsBwsDtMapper emsBwsDtMapper;
    @Autowired
    private EmsBwsAcmpFormDtMapper emsBwsAcmpFormDtMapper;

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
        emsBwsDtMapper.delete(entityWrapper);
        emsBwsAcmpFormDtMapper.delete(entityWrapper);
        //再删除主表数据
        return emsBwsBscMapper.delete(entityWrapper) > 0;
    }
}
