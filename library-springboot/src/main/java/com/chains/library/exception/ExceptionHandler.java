package com.chains.library.exception;

import com.chains.library.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = ServiceException.class)
    public Result serviceExceptionError(ServiceException e){
        log.error("业务异常！",e.getMessage());
        return Result.error(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public Result exceptionError(Exception e){
        log.error("系统错误！",e);
        return Result.error("系统错误！");
    }
}
