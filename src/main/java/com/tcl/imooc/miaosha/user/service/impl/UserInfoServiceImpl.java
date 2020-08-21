package com.tcl.imooc.miaosha.user.service.impl;

import com.tcl.imooc.miaosha.user.entity.UserInfo;
import com.tcl.imooc.miaosha.user.mapper.UserInfoMapper;
import com.tcl.imooc.miaosha.user.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hundanli
 * @date  2020-08-21
 * @version 0.0.1
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    UserInfoMapper mapper;

    @Override
    public UserInfo getById(Integer id) {
        return mapper.selectById(id);
    }

}
