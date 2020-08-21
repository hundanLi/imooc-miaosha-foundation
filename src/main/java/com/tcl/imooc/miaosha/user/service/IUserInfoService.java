package com.tcl.imooc.miaosha.user.service;

import com.tcl.imooc.miaosha.common.error.BusinessException;
import com.tcl.imooc.miaosha.user.entity.UserInfo;
import com.tcl.imooc.miaosha.user.vo.RegisterVo;

import java.security.NoSuchAlgorithmException;

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


    /** 注册逻辑
     * @param registerVo vo
     */
    void register(RegisterVo registerVo) throws NoSuchAlgorithmException, BusinessException;
}
