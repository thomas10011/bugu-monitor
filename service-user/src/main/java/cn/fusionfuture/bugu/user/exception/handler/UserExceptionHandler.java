package cn.fusionfuture.bugu.user.exception.handler;


import cn.fusionfuture.bugu.dispose.exception.ExceptionUtil;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.api.ResultCode;
import cn.fusionfuture.bugu.user.exception.WechatBindException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author thomas
 * @version 1.0
 * @date 2021/3/4 20:35
 * TODO
 **/
@Order(0)
@RestControllerAdvice
public class UserExceptionHandler {
    /**
     * WechatBindException 绑定用户微信时出现异常
     */
    @ExceptionHandler(value = WechatBindException.class)
    @ResponseStatus(HttpStatus.OK)
    public Object wechatBindException(WechatBindException e) throws Throwable {
        ExceptionUtil.logError(e);
        return CommonResult.fail(ResultCode.USER_BIND_FAILED);
    }
}
