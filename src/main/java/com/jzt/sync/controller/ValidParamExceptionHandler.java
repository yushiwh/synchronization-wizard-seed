package com.jzt.sync.controller;

import com.jzt.sync.model.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Classname ValidParamExceptionHandler
 * @Description 统一封装出现异常参数校验的方法
 * @Date 2020/11/20 16:28
 * @Created by nick
 */
@ControllerAdvice
@Slf4j
public class ValidParamExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultModel handleMethodNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder errorMessage = new StringBuilder(bindingResult.getFieldErrors().size() * 16);
        errorMessage.append("错误信息:");
        for (int i = 0; i < bindingResult.getFieldErrors().size(); i++) {
            if (i > 0) {
                errorMessage.append(",");
            }
            FieldError fieldError = bindingResult.getFieldErrors().get(i);
            errorMessage.append(fieldError.getField());
            errorMessage.append(":");
            errorMessage.append(fieldError.getDefaultMessage());
        }
        ResultModel resultModel = new ResultModel();
        resultModel.setCode("400").setMsg(errorMessage.toString());
        return resultModel;
    }

}
