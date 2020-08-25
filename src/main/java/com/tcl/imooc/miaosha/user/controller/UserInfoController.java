package com.tcl.imooc.miaosha.user.controller;


import com.tcl.imooc.miaosha.common.error.BusinessException;
import com.tcl.imooc.miaosha.common.error.ErrorEnum;
import com.tcl.imooc.miaosha.common.response.Result;
import com.tcl.imooc.miaosha.user.vo.LoginVo;
import com.tcl.imooc.miaosha.user.vo.RegisterVo;
import com.tcl.imooc.miaosha.user.vo.TelephoneVo;
import com.tcl.imooc.miaosha.user.entity.UserInfo;
import com.tcl.imooc.miaosha.user.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
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
@CrossOrigin(allowCredentials = "true")
@Slf4j
@RestController
@RequestMapping("/user")
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
        return Result.success("发送成功");
    }

    @PostMapping("/register")
    public Result register(@RequestBody @Valid RegisterVo registerVo, HttpServletRequest request) throws BusinessException, NoSuchAlgorithmException {
        String otpCode = registerVo.getOtpCode();
        String sessionOtp = (String) request.getSession().getAttribute(registerVo.getTelephone());
        if (!StringUtils.equals(otpCode, sessionOtp)) {
            throw new BusinessException(ErrorEnum.PARAMETER_INVALID, ErrorEnum.PARAMETER_INVALID.getErrorMsg());
        }
        service.register(registerVo);

        return Result.success("注册成功");
    }


    @PostMapping("/login")
    public Result login(@RequestBody @Valid LoginVo loginVo, HttpServletRequest request) throws NoSuchAlgorithmException, BusinessException {
        UserInfo userInfo = service.login(loginVo);
        request.getSession().setAttribute("IS_LOGIN", true);
        request.getSession().setAttribute("LOGIN_USER", userInfo);
        return Result.success("登录成功");
    }

}

