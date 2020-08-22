package com.tcl.imooc.miaosha.item.controller;


import com.tcl.imooc.miaosha.common.response.Result;
import com.tcl.imooc.miaosha.item.service.IItemService;
import com.tcl.imooc.miaosha.item.vo.ItemVo;
import com.tcl.imooc.miaosha.item.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hundanli
 * @date  2020-08-22
 * @version 0.0.1
 */
@CrossOrigin(origins = "*", allowCredentials = "true")
@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    IItemService service;

    @PostMapping("/create")
    public Result createItem(@RequestBody @Valid ItemVo itemVo) {
        itemVo = service.createItem(itemVo);
        return Result.success(itemVo);
    }

    @GetMapping("/get")
    public Result getItemById(@RequestParam(name = "id") Integer id) {
        return Result.success(service.getItemById(id));
    }

    @PostMapping("/list")
    public Result getItemList(@RequestBody @Valid PageVo pageVo) {
        return Result.success(service.listItem(pageVo.getPageNum(), pageVo.getPageSize()));
    }
}

