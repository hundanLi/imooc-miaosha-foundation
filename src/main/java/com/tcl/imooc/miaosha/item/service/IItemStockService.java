package com.tcl.imooc.miaosha.item.service;

import com.tcl.imooc.miaosha.item.entity.ItemStock;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hundanli
 * @date  2020-08-22
 * @version 0.0.1
 */
public interface IItemStockService {

    ItemStock selectByItemId(Integer itemId);

}
