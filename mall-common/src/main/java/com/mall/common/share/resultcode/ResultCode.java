package com.mall.common.share.resultcode;

/**
 * User: lanxinghua
 * Date: 2019/10/25 13:43
 * Desc: API操作码
 */
public enum  ResultCode implements IErrorCode{
    SUCCESS(200, "操作成功"),
    FAILED(500, "未知异常，请联系管理员"),
    ;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
