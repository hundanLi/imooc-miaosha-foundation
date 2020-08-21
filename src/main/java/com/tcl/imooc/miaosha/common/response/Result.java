package com.tcl.imooc.miaosha.common.response;


import lombok.Data;

/**
 * @author li
 * @version 1.0
 * @date 2020/8/21 13:35
 */
@Data
public class Result {

    /**
     *  若status=success，则data内返回前端需要的json数据
     *  若status=fail，则data内使用通用的错误码格式
     */
    private Object data;
    /**
     * 表明对应请求的返回处理结果“success”或“fail”
     */
    private String status;

    public static Result success(Object data) {
        return new Result(data, "success");
    }

    public static Result fail(Object data) {
        return new Result(data, "fail");
    }

    public Result() {
    }

    public Result(Object data, String status) {
        this.data = data;
        this.status = status;
    }

}
