package com.tcl.imooc.miaosha.order.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class SequenceInfo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.INPUT)
    private String name;

    private Integer currentValue;

    private Integer step;


}
