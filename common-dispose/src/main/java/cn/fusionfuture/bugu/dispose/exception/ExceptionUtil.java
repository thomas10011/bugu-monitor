package cn.fusionfuture.bugu.dispose.exception;

import cn.fusionfuture.bugu.dispose.annotation.EnableIgnoreResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author thomas
 * @version 1.0
 * @date 2021/3/4 20:57
 * TODO
 **/
@Slf4j
public class ExceptionUtil {
    /**
     * 校验是否进行异常处理
     *
     * @param e   异常
     * @param <T> extends Throwable
     * @throws Throwable 异常
     */
    public static  <T extends Throwable> void errorDispose(T e) throws Throwable {
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
            // 是否忽略返回结果封装
            if (methodAnnotation.dispose()) {
                throw e;
            } else {
                return;
            }
        }
        // 判断类是否存在 IgnoreResponseAdvice 注解
        EnableIgnoreResponse classAnnotation = beanType.getAnnotation(EnableIgnoreResponse.class);
        if (classAnnotation != null) {
            if (classAnnotation.dispose()) {
                throw e;
            }
        }
    }

    public static void logError(Throwable throwable) {
        log.error("[{}]: {}", throwable.getClass().getSimpleName(), throwable.getMessage());
    }

    public static void logWarn(Throwable throwable) {
        log.warn("[{}]: {}", throwable.getClass().getSimpleName(), throwable.getMessage());
    }
}
