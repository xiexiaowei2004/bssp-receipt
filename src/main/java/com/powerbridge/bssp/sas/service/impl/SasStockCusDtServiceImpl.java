package com.powerbridge.bssp.sas.service.impl;


import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas.dao.SasStockCusDtMapper;
import com.powerbridge.bssp.sas.entity.SasStockCusDt;
import com.powerbridge.bssp.sas.service.ISasStockCusDtService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 出入库商品正式表 服务实现类
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
@Service("sasStockCusDtService")
public class SasStockCusDtServiceImpl extends BaseServiceImpl<SasStockCusDtMapper, SasStockCusDt> implements ISasStockCusDtService {

}
