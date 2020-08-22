package com.tcl.imooc.miaosha.common.error;


/**
 * @author li
 * @version 1.0
 * @date 2020/8/21 13:44
 */
public interface Error {

    /** 错误码
     * @return int
     */
    int getErrorCode();

    /** 错误信息
     * @return String
     */
    String getErrorMsg();

    /** 设置信息
     * @param errorMsg String
     */
    Error setErrorMsg(String errorMsg);
}
