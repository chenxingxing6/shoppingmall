package com.mall.service;

import com.mall.common.share.result.CommonResult;
import com.mall.model.UmsMember;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会员管理Service
 */
public interface UmsMemberService {
    /**
     * 根据用户名获取会员
     */
    UmsMember getByUsername(String username);

    /**
     * 根据会员编号获取会员
     */
    UmsMember getById(Long id);

    /**
     * 用户登录
     */
    String login(String telephone, String smsCode);

    /**
     * 账号登录
     */
    String pwdLogin(String telephone, String pwd);


    /**
     * 用户注册
     */
    @Transactional
    CommonResult registerUser(String mobile, String code, String pwd);

    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 修改密码
     */
    @Transactional
    CommonResult updatePassword(String telephone, String password, String authCode);

    /**
     * 获取当前登录会员
     */
    UmsMember getCurrentMember();

    /**
     * 根据会员id修改会员积分
     */
    void updateIntegration(Long id, Integer integration);

    void loginout(String mobile);
}
