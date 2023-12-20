package com.study.novel.core.common.exception;

import com.study.novel.core.common.constant.ErrorCodeEnum;
import com.study.novel.core.common.resp.RestResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.net.BindException;

/**
 * 异常处理器
 * @author 597
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    /**
     * 处理数据校验异常
     */
    @ExceptionHandler(BindException.class)
    public RestResp handlerBindException(BindException e) {
        log.error(e.getMessage());
        return RestResp.fail(ErrorCodeEnum.USER_REQUEST_PARAM_ERROR);
    }

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public RestResp handlerBusinessException(BusinessException e) {
        log.error(e.getMessage());
        return RestResp.fail(e.getErrorCode());
    }

    /**
     * 处理系统异常
     */
    @ExceptionHandler(Exception.class)
    public RestResp handlerException(Exception e) {
        log.error(e.getMessage());
        return RestResp.error();
    }
}
