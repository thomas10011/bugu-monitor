package cn.fusionfuture.bugu.gateway.component;

import cn.fusionfuture.bugu.gateway.config.IgnoreUrlsConfig;
import cn.fusionfuture.bugu.gateway.dto.UserDTO;
import cn.fusionfuture.bugu.pojo.constants.AuthConstants;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.nimbusds.jose.JWSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author thomas
 * @version 1.0
 * @class ReactiveAuthorizationManagerImpl
 * @description 动态进行权限校验
 * @date 2020/8/22 6:31 下午
 */
@Component
public class ReactiveAuthorizationManagerImpl implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    /**
     * 配置鉴权过程
     * @author thomas
     * @since 2020/8/24 7:38 下午
     * @param mono 鉴权实体
     * @param authorizationContext 安全上下文
     * @return reactor.core.publisher.Mono<org.springframework.security.authorization.AuthorizationDecision>
     **/
    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();

        // 白名单路径直接放行
        List<String> ignoreUrls = ignoreUrlsConfig.getUrls();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                return Mono.just(new AuthorizationDecision(true));
            }
        }

        // 对应跨域的预检请求直接放行
        if(request.getMethod()== HttpMethod.OPTIONS){
            return Mono.just(new AuthorizationDecision(true));
        }

        // 不同用户体系登录不允许互相访问
        try {
            String token = request.getHeaders().getFirst(AuthConstants.JWT_TOKEN_HEADER);
            if(StrUtil.isEmpty(token)){
                return Mono.just(new AuthorizationDecision(false));
            }
            String realToken = token.replace(AuthConstants.JWT_TOKEN_PREFIX, "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            String userStr = jwsObject.getPayload().toString();
            UserDTO userDto = JSONUtil.toBean(userStr, UserDTO.class);
            if (AuthConstants.ADMIN_CLIENT_ID.equals(userDto.getClientId()) && !pathMatcher.match(AuthConstants.ADMIN_URL_PATTERN, uri.getPath())) {
                return Mono.just(new AuthorizationDecision(false));
            }
            if (AuthConstants.PORTAL_CLIENT_ID.equals(userDto.getClientId()) && pathMatcher.match(AuthConstants.ADMIN_URL_PATTERN, uri.getPath())) {
                return Mono.just(new AuthorizationDecision(false));
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return Mono.just(new AuthorizationDecision(false));
        }

        // 非管理端路径直接放行
        if (!pathMatcher.match(AuthConstants.ADMIN_URL_PATTERN, uri.getPath())) {
            return Mono.just(new AuthorizationDecision(true));
        }

        // 管理端路径需校验权限
        Map<Object, Object> resourceRolesMap = redisTemplate.opsForHash().entries(AuthConstants.RESOURCE_ROLES_MAP_KEY);
        Iterator<Object> iterator = resourceRolesMap.keySet().iterator();
        List<String> authorities = new ArrayList<>();
        while (iterator.hasNext()) {
            String pattern = (String) iterator.next();
            if (pathMatcher.match(pattern, uri.getPath())) {
                authorities.addAll(Convert.toList(String.class, resourceRolesMap.get(pattern)));
            }
        }
        authorities = authorities.stream().map(i -> i = AuthConstants.AUTHORITY_PREFIX + i).collect(Collectors.toList());

        //认证通过且角色匹配的用户可访问当前路径
        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorities::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

}
