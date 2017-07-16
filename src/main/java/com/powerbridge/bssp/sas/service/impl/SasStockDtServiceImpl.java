package com.powerbridge.bssp.sas.service.impl;


import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas.dao.SasStockDtMapper;
import com.powerbridge.bssp.sas.entity.SasStockDt;
import com.powerbridge.bssp.sas.service.ISasStockDtService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 出入库商品表 服务实现类
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
@Service("sasStockDtService")
public class SasStockDtServiceImpl extends BaseServiceImpl<SasStockDtMapper, SasStockDt> implements ISasStockDtService {

}
