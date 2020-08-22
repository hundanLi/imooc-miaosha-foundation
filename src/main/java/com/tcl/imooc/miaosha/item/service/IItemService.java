package com.tcl.imooc.miaosha.item.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tcl.imooc.miaosha.item.vo.ItemVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hundanli
 * @date  2020-08-22
 * @version 0.0.1
 */
public interface IItemService {

    ItemVo createItem(ItemVo itemVo);

    IPage<ItemVo> listItem(Integer pageNum, Integer pageSize);

    ItemVo getItemById(Integer itemId);

}
