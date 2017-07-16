package com.powerbridge.bssp.ems.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.ems.dao.*;
import com.powerbridge.bssp.ems.entity.EmsPutrecBsc;
import com.powerbridge.bssp.ems.service.IEmsPutrecBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-receipt
 * 类名称：EmsPutrecBscServiceImpl
 * 类描述：账册备案申请表服务实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:23
 * 修改人：jokylao
 * 修改时间：2017/6/1 22:18
 */
@Service("emsPutrecBscService")
public class EmsPutrecBscServiceImpl extends BaseServiceImpl<EmsPutrecBscMapper, EmsPutrecBsc> implements IEmsPutrecBscService {

    @Autowired
    private EmsPutrecBscMapper emsPutrecBscMapper;
    @Autowired
    private EmsPutrecImgMapper emsPutrecImgMapper;
    @Autowired
    private EmsPutrecExgMapper emsPutrecExgMapper;
    @Autowired
    private EmsPutrecUcnsDtMapper emsPutrecUcnsDtMapper;
    @Autowired
    private EmsPutrecAcmpFormDtMapper emsPutrecAcmpFormDtMapper;

    /**
     * 根据id删除表数据和子表数据
     *
     * @param seqNo 单据编号
     * @return
     */
    @Override
    public boolean deleteBySeqNo(String seqNo) throws Exception {
        //执行删除
        //先删除子表数据
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("SEQ_NO", seqNo);
        emsPutrecImgMapper.delete(entityWrapper);
        emsPutrecExgMapper.delete(entityWrapper);
        emsPutrecUcnsDtMapper.delete(entityWrapper);
        emsPutrecAcmpFormDtMapper.delete(entityWrapper);
        //再删除主表数据
        return emsPutrecBscMapper.delete(entityWrapper) > 0;
    }
}
