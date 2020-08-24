package com.tcl.imooc.miaosha.order.service;

import com.tcl.imooc.miaosha.common.error.BusinessException;
import com.tcl.imooc.miaosha.order.entity.OrderInfo;
import com.tcl.imooc.miaosha.order.vo.OrderVo;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hundanli
 * @date  2020-08-24
 * @version 0.0.1
 */
public interface IOrderInfoService {

    @Transactional
    OrderInfo createItem(OrderVo orderVo) throws BusinessException;

}
