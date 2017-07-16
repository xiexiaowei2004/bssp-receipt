package com.powerbridge.bssp.dcr.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.dcr.entity.DcrChgoffHisInvtLt;

import java.util.List;

/**
 * <p>
 * 账册报核清单历史表 Mapper 接口
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
public interface DcrChgoffHisInvtLtMapper extends BaseMapper<DcrChgoffHisInvtLt> {
    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return List<DcrChgoffHisInvtLt>
     */
    List<DcrChgoffHisInvtLt> selectByList(Page<DcrChgoffHisInvtLt> page, String seqNo);
}