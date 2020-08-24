package com.tcl.imooc.miaosha.order.service.impl;

import com.tcl.imooc.miaosha.common.error.BusinessException;
import com.tcl.imooc.miaosha.common.error.ErrorEnum;
import com.tcl.imooc.miaosha.item.service.IItemService;
import com.tcl.imooc.miaosha.item.vo.ItemVo;
import com.tcl.imooc.miaosha.order.entity.OrderInfo;
import com.tcl.imooc.miaosha.order.entity.SequenceInfo;
import com.tcl.imooc.miaosha.order.mapper.OrderInfoMapper;
import com.tcl.imooc.miaosha.order.mapper.SequenceInfoMapper;
import com.tcl.imooc.miaosha.order.service.IOrderInfoService;
import com.tcl.imooc.miaosha.order.vo.OrderVo;
import com.tcl.imooc.miaosha.user.entity.UserInfo;
import com.tcl.imooc.miaosha.user.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hundanli
 * @version 0.0.1
 * @date 2020-08-24
 */
@Service
public class OrderInfoServiceImpl implements IOrderInfoService {

    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Autowired
    SequenceInfoMapper sequenceInfoMapper;

    @Autowired
    IUserInfoService userInfoService;

    @Autowired
    IItemService itemService;


    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public OrderInfo createItem(OrderVo orderVo) throws BusinessException {
        // 判断用户是否，订单号是否合法
        UserInfo userInfo = userInfoService.getById(orderVo.getUserId());
        if (userInfo == null) {
            throw new BusinessException(ErrorEnum.PARAMETER_INVALID.setErrorMsg("用户不存在！"));
        }
        ItemVo itemVo = itemService.getItemById(orderVo.getItemId());
        if (itemVo == null) {
            throw new BusinessException(ErrorEnum.PARAMETER_INVALID.setErrorMsg("商品不存在！"));
        }
        // 商品减库存
        itemService.decreaseStock(orderVo.getItemId(), orderVo.getAmount());


        // 订单入库
        OrderInfo orderInfo = new OrderInfo();
        // 生成交易流水号（订单ID）
        orderInfo.setId(generateOrderId());
        orderInfo.setUserId(userInfo.getId());
        orderInfo.setItemId(itemVo.getId());
        orderInfo.setItemPrice(itemVo.getPrice());
        orderInfo.setAmount(orderVo.getAmount());
        orderInfo.setOrderPrice(itemVo.getPrice().multiply(BigDecimal.valueOf(orderVo.getAmount())));
        orderInfoMapper.insert(orderInfo);

        // 修改商品销量
        itemService.increaseSales(itemVo.getId(), orderInfo.getAmount());

        return orderInfo;
    }


    /**
     * @return 订单号
     */
    private String generateOrderId() {
        // 订单号包含16位
        StringBuilder orderId = new StringBuilder();
        // 前8位是时间：年月日
        String prefix = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE).replace("-", "");
        orderId.append(prefix);

        // 中间6位是递增序列
        SequenceInfo sequenceInfo = sequenceInfoMapper.selectByName("order_info");
        Integer currentValue = sequenceInfo.getCurrentValue();
        String mid = currentValue.toString();
        for (int i = 0; i < 6 - mid.length(); i++) {
            orderId.append('0');
        }
        orderId.append(mid);

        //最后2位是数据库分库分表信息，保留
        orderId.append("00");
        return orderId.toString();
    }
}
