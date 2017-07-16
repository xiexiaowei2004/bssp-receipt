package com.powerbridge.bssp.edi.service.impl;


import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.edi.dao.EdiRoutingInfoMapper;
import com.powerbridge.bssp.edi.entity.EdiRoutingInfo;
import com.powerbridge.bssp.edi.service.IEdiRoutingInfoService;
import org.springframework.stereotype.Service;

@Service("ediRoutingInfoService")
public class EdiRoutingInfoServiceImpl extends BaseServiceImpl<EdiRoutingInfoMapper, EdiRoutingInfo> implements IEdiRoutingInfoService {


}
