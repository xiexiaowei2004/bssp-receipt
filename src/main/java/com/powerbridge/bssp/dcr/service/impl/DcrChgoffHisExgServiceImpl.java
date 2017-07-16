package com.powerbridge.bssp.dcr.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.dcr.dao.DcrChgoffHisExgMapper;
import com.powerbridge.bssp.dcr.entity.DcrChgoffHisExg;
import com.powerbridge.bssp.dcr.service.IDcrChgoffHisExgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账册报核成品历史表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
@Service("dcrChgoffHisExgService")
public class DcrChgoffHisExgServiceImpl extends BaseServiceImpl<DcrChgoffHisExgMapper, DcrChgoffHisExg> implements IDcrChgoffHisExgService {

    @Autowired
    private DcrChgoffHisExgMapper dcrChgoffHisExgMapper;

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return Page<DcrChgoffHisExg>
     */
    public Page<DcrChgoffHisExg> selectByList(Page<DcrChgoffHisExg> page, String seqNo) {
        page.setRecords(dcrChgoffHisExgMapper.selectByList(page, seqNo));
        return page;
    }
}
