package com.mall.component;

import com.mall.common.exception.BizException;
import com.mall.common.share.result.CommonResult;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * User: lanxinghua
 * Date: 2019/10/26 14:15
 * Desc: 全局异常拦截器
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BizException.class)
    public CommonResult handleRRException(BizException e){
       return CommonResult.failed(e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public CommonResult handleDuplicateKeyException(DuplicateKeyException e){
        return CommonResult.failed("数据库中已存在该记录");
    }

    @ExceptionHandler(value = Exception.class)
    public CommonResult<String> exceptionHandler(HttpServletRequest request, Exception e){
        return CommonResult.failed(e.getMessage());
    }
}
