package com.tcl.imooc.miaosha.item.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author hundanli
 * @date  2020-08-22
 * @version 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Item implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private BigDecimal price;

    private String description;

    private Integer sales;

    private String imgUrl;


}
