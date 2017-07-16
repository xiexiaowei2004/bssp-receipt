package com.powerbridge.bssp.saspass.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.saspass.dao.SasPassportRltMapper;
import com.powerbridge.bssp.saspass.entity.SasPassportRlt;
import com.powerbridge.bssp.saspass.service.ISasPassportRltService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("sasPassportRltService")
@Transactional
public class SasPassportRltServiceImpl extends BaseServiceImpl<SasPassportRltMapper, SasPassportRlt> implements ISasPassportRltService {

}
