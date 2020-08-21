package com.tcl.imooc.miaosha.common.error;

/**
 * @author li
 * @version 1.0
 * @date 2020/8/21 13:54
 *
 * 使用包装器模式设计业务异常类
 */
public class BusinessException extends Exception implements Error{

    private final Error error;


    public BusinessException() {
        this.error = ErrorEnum.UNKNOWN_ERROR;
    }

    /**
     * @param error 包装通用错误类构造异常实例
     */
    public BusinessException(Error error) {
        super();
        this.error = error;
    }

    public BusinessException(Error error, String msg) {
        super();
        this.error = error;
        this.setErrorMsg(msg);
    }


    @Override
    public int getErrorCode() {
        return error.getErrorCode();
    }

    @Override
    public String getErrorMsg() {
        return error.getErrorMsg();
    }

    @Override
    public Error setErrorMsg(String msg) {
        this.error.setErrorMsg(msg);
        return this;
    }
}
