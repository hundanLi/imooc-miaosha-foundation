package com.tcl.imooc.miaosha.common.error;

/**
 * @author li
 * @version 1.0
 * @date 2020/8/21 13:47
 */
public enum ErrorEnum implements Error {
    /**
     * 枚举实例
     */
    DATA_INVALID(100001, "参数不合法"),
    DATA_NOT_EXIST(400001, "数据不存在"),
    PASSWORD_INCORRECT(400002, "输入密码错误"),
    UNKNOWN_ERROR(500000, "未知错误")
    ;

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public Error setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    private final int errorCode;
    private String errorMsg;

    ErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
