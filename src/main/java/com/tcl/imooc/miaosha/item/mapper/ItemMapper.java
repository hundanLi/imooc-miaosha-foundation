package com.tcl.imooc.miaosha.item.mapper;

import com.tcl.imooc.miaosha.item.entity.Item;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hundanli
 * @date  2020-08-22
 * @version 0.0.1
 */
@Repository
public interface ItemMapper extends BaseMapper<Item> {

    void increaseSales(@Param("itemId") Integer itemId, @Param("amount") Integer amount);
}
