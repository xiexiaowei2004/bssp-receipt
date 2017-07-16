package com.powerbridge.bssp.sas.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.sas.entity.SasStockDt;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 出入库商品表 Mapper 接口
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
@Repository("sasStockDtMapper")
public interface SasStockDtMapper extends BaseMapper<SasStockDt> {

}