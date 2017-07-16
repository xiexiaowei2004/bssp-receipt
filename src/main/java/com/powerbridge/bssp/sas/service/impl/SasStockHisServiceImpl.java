package com.powerbridge.bssp.sas.service.impl;


import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas.dao.SasStockHisMapper;
import com.powerbridge.bssp.sas.entity.SasStockHis;
import com.powerbridge.bssp.sas.service.ISasStockHisService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 出入库单历史记录表 服务实现类
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
@Service("sasStockHisService")
public class SasStockHisServiceImpl extends BaseServiceImpl<SasStockHisMapper, SasStockHis> implements ISasStockHisService {

}
