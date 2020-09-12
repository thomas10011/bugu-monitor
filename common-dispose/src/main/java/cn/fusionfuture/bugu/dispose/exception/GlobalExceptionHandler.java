package cn.fusionfuture.bugu.dispose.exception;

import cn.fusionfuture.bugu.dispose.annotation.EnableIgnoreResponse;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author thomas
 * @version 1.0
 * @class GlobalExceptionHandler
 * @description 全局异常统一处理
 * @date 2020/8/16 2:16 下午
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * NoHandlerFoundException 404 异常处理
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResult handlerNoHandlerFoundException(NoHandlerFoundException e) throws Throwable {
        errorDispose(e);
        outPutErrorWarn(NoHandlerFoundException.class, ResultCode.NOT_FOUND, e);
        return CommonResult.fail(ResultCode.NOT_FOUND);
    }

    /**
     * HttpRequestMethodNotSupportedException 405 异常处理
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonResult handlerHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) throws Throwable {
        errorDispose(e);
        outPutErrorWarn(HttpRequestMethodNotSupportedException.class, ResultCode.METHOD_NOT_ALLOWED, e);
        return CommonResult.fail(ResultCode.METHOD_NOT_ALLOWED);
    }

    /**
     * HttpMediaTypeNotSupportedException 415 异常处理
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public CommonResult handlerHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException e) throws Throwable {
        errorDispose(e);
        outPutErrorWarn(HttpMediaTypeNotSupportedException.class, ResultCode.UNSUPPORTED_MEDIA_TYPE, e);
        return CommonResult.fail(ResultCode.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * Exception 类捕获 500 异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public CommonResult handlerException(Exception e) throws Throwable {
        errorDispose(e);
        log.info(e.getMessage());
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
        log.info(cause.getMessage());
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

    /**
     * 校验是否进行异常处理
     *
     * @param e   异常
     * @param <T> extends Throwable
     * @throws Throwable 异常
     */
    private <T extends Throwable> void errorDispose(T e) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HandlerMethod handlerMethod = (HandlerMethod) request.getAttribute("org.springframework.web.servlet.HandlerMapping.bestMatchingHandler");

        if (handlerMethod == null) {
            return;
        }
        // 获取异常 Controller
        Class<?> beanType = handlerMethod.getBeanType();
        // 获取异常方法
        Method method = handlerMethod.getMethod();

        // 判断方法是否存在 IgnoreResponseAdvice 注解
        EnableIgnoreResponse methodAnnotation = method.getAnnotation(EnableIgnoreResponse.class);
        if (methodAnnotation != null) {
            // 是否使用异常处理
            if (methodAnnotation.errorDispose()) {
                throw e;
            } else {
                return;
            }
        }
        // 判类是否存在 IgnoreResponseAdvice 注解
        EnableIgnoreResponse classAnnotation = beanType.getAnnotation(EnableIgnoreResponse.class);
        if (classAnnotation != null) {
            if (classAnnotation.errorDispose()) {
                throw e;
            }
        }
    }

    public void outPutError(Class errorType, Enum secondaryErrorType, Throwable throwable) {
        log.error("[{}] {}: {}", errorType.getSimpleName(), secondaryErrorType, throwable.getMessage(),
                throwable);
    }

    public void outPutErrorWarn(Class errorType, Enum secondaryErrorType, Throwable throwable) {
        log.warn("[{}] {}: {}", errorType.getSimpleName(), secondaryErrorType, throwable.getMessage());
    }

}
