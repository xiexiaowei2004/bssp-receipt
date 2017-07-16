package com.powerbridge.bssp.dcr.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.dcr.entity.DcrChgoffHisImg;

import java.util.List;

/**
 * <p>
 * 账册报核料件历史表 Mapper 接口
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
public interface DcrChgoffHisImgMapper extends BaseMapper<DcrChgoffHisImg> {

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return List<DcrChgoffHisImg>
     */
    List<DcrChgoffHisImg> selectByList(Page<DcrChgoffHisImg> page, String seqNo);
}