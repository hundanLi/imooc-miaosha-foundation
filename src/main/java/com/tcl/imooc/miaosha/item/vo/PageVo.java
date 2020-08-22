package com.tcl.imooc.miaosha.item.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author li
 * @version 1.0
 * @date 2020/8/22 17:21
 */
@Data
public class PageVo {
    @Min(1)
    @NotNull
    Integer pageNum;
    @Min(5)
    @NotNull
    Integer pageSize;
}
