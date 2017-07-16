package com.powerbridge.bssp.sas.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.sas.entity.SasVehicleBsc;
import org.springframework.stereotype.Repository;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：SasVehicleBscMapper
 * 类描述： 车辆备案信息申请表数据访问层接口
 * 创建人：xuzuotao
 * 创建时间：2017年5月19日 下午10:54:17
 */
@Repository("sasVehicleBscMapper")
public interface SasVehicleBscMapper extends BaseMapper<SasVehicleBsc> {

}