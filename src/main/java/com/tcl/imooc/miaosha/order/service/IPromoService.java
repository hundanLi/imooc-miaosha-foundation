package com.tcl.imooc.miaosha.order.service;

import com.tcl.imooc.miaosha.order.vo.PromoVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hundanli
 * @date  2020-08-24
 * @version 0.0.1
 */
public interface IPromoService {
    PromoVo getPromoByItemId(Integer itemId);
}
