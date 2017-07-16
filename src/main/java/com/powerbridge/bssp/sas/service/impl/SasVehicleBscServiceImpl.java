package com.powerbridge.bssp.sas.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas.dao.SasVehicleBscMapper;
import com.powerbridge.bssp.sas.entity.SasVehicleBsc;
import com.powerbridge.bssp.sas.service.ISasVehicleBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：SasVehicleBscServiceImpl
 * 类描述：车辆信息备案申请表业务逻辑层接口 实现类
 * 创建人：xuzuotao
 * 创建时间：2017年5月19日 下午10:55:23
 */
@Service("sasVehicleBscService")
public class SasVehicleBscServiceImpl extends BaseServiceImpl<SasVehicleBscMapper, SasVehicleBsc> implements ISasVehicleBscService {
    @Autowired
    private SasVehicleBscMapper sasVehicleBscMapper;

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


        return sasVehicleBscMapper.delete(entityWrapper) > 0;
    }
}
