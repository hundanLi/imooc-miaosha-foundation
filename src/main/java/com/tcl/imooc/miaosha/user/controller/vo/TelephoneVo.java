package com.tcl.imooc.miaosha.user.controller.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author li
 * @version 1.0
 * @date 2020/8/21 15:56
 */
@Data
public class TelephoneVo {

    @NotBlank
    private String telephone;
}
