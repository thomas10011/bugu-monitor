package cn.fusionfuture.bugu.dispose.response;

import cn.fusionfuture.bugu.dispose.GlobalResponseProperties;
import cn.fusionfuture.bugu.dispose.annotation.EnableIgnoreResponse;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.api.ResultCode;
import cn.fusionfuture.bugu.pojo.api.ServiceCode;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
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
import springfox.documentation.spring.web.json.Json;

import java.util.Map;

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

        // 返回状态码则直接设置状态码
        if (o instanceof ResultCode) {
            return new CommonResult<>()
                    .setCode(((ResultCode) o).getCode())
                    .setMessage(((ResultCode) o).getMessage())
                    .setService(globalResponseProperties.getServiceName());
        }
        // 如果是通用统一处理结果name直接返回
        if (o instanceof CommonResult) {
            return ((CommonResult<?>) o).setService(globalResponseProperties.getServiceName());
        }
        // 如果是String类型需要另外处理，否则会报异常
        if (o instanceof String) {
            return JSONUtil.parseObj(
                    CommonResult
                            .success()
                            .setService(globalResponseProperties.getServiceName())
                            .append(o),
                    false, true
            ).toStringPretty();
        }
        // 返回null或者数据一律视为成功
        return CommonResult
                .success()
                .setService(globalResponseProperties.getServiceName())
                .append(o);

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
