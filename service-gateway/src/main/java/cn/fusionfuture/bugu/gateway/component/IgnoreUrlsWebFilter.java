package cn.fusionfuture.bugu.gateway.component;

import cn.fusionfuture.bugu.gateway.config.IgnoreUrlsConfig;
import cn.fusionfuture.bugu.pojo.constants.AuthConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

/**
 * @author thomas
 * @version 1.0
 * @class IgnoreUrlsWebFilter
 * @description 对于白名单中的URI直接过滤掉请求头中的token
 * @date 2020/8/22 7:58 下午
 */
@Component
public class IgnoreUrlsWebFilter implements WebFilter {

    @Autowired
    IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        //白名单路径移除JWT请求头
        List<String> ignoreUrls = ignoreUrlsConfig.getUrls();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                request = serverWebExchange.getRequest().mutate().header(AuthConstants.JWT_TOKEN_HEADER, "").build();
                serverWebExchange = serverWebExchange.mutate().request(request).build();
                return webFilterChain.filter(serverWebExchange);
            }
        }
        return webFilterChain.filter(serverWebExchange);
    }
}
