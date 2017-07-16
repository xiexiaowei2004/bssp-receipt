package com.powerbridge.bssp.sas.service.impl;


import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas.dao.SasStockHisDtMapper;
import com.powerbridge.bssp.sas.entity.SasStockHisDt;
import com.powerbridge.bssp.sas.service.ISasStockHisDtService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 出入库商品历史记录表 服务实现类
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
@Service("sasStockHisDtService")
public class SasStockHisDtServiceImpl extends BaseServiceImpl<SasStockHisDtMapper, SasStockHisDt> implements ISasStockHisDtService {

}
