package com.powerbridge.bssp.inv.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.inv.entity.InvBsc;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 保税核注清单表  Mapper 接口
 * </p>
 *
 * @author zsl
 * @since 2017-06-03
 */
@Repository("invBscMapper")
public interface InvBscMapper extends BaseMapper<InvBsc> {

}