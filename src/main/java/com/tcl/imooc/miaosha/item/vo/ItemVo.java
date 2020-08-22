package com.tcl.imooc.miaosha.item.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

/**
 * 商品模型类
 *
 * @author li
 * @version 1.0
 * @date 2020/8/22 16:11
 */
@Data
public class ItemVo {

    private Integer id;
    /**
     * 名称
     */
    @NotBlank
    private String title;

    /**
     * 库存
     */
    @Min(0)
    @NotNull
    private Integer stock;
    /**
     * 描述
     */
    @NotBlank
    private String description;
    /**
     * 价格
     */
    @Min(0)
    @NotNull
    private BigDecimal price;
    /**
     * 销量
     */
    @Null
    private Integer sales;
    /**
     * 图片
     */
    @NotBlank
    private String imgUrl;
}
