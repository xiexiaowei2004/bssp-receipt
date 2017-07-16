package com.powerbridge.bssp.sas.service;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasVehicleBsc;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：ISasVehicleBscService
 * 类描述：车辆信息备案申请表业务逻辑层接口
 * 创建人：xuzuotao
 * 创建时间：2017年5月19日 下午10:12:17
 */
public interface ISasVehicleBscService extends IService<SasVehicleBsc> {
    /**
     * 根据seqNo删除表数据和子表数据
     *
     * @param seqNo 单据编号
     * @return
     */
    Boolean deleteBySeqNo(String seqNo) throws Exception;
}
