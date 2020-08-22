package com.tcl.imooc.miaosha.user.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author li
 * @version 1.0
 * @date 2020/8/22 13:06
 */
@Data
public class LoginVo {
    @NotBlank
    private String telephone;
    @NotBlank
    private String password;
}
