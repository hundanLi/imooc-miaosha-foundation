package com.tcl.imooc.miaosha.user.controller;


import com.tcl.imooc.miaosha.common.error.BusinessException;
import com.tcl.imooc.miaosha.common.error.ErrorEnum;
import com.tcl.imooc.miaosha.common.response.Result;
import com.tcl.imooc.miaosha.user.entity.UserInfo;
import com.tcl.imooc.miaosha.user.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hundanli
 * @date  2020-08-21
 * @version 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/user/user-info")
public class UserInfoController {

    @Autowired
    IUserInfoService service;

    @GetMapping("")
    public Result queryById(@RequestParam(name = "id") Integer id) throws BusinessException{
        UserInfo userInfo = service.getById(id);
        if (userInfo == null) {
            throw new BusinessException(ErrorEnum.DATA_NOT_EXIST);
        }
        return Result.success(userInfo);
    }
    
}

