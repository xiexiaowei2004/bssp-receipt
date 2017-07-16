package com.powerbridge.bssp.edi.service;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.edi.entity.EdiXmlMapDb;

import java.util.Map;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：ICodStdAreaCodeService
 * 类描述：CodStdAreaCode表业务逻辑层接口
 * 创建人：xwq
 * 创建时间：2017年5月10日19:21:36
 */
public interface IEdiXmlMapDbService extends IService<EdiXmlMapDb> {
    Map<String, Map> getXmlMapDb() throws Exception;
}
  
    