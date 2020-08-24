package com.tcl.imooc.miaosha.order.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author li
 * @version 1.0
 * @date 2020/8/24 13:55
 */
@Data
public class PromoVo {
    private Integer id;

    private String promoName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    private Integer itemId;

    private BigDecimal promoItemPrice;


    /**
     * 促销活动状态：1表示未开始，2表示进行中，3表示已结束
     */
    private Integer status;
}
