package com.tcl.imooc.miaosha.user.service;

import com.tcl.imooc.miaosha.user.entity.UserInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hundanli
 * @date  2020-08-21
 * @version 0.0.1
 */
public interface IUserInfoService {

    /** 根据id查询
     * @param id id
     * @return UserInfo
     */
    UserInfo getById(Integer id);
}
