package com.mall.service.impl;

import com.mall.common.exception.BizException;
import com.mall.common.share.result.CommonResult;
import com.mall.mapper.UmsMemberLevelMapper;
import com.mall.mapper.UmsMemberMapper;
import com.mall.model.UmsMember;
import com.mall.model.UmsMemberExample;
import com.mall.model.UmsMemberLevel;
import com.mall.model.UmsMemberLevelExample;
import com.mall.service.RedisService;
import com.mall.service.UmsMemberService;
import com.mall.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 会员管理Service实现类
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    private UmsMemberMapper memberMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UmsMemberLevelMapper memberLevelMapper;
    @Autowired
    private RedisService redisService;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public UmsMember getByUsername(String username) {
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(memberList)) {
            return memberList.get(0);
        }
        return null;
    }

    @Override
    public UmsMember getById(Long id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public String login(String telephone, String smsCode) {
        String token = null;
        //验证验证码
        if (!verifyAuthCode(smsCode, telephone)) {
            throw new BizException("验证码错误");
        }
        UmsMemberExample example = new UmsMemberExample();
        UmsMemberExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(telephone).andStatusEqualTo(1);
        List<UmsMember> umsMembers = memberMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(umsMembers)) {
            throw new BizException("手机号不存在");
        }
        UmsMember umsMember = umsMembers.get(0);
        //密码需要客户端加密后传递
        try {
            token = jwtTokenUtil.generateToken(umsMember.getUsername());
            return token;
        } catch (Exception e) {
            throw new BizException("登陆失败");
        }
    }

    @Override
    public String pwdLogin(String telephone, String pwd) {
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andPhoneEqualTo(telephone).andPasswordEqualTo(pwd);
        List<UmsMember> umsMembers = memberMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(umsMembers)) {
            throw new BizException("账号错误");
        }
        UmsMember umsMember = umsMembers.get(0);
        try {
            return jwtTokenUtil.generateToken(umsMember.getUsername());
        } catch (Exception e) {
            throw new BizException("登陆失败");
        }
    }

    @Override
    public CommonResult registerUser(String mobile, String code, String pwd) {
        //验证验证码
        if(!verifyAuthCode(code,mobile)){
            return CommonResult.failed("验证码错误");
        }
        //查询是否已有该用户
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andPhoneEqualTo(mobile);
        List<UmsMember> umsMembers = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(umsMembers)) {
            return CommonResult.failed("该用户已经存在");
        }
        //没有该用户进行添加操作
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(mobile);
        umsMember.setPhone(mobile);
        umsMember.setPassword(pwd);
        umsMember.setCreateTime(new Date());
        umsMember.setStatus(1);
        //获取默认会员等级并设置
        UmsMemberLevelExample levelExample = new UmsMemberLevelExample();
        levelExample.createCriteria().andDefaultStatusEqualTo(1);
        List<UmsMemberLevel> memberLevelList = memberLevelMapper.selectByExample(levelExample);
        if (!CollectionUtils.isEmpty(memberLevelList)) {
            umsMember.setMemberLevelId(memberLevelList.get(0).getId());
        }
        memberMapper.insert(umsMember);
        umsMember.setPassword(null);
        return CommonResult.success(null,"注册成功");
    }

    @Override
    public CommonResult generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<6;i++){
            sb.append(random.nextInt(10));
        }
        //验证码绑定手机号并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE+telephone,sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE+telephone,AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(),"获取验证码成功");
    }

    @Override
    public CommonResult updatePassword(String telephone, String password, String authCode) {
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andPhoneEqualTo(telephone);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(memberList)){
            return CommonResult.failed("该账号不存在");
        }
        //验证验证码
        if(!verifyAuthCode(authCode,telephone)){
            return CommonResult.failed("验证码错误");
        }
        UmsMember umsMember = memberList.get(0);
        umsMember.setPassword(password);
        memberMapper.updateByPrimaryKeySelective(umsMember);
        return CommonResult.success(null,"密码修改成功");
    }

    @Override
    public UmsMember getCurrentMember() {
       /* SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        MemberDetails memberDetails = (MemberDetails) auth.getPrincipal();
        return memberDetails.getUmsMember();*/
       return null;
    }

    @Override
    public void updateIntegration(Long id, Integer integration) {
        UmsMember record=new UmsMember();
        record.setId(id);
        record.setIntegration(integration);
        memberMapper.updateByPrimaryKeySelective(record);
    }

    //对输入的验证码进行校验
    private boolean verifyAuthCode(String authCode, String telephone){
        if(StringUtils.isEmpty(authCode)){
            return false;
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        return authCode.equals(realAuthCode);
    }

    @Override
    public void loginout(String mobile) {
        redisService.remove(REDIS_KEY_PREFIX_AUTH_CODE+mobile);
    }
}
