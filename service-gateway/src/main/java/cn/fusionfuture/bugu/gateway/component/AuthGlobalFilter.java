package cn.fusionfuture.bugu.gateway.component;

import cn.hutool.core.util.StrUtil;
import com.nimbusds.jose.JWSObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author thomas
 * @version 1.0
 * @class AuthGlobalFilter
 * @description 将登录用户的JWT转化成用户信息的全局过滤器
 * @date 2020/9/8 5:04 下午
 */
@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

//        // 暂时把请求中的uid写死
//        ServerHttpRequest request = exchange.getRequest().mutate().header("uid", "1").build();
//        exchange = exchange.mutate().request(request).build();
//        return chain.filter(exchange);

        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (StrUtil.isEmpty(token)) {
            return chain.filter(exchange);
        }
        try {
            //从token中解析用户信息并设置到Header中去
            String realToken = token.replace("Bearer ", "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            String uid = jwsObject.getPayload().toJSONObject().getAsString("user_name");
            log.info("AuthGlobalFilter.filter() user:{}", jwsObject.getPayload().toString());

            // uid放到请求头中
            ServerHttpRequest request = exchange.getRequest().mutate().header("uid", uid).build();
            exchange = exchange.mutate().request(request).build();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

