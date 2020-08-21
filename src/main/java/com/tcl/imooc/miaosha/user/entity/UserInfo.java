package com.tcl.imooc.miaosha.user.entity;

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
 * @date  2020-08-21
 * @version 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    /**
     * 1代表男性

     */
    private Integer gender;

    private Integer age;

    private String telephone;

    /**
     * //byphone,bywechat,byalipay,
     */
    private String registerMode;

    private Integer thirdPartyId;


}
