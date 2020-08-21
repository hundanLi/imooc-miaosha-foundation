package com.tcl.imooc.miaosha.user.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author li
 * @version 1.0
 * @date 2020/8/21 19:32
 */
@Data
public class RegisterVo {
    @NotBlank(message = "用户名不能为空！")
    private String name;
    @NotNull(message = "性别不能为空！")
    @Min(value = 0)
    private Integer gender;
    @NotNull(message = "年龄不能为空！")
    @Min(0)
    private Integer age;
    @NotBlank(message = "手机号码不能为空！")
    private String telephone;
    @NotBlank(message = "登录方式不能为空！")
    private String registerMode;
    @NotBlank(message = "验证码不能为空！")
    private String otpCode;

    @NotBlank(message = "密码不能为空！")
    private String password;
}
