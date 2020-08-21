package com.tcl.imooc.miaosha.common.handler;

import com.tcl.imooc.miaosha.common.error.BusinessException;
import com.tcl.imooc.miaosha.common.error.ErrorEnum;
import com.tcl.imooc.miaosha.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @author li
 * @version 1.0
 * @date 2020/8/21 14:03
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("业务调用抛出异常: ", e.getCause());
        HashMap<String, Object> data = new HashMap<>(2);
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            data.put("errorCode", businessException.getErrorCode());
            data.put("errorMsg", businessException.getErrorMsg());
        } else {
            data.put("errorCode", ErrorEnum.UNKNOWN_ERROR.getErrorCode());
            data.put("errorMsg", ErrorEnum.UNKNOWN_ERROR.getErrorMsg());
        }
        return Result.fail(data);
    }

}
