package com.tcl.imooc.miaosha.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tcl.imooc.miaosha.item.entity.ItemStock;
import com.tcl.imooc.miaosha.item.mapper.ItemStockMapper;
import com.tcl.imooc.miaosha.item.service.IItemStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hundanli
 * @version 0.0.1
 * @date 2020-08-22
 */
@Service
public class ItemStockServiceImpl implements IItemStockService {
    @Autowired
    ItemStockMapper stockMapper;

    @Override
    public ItemStock selectByItemId(Integer itemId) {
        LambdaQueryWrapper<ItemStock> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ItemStock::getItemId, itemId);
        return stockMapper.selectOne(queryWrapper);
    }
}
