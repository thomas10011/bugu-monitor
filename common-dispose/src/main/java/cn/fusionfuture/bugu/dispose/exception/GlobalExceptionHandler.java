package cn.fusionfuture.bugu.dispose.exception;

import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;


/**
 * @author thomas
 * @version 1.0
 * @class GlobalExceptionHandler
 * @description 全局异常统一处理
 * @date 2020/8/16 2:16 下午
 */
@RestControllerAdvice
@Slf4j
@Order(1)
public class GlobalExceptionHandler {
    /**
     * NoHandlerFoundException 404 异常处理
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResult handlerNoHandlerFoundException(NoHandlerFoundException e) throws Throwable {
        ExceptionUtil.logWarn(e);
        log.error(e.getMessage());
        return CommonResult.fail(ResultCode.NOT_FOUND);
    }

    /**
     * HttpRequestMethodNotSupportedException 405 异常处理
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonResult handlerHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) throws Throwable {
        log.error(e.getMessage());
        return CommonResult.fail(ResultCode.METHOD_NOT_ALLOWED);
    }

    /**
     * HttpMediaTypeNotSupportedException 415 异常处理
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public CommonResult handlerHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException e) throws Throwable {
        log.error(e.getMessage());
        return CommonResult.fail(ResultCode.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * Exception 类捕获 500 异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public CommonResult handlerException(Exception e) throws Throwable {
        ExceptionUtil.logError(e);
        System.out.println(e.getStackTrace()[0].toString());
        return ifDepthExceptionType(e);
    }

    /**
     * 二次深度检查错误类型
     */
    private CommonResult ifDepthExceptionType(Throwable throwable) throws Throwable {
        Throwable cause = throwable.getCause();
//        if (cause instanceof ClientException) {
//            return handlerClientException((ClientException) cause);
//        }
//        if (cause instanceof FeignException) {
//            return handlerFeignException((FeignException) cause);
//        }
//        outPutError(Exception.class, CommonErrorCode.EXCEPTION, throwable);
        return CommonResult.fail(ResultCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * FeignException 类捕获
     */
//    @ExceptionHandler(value = FeignException.class)
//    public Result handlerFeignException(FeignException e) throws Throwable {
//        errorDispose(e);
//        outPutError(FeignException.class, CommonErrorCode.RPC_ERROR, e);
//        return Result.ofFail(CommonErrorCode.RPC_ERROR);
//    }

    /**
     * ClientException 类捕获
     */
//    @ExceptionHandler(value = ClientException.class)
//    public Result handlerClientException(ClientException e) throws Throwable {
//        errorDispose(e);
//        outPutError(ClientException.class, CommonErrorCode.RPC_ERROR, e);
//        return Result.ofFail(CommonErrorCode.RPC_ERROR);
//    }



}
