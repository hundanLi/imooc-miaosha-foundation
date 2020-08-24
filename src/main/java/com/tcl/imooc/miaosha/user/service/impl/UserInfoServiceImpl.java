package com.tcl.imooc.miaosha.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tcl.imooc.miaosha.common.error.BusinessException;
import com.tcl.imooc.miaosha.common.error.ErrorEnum;
import com.tcl.imooc.miaosha.user.entity.UserInfo;
import com.tcl.imooc.miaosha.user.entity.UserPassword;
import com.tcl.imooc.miaosha.user.mapper.UserInfoMapper;
import com.tcl.imooc.miaosha.user.mapper.UserPasswordMapper;
import com.tcl.imooc.miaosha.user.service.IUserInfoService;
import com.tcl.imooc.miaosha.user.vo.LoginVo;
import com.tcl.imooc.miaosha.user.vo.RegisterVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    UserInfoMapper userInfoMapper;

    @Autowired
    UserPasswordMapper userPasswordMapper;

    @Override
    public UserInfo getById(Integer id) {
        return userInfoMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void register(RegisterVo registerVo) throws NoSuchAlgorithmException, BusinessException {
        // 1.保存用户信息
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(registerVo, userInfo);
        try {
            userInfoMapper.insert(userInfo);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(ErrorEnum.PARAMETER_INVALID.setErrorMsg("手机号码已经被注册"));
        }

        // 2.密码加密+保存
        UserPassword userPassword = new UserPassword();
        userPassword.setUserId(userInfo.getId());
        userPassword.setEncryptPassword(encryptPassword(registerVo.getPassword()));
        userPasswordMapper.insert(userPassword);

    }

    @Override
    public UserInfo login(LoginVo loginVo) throws BusinessException, NoSuchAlgorithmException {
        // 检查用户是否已经注册
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getTelephone, loginVo.getTelephone());
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        if (userInfo == null) {
            throw new BusinessException(ErrorEnum.DATA_NOT_EXIST.setErrorMsg("该手机号码尚未注册！"));
        }
        // 校验密码是否正确
        LambdaQueryWrapper<UserPassword> passwordWrapper = new LambdaQueryWrapper<>();
        passwordWrapper.eq(UserPassword::getUserId, userInfo.getId());
        passwordWrapper.eq(UserPassword::getEncryptPassword, encryptPassword(loginVo.getPassword()));
        if (userPasswordMapper.selectCount(passwordWrapper) < 1) {
            throw new BusinessException(ErrorEnum.PASSWORD_INCORRECT);
        }
        return userInfo;
    }

    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(digest.digest(password.getBytes()));
    }

}
