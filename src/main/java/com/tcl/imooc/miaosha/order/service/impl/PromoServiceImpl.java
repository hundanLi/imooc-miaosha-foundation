package com.tcl.imooc.miaosha.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tcl.imooc.miaosha.order.entity.Promo;
import com.tcl.imooc.miaosha.order.mapper.PromoMapper;
import com.tcl.imooc.miaosha.order.service.IPromoService;
import com.tcl.imooc.miaosha.order.vo.PromoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hundanli
 * @date  2020-08-24
 * @version 0.0.1
 */
@Service
public class PromoServiceImpl implements IPromoService {

    @Autowired
    PromoMapper mapper;

    @Override
    public PromoVo getPromoByItemId(Integer itemId) {
        LambdaQueryWrapper<Promo> queryWrapper = new LambdaQueryWrapper<>();
        Promo promo = mapper.selectOne(queryWrapper.eq(Promo::getItemId, itemId));
        if (promo == null) {
            return null;
        }
        PromoVo promoVo = new PromoVo();

        BeanUtils.copyProperties(promo, promoVo);

        // 设置促销活动状态
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(promo.getEndDate())) {
            promoVo.setStatus(3);
        } else if (now.isAfter(promo.getStartDate())) {
            promoVo.setStatus(2);
        } else {
            promoVo.setStatus(1);
        }
        return promoVo;
    }
}
