package com.study.novel.core.common.exception;

import com.study.novel.core.common.constant.ErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义业务异常
 * 处理业务请求并发生错误时抛出
 *
 * @author 597
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException{

    private final ErrorCodeEnum errorCode;

    private BusinessException(ErrorCodeEnum errorCode) {
        // 不调用父类 Throwable的fillInStackTrace() 方法生成栈追踪信息，提高应用性能
        // 构造器之间的调用必须在第一行
        /*
        message：用于描述异常的信息。
        cause：表示引起异常的原因。这个参数通常是另一个异常。如果没有可用的原因，可以传入 null。
        enableSuppression：表示是否启用异常抑制功能。如果为 true，则表示异常抑制功能被启用（默认情况下为 true）。
        异常抑制是指当一个异常被抛出时，可能会抑制（压制）另一个异常的抛出。
        writableStackTrace：表示是否生成可写的堆栈跟踪信息。如果为 true，则表示生成的堆栈跟踪信息可以被修改。默认情况下为 true。
         */
        super(errorCode.getMessage(), null, false, false);
        this.errorCode = errorCode;
    }
}
