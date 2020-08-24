package com.tcl.imooc.miaosha.order.controller;


import com.tcl.imooc.miaosha.common.error.BusinessException;
import com.tcl.imooc.miaosha.common.error.ErrorEnum;
import com.tcl.imooc.miaosha.common.response.Result;
import com.tcl.imooc.miaosha.order.entity.OrderInfo;
import com.tcl.imooc.miaosha.order.service.IOrderInfoService;
import com.tcl.imooc.miaosha.order.vo.OrderVo;
import com.tcl.imooc.miaosha.user.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hundanli
 * @version 0.0.1
 * @date 2020-08-24
 */
@RestController
@RequestMapping("/order")
public class OrderInfoController {

    @Autowired
    IOrderInfoService orderInfoService;

    @PostMapping("/createorder")
    public Result createOrder(@RequestBody @Valid OrderVo orderVo, HttpServletRequest request) throws BusinessException {
        // 验证是否登录
        Boolean isLogin = (Boolean) request.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin) {
            throw new BusinessException(ErrorEnum.USER_NOT_LOGIN);
        }
        UserInfo user = (UserInfo) request.getSession().getAttribute("LOGIN_USER");
        orderVo.setUserId(user.getId());

        OrderInfo orderInfo = orderInfoService.createItem(orderVo);
        return Result.success(orderInfo);
    }

}

