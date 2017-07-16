package com.powerbridge.bssp.dcr.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.dcr.entity.DcrAdjaccHisAcmpFormDt;

import java.util.List;

/**
 * <p>
 * 账册核销随附单证明细历史表 Mapper 接口
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
public interface DcrAdjaccHisAcmpFormDtMapper extends BaseMapper<DcrAdjaccHisAcmpFormDt> {

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return List<DcrAdjaccHisAcmpFormDt>
     */
    List<DcrAdjaccHisAcmpFormDt> selectByList(Page<DcrAdjaccHisAcmpFormDt> page, String seqNo);
}