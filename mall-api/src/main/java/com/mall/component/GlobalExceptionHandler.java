package com.mall.component;

import com.mall.common.exception.BizException;
import com.mall.common.share.result.CommonResult;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * User: lanxinghua
 * Date: 2019/10/26 14:15
 * Desc: 全局异常拦截器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BizException.class)
    public CommonResult handleRRException(BizException e){
       CommonResult r = new CommonResult(false);
       r.put("code", e.getCode());
       r.put("msg", e.getMsg());
       return r;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public CommonResult handleDuplicateKeyException(DuplicateKeyException e){
        CommonResult r = new CommonResult(false);
        r.put("msg", "数据库中已存在该记录");
        return r;
    }

    @ExceptionHandler(value = Exception.class)
    public CommonResult<String> exceptionHandler(HttpServletRequest request, Exception e){
        CommonResult r = new CommonResult(false);
        r.put("msg", e.getMessage());
        return r;
    }
}
