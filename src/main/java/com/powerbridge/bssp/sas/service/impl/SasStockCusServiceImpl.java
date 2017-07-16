package com.powerbridge.bssp.sas.service.impl;


import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas.dao.SasStockCusMapper;
import com.powerbridge.bssp.sas.entity.SasStockCus;
import com.powerbridge.bssp.sas.service.ISasStockCusService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 出入库单正式表 服务实现类
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
@Service("sasStockCusService")
public class SasStockCusServiceImpl extends BaseServiceImpl<SasStockCusMapper, SasStockCus> implements ISasStockCusService {

}
