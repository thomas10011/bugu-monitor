package cn.fusionfuture.bugu.dispose.response;

import cn.fusionfuture.bugu.dispose.GlobalResponseProperties;
import cn.fusionfuture.bugu.dispose.annotation.EnableIgnoreResponse;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.api.ServiceCode;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author thomas
 * @version 1.0
 * @class GlobalResponseHandler
 * @description 全局响应统一处理
 * @date 2020/8/16 2:15 下午
 */
@RestControllerAdvice
@AllArgsConstructor
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    private final GlobalResponseProperties globalResponseProperties;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return filter(methodParameter);
    }

    @Override
    @Nullable
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 返回值为 Object 类型  并且返回为空是  AbstractMessageConverterMethodProcessor#writeWithMessageConverters 方法
        // 无法触发调用本类的 beforeBodyWrite 处理，开发在 Controller 尽量避免直接使用 Object 类型返回。

        // o is null -> return response
        if (o == null) {
            // 当 o 返回类型为 string 并且为null会出现 java.lang.ClassCastException: Result cannot be cast to java.lang.String
            if ("java.lang.String".equals(methodParameter.getParameterType().getName())) {
                return JSONUtil.parse(CommonResult.success().setService(globalResponseProperties.getServiceName())).toString();
            }
            return CommonResult.success().setService(globalResponseProperties.getServiceName());
        }
        // o is instanceof ConmmonResponse -> return o
        if (o instanceof CommonResult) {
            return (CommonResult) ((CommonResult) o).setService(globalResponseProperties.getServiceName());
        }
        // string 特殊处理 java.lang.ClassCastException: Result cannot be cast to java.lang.String
        if (o instanceof String) {
            return JSONUtil.parse(CommonResult.success().setService(globalResponseProperties.getServiceName()).append("data", o)).toString();
        }
        return CommonResult.success().setService(globalResponseProperties.getServiceName()).append("data", o);
    }

    /**
     * @author thomas
     * @description 用于对方法参数进行过滤
     * @create 2020/8/16 3:11 下午
     * @update 2020/8/16 3:11 下午
     * @param methodParameter 方法参数
     * @return java.lang.Boolean
     **/
    private Boolean filter(MethodParameter methodParameter) {
        Class<?> declaringClass = methodParameter.getDeclaringClass();
        // 检查过滤包路径
        long count = globalResponseProperties.getHandlerFilterPackage().stream()
                .filter(l -> declaringClass.getName().contains(l)).count();
        if (count > 0) {
            return false;
        }
        // 检查<类>过滤列表
        if (globalResponseProperties.getHandlerFilterClass().contains(declaringClass.getName())) {
            return false;
        }
        // 检查忽略返回结果处理注解是否存在
        if (methodParameter.getDeclaringClass().isAnnotationPresent(EnableIgnoreResponse.class)) {
            return false;
        }
        if (methodParameter.getMethod().isAnnotationPresent(EnableIgnoreResponse.class)) {
            return false;
        }
        return true;
    }
}
