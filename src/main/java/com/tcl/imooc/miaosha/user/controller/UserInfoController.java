package com.tcl.imooc.miaosha.user.controller;


import com.tcl.imooc.miaosha.user.entity.UserInfo;
import com.tcl.imooc.miaosha.user.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hundanli
 * @date  2020-08-21
 * @version 0.0.1
 */
@RestController
@RequestMapping("/user/user-info")
public class UserInfoController {

    @Autowired
    IUserInfoService service;

    @GetMapping("")
    public UserInfo queryById(@RequestParam(name = "id") Integer id) {
        return service.getById(id);
    }

}

