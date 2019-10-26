package com.mall.interceptor;

import cn.hutool.core.util.StrUtil;
import com.mall.common.exception.BizException;
import com.mall.mapper.UmsMemberMapper;
import com.mall.model.UmsMember;
import com.mall.service.UmsMemberService;
import com.mall.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: lanxinghua
 * Date: 2019/10/26 15:47
 * Desc: 权限(Token)验证
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    public static final String USER_KEY = "userId";
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UmsMemberService umsMemberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equalsIgnoreCase("OPTIONS")){
            return true;
        }
        //从header中获取token
        String token = request.getHeader("token");
        if (token.equalsIgnoreCase("undefined")){
            return true;
        }
        //如果header中不存在token，则从参数中获取token
        if(StrUtil.isBlank(token)){
            token = request.getParameter("token");
        }
        //token为空
        if(StrUtil.isBlank(token)){
            throw new BizException("token不能为空");
        }
        //查询token信息
        String userName = jwtTokenUtil.getUserNameFromToken(token);
        if (!jwtTokenUtil.validateToken(token, userName)){
            throw new BizException("token失效，请重新登录");
        }
        UmsMember umsMember = umsMemberService.getByUsername(userName);
        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(USER_KEY, umsMember);
        return true;
    }
}
