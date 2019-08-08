package com.etycx.rest.config.exception;

import com.etycx.remote.response.BaseVo;
import com.etycx.remote.response.RemoteServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 武海升
 * @desc 全局异常处理
 * @date  2019-08-07
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final BaseVo baseVo = new BaseVo();

    /**
     * 定义要捕获的异常
     *
     * @param request  request
     * @param e        exception
     * @param response response
     * @return 响应结果
     */
    @ExceptionHandler(RemoteServiceException.class)
    public BaseVo customExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        RemoteServiceException exception = (RemoteServiceException) e;
        return baseVo.error(exception.getError(),exception.getMessage());
    }

}
