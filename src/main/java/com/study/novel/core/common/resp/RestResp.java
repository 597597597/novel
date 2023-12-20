package com.study.novel.core.common.resp;

import com.study.novel.core.common.constant.ErrorCodeEnum;
import lombok.Getter;
import java.util.Objects;

/**
 * Http Rest 响应工具及数据格式封装
 * @author 597
 */
@Getter
public class RestResp<T> {
    /**
     * 响应码
     */
    private String code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应成功但无数据
     */
    private RestResp() {
        this.code = ErrorCodeEnum.OK.getCode();
        this.message = ErrorCodeEnum.OK.getMessage();
    }

    /**
     * 响应成功且有数据
     */
    private RestResp(T data) {
//        this.code = ErrorCodeEnum.OK.getCode();
//        this.message = ErrorCodeEnum.OK.getMessage();
        this.data = data;
    }

    /**
     * 响应失败
     */
    private RestResp(ErrorCodeEnum errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    /**
     * 业务处理成功，无数据返回
     */
    public static RestResp ok() {
        return new RestResp();
    }

    /**
     * 业务处理成功，有数据返回
     */
    public static <T> RestResp ok(T data) {
        return new RestResp(data);
    }

    /**
     * 业务处理失败
     */
    public static RestResp fail(ErrorCodeEnum errorCode) {
        return new RestResp(errorCode);
    }

    /**
     * 系统错误
     */
    public static RestResp error() {
        return new RestResp(ErrorCodeEnum.SYSTEM_ERROR);
    }

    /**
     * 判断是否成功
     */
    public boolean isOk() {
        // 响应码是否等于成功的响应码
        return Objects.equals(this.code, ErrorCodeEnum.OK.getCode());
    }
}
