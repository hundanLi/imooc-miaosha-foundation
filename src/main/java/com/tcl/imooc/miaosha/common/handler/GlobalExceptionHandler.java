package com.tcl.imooc.miaosha.common.handler;

import com.tcl.imooc.miaosha.common.error.BusinessException;
import com.tcl.imooc.miaosha.common.error.ErrorEnum;
import com.tcl.imooc.miaosha.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 * @author li
 * @version 1.0
 * @date 2020/8/21 14:03
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleException(MethodArgumentNotValidException e) {
        log.error("参数校验错误: {}", e.getMessage());
        HashMap<String, Object> data = new HashMap<>(2);
        e.getBindingResult().getFieldErrors().forEach(new Consumer<FieldError>() {
            @Override
            public void accept(FieldError fieldError) {
                data.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        });
        return Result.fail(data);
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleException(HttpMessageNotReadableException e) {
        log.error("参数转换错误: {}", e.getMessage());
        HashMap<String, Object> data = new HashMap<>(2);
        data.put("errorCode", ErrorEnum.PARAMETER_INVALID.getErrorCode());
        data.put("errorMsg", ErrorEnum.PARAMETER_INVALID.getErrorMsg());
        return Result.fail(data);
    }


    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        HashMap<String, Object> data = new HashMap<>(2);
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            log.error("业务调用异常: {}", ((BusinessException) e).getErrorMsg());
            data.put("errorCode", businessException.getErrorCode());
            data.put("errorMsg", businessException.getErrorMsg());
        } else {
            log.error("未知异常：", e);
            data.put("errorCode", ErrorEnum.UNKNOWN_ERROR.getErrorCode());
            data.put("errorMsg", ErrorEnum.UNKNOWN_ERROR.getErrorMsg());
            data.put("data", e.getMessage());
        }
        return Result.fail(data);
    }

}
