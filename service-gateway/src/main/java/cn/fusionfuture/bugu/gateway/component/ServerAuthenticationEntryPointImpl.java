package cn.fusionfuture.bugu.gateway.component;

import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.api.ResultCode;
import cn.hutool.json.JSONUtil;
import com.sun.corba.se.spi.activation.ServerOperations;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author thomas
 * @version 1.0
 * @class ServerAuthenticationEntryPointImpl
 * @description AuthenticationEntryPoint 用来解决匿名用户访问无权限资源时的异常
 * @date 2020/8/22 6:40 下午
 */
@Component
public class ServerAuthenticationEntryPointImpl implements ServerAuthenticationEntryPoint {
    @Override
    public Mono<Void> commence(ServerWebExchange serverWebExchange, AuthenticationException e) {
        ServerHttpResponse response = serverWebExchange.getResponse();
        HttpRequest request = serverWebExchange.getRequest();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getHeaders().set("Access-Control-Allow-Origin","*");
        response.getHeaders().set("Cache-Control","no-cache");
        String body= JSONUtil.toJsonStr(
                CommonResult
                        .fail(ResultCode.METHOD_NOT_ALLOWED)
                        .setService(request.getURI().getPath().split("/")[1])
                        .append(serverWebExchange.getAttributes())
        );
        DataBuffer buffer =  response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }

}
