package com.tcl.imooc.miaosha.order.vo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author li
 * @version 1.0
 * @date 2020/8/24 9:53
 */
@Data
public class OrderVo {
    @NotNull
    @Min(1)
    private Integer itemId;
    @Min(1)
    private Integer userId;
    @NotNull
    @Min(1)
    @Max(3)
    private Integer amount;

    @Min(1)
    private Integer promoId;
}
