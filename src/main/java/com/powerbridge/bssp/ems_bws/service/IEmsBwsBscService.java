package com.powerbridge.bssp.ems_bws.service;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsBsc;

/**
 * 项目名称：bssp-receipt
 * 类名称：IEmsBwsBscService
 * 类描述：账册备案申请表服务
 * 创建人：willChen
 * 创建时间：2017/5/16 22:00
 * 修改人：jokylao
 * 修改时间：2017/6/1 22:06
 */
public interface IEmsBwsBscService extends IService<EmsBwsBsc> {

    boolean deleteBySeqNo(String seqNo) throws Exception;
}
