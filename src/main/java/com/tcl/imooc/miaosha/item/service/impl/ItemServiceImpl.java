package com.tcl.imooc.miaosha.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tcl.imooc.miaosha.item.entity.Item;
import com.tcl.imooc.miaosha.item.entity.ItemStock;
import com.tcl.imooc.miaosha.item.mapper.ItemMapper;
import com.tcl.imooc.miaosha.item.mapper.ItemStockMapper;
import com.tcl.imooc.miaosha.item.service.IItemService;
import com.tcl.imooc.miaosha.item.service.IItemStockService;
import com.tcl.imooc.miaosha.item.vo.ItemVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
public class ItemServiceImpl implements IItemService {

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    ItemStockMapper stockMapper;

    @Autowired
    IItemStockService stockService;

    @Override
    public ItemVo createItem(ItemVo itemVo) {
        Item item = new Item();
        BeanUtils.copyProperties(itemVo, item);
        itemMapper.insert(item);

        ItemStock itemStock = new ItemStock();
        itemStock.setItemId(item.getId());
        itemStock.setStock(itemVo.getStock());
        stockMapper.insert(itemStock);
        itemVo.setId(item.getId());

        return itemVo;
    }

    @Override
    public IPage<ItemVo> listItem(Integer pageNum, Integer pageSize) {
        List<ItemVo> itemVos;
        Page<Item> page = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<Item> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Item::getSales);
        Page<Item> items = itemMapper.selectPage(page, queryWrapper);

        itemVos = items.getRecords().stream().map(new Function<Item, ItemVo>() {
            @Override
            public ItemVo apply(Item item) {
                // 属性拷贝
                ItemVo itemVo = new ItemVo();
                BeanUtils.copyProperties(item, itemVo);

                // 查询库存
                LambdaQueryWrapper<ItemStock> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(ItemStock::getItemId, item.getId());
                ItemStock itemStock = stockMapper.selectOne(queryWrapper);
                itemVo.setStock(itemStock.getStock());

                return itemVo;
            }
        }).collect(Collectors.toList());

        // 构造返回
        Page<ItemVo> voPage = new Page<>();
        voPage.setRecords(itemVos);
        return voPage;
    }

    @Override
    public ItemVo getItemById(Integer itemId) {
        // 查询基本信息
        ItemVo itemVo = new ItemVo();
        Item item = itemMapper.selectById(itemId);
        BeanUtils.copyProperties(item, itemVo);

        // 查询库存信息
        ItemStock itemStock = stockService.selectByItemId(itemId);
        itemVo.setStock(itemStock.getStock());

        return itemVo;
    }
}
