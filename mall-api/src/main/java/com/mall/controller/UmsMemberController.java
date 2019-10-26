package com.mall.controller;

import cn.hutool.core.util.StrUtil;
import com.mall.annotation.User;
import com.mall.common.exception.BizException;
import com.mall.common.share.result.CommonResult;
import com.mall.model.UmsMember;
import com.mall.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 会员登录注册管理Controller
 */
@Controller
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
@RequestMapping("/sso")
public class UmsMemberController {
    @Autowired
    private UmsMemberService memberService;

    @ApiOperation("注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(@RequestParam String mobile,
                                 @RequestParam String code,
                                 @RequestParam String pwd){
        return memberService.registerUser(mobile, code, pwd);
    }

    @ApiOperation("登录以后返回token")
    @RequestMapping(value = "/smsLogin", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult smsLogin(@RequestParam String telephone,
                                 @RequestParam String smsCode) {
        if (StrUtil.isEmpty(telephone)){
            throw new BizException("手机号不能为空");
        }
        if (StrUtil.isEmpty(smsCode)){
            throw new BizException("验证码不能为空");
        }
        String token = memberService.login(telephone, smsCode);
        if (token == null) {
            throw new BizException("登陆失败");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("账号登录以后返回token")
    @RequestMapping(value = "/pwdLogin", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult pwdLogin(@RequestParam String telephone,
                                 @RequestParam String pwd) {
        if (StrUtil.isEmpty(telephone)){
            throw new BizException("手机号不能为空");
        }
        if (StrUtil.isEmpty(pwd)){
            throw new BizException("密码不能为空");
        }
        String token = memberService.pwdLogin(telephone, pwd);
        if (token == null) {
            throw new BizException("登陆失败");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("获取用户信息")
    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getUserInfo(@User UmsMember umsMember) {
        umsMember.setPassword(null);
        return CommonResult.success(umsMember);
    }

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAuthCode(@RequestParam String telephone) {
        return memberService.generateAuthCode(telephone);
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@RequestParam String telephone,
                                 @RequestParam String password,
                                 @RequestParam String authCode) {
        return memberService.updatePassword(telephone,password,authCode);
    }

    @ApiOperation("退出登录")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout(@User UmsMember umsMember) {
        memberService.loginout(umsMember.getPhone());
        return CommonResult.success(true);
    }
}
