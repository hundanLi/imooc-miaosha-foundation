package com.tcl.imooc.miaosha.user.controller;


import com.tcl.imooc.miaosha.common.error.BusinessException;
import com.tcl.imooc.miaosha.common.error.ErrorEnum;
import com.tcl.imooc.miaosha.common.response.Result;
import com.tcl.imooc.miaosha.user.controller.vo.TelephoneVo;
import com.tcl.imooc.miaosha.user.entity.UserInfo;
import com.tcl.imooc.miaosha.user.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
@CrossOrigin
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

    @PostMapping(value = "/getotp")
    public Result getOtp(@RequestBody @Valid TelephoneVo telephone, HttpServletRequest request) {
        int otp = ThreadLocalRandom.current().nextInt(100000, 1000_000);
        String otpCode = String.valueOf(otp);
        request.getSession().setAttribute(telephone.getTelephone(), otpCode);
        // 将OTP验证码同对应用户的手机号关联，使用httpSession的方式绑定手机号与OTPCDOE
        log.error("{} ==> {}", telephone, otpCode);
        return Result.success(null);
    }
}

