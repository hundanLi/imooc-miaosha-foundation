package com.tcl.imooc.miaosha.order.mapper;

import com.tcl.imooc.miaosha.order.entity.SequenceInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hundanli
 * @date  2020-08-24
 * @version 0.0.1
 */
@Repository
public interface SequenceInfoMapper extends BaseMapper<SequenceInfo> {

    SequenceInfo selectByName(@Param("name") String name);
}
