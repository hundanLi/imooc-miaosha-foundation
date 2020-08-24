package com.tcl.imooc.miaosha.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author hundanli
 * @date  2020-08-24
 * @version 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Promo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String promoName;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer itemId;

    private BigDecimal promoItemPrice;


}
