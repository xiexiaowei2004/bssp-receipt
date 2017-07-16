package com.powerbridge.bssp.sas.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas.dao.SasStockBscMapper;
import com.powerbridge.bssp.sas.dao.SasStockDtMapper;
import com.powerbridge.bssp.sas.entity.SasStockBsc;
import com.powerbridge.bssp.sas.service.ISasStockBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * <p>
 * 出入库单表 服务实现类
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
@Service("sasStockBscService")
public class SasStockBscServiceImpl extends BaseServiceImpl<SasStockBscMapper, SasStockBsc> implements ISasStockBscService {
    @Autowired
    private SasStockBscMapper sasStockBscMapper;
    @Autowired
    private SasStockDtMapper sasStockDtMapper;

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
        sasStockDtMapper.delete(entityWrapper);

        return sasStockBscMapper.delete(entityWrapper) > 0;
    }
}
