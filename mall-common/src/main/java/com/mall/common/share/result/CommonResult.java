package com.mall.common.share.result;

import com.mall.common.share.resultcode.IErrorCode;
import com.mall.common.share.resultcode.ResultCode;

import java.io.Serializable;
import java.util.HashMap;

/**
 * User: lanxinghua
 * Date: 2019/10/25 13:47
 * Desc: 通用返回对象
 */
public class CommonResult<T> extends HashMap<String, Object> implements Serializable {

    public CommonResult() {
    }

    public CommonResult(boolean isSucess){
        put("isSucess", isSucess);
        put("code", ResultCode.SUCCESS.getCode());
        put("msg", ResultCode.SUCCESS.getMessage());
        put("data", null);
    }

    protected CommonResult(Integer code, String message, T data) {
        put("isSucess", true);
        put("code", code);
        put("msg", message);
        put("data", data);
    }

    protected CommonResult(boolean isSucess, Integer code, String message, T data) {
        put("isSucess", isSucess);
        put("code", code);
        put("msg", message);
        put("data", data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
        return new CommonResult<T>(false, errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(false, ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    @Override
    public CommonResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
