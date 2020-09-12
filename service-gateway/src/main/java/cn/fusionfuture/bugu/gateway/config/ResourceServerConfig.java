package cn.fusionfuture.bugu.gateway.config;

import cn.fusionfuture.bugu.gateway.component.IgnoreUrlsWebFilter;
import cn.fusionfuture.bugu.gateway.component.ReactiveAuthorizationManagerImpl;
import cn.fusionfuture.bugu.gateway.component.ServerAccessDeniedHandlerImpl;
import cn.fusionfuture.bugu.gateway.component.ServerAuthenticationEntryPointImpl;
import cn.fusionfuture.bugu.pojo.constants.AuthConstants;
import cn.hutool.core.util.ArrayUtil;
import com.nimbusds.oauth2.sdk.auth.JWTAuthentication;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author thomas
 * @version 1.0
 * @class ResourceServerConfig
 * @description 资源服务器配置
 * @date 2020/8/22 6:27 下午
 */
@AllArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {

    private final ReactiveAuthorizationManagerImpl reactiveAuthorizationManager;

    private final IgnoreUrlsConfig ignoreUrlsConfig;

    private final ServerAccessDeniedHandlerImpl serverAccessDeniedHandler;

    private final ServerAuthenticationEntryPointImpl serverAuthenticationEntryPoint;

    private final IgnoreUrlsWebFilter ignoreUrlsWebFilter;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
        http
                .addFilterBefore(ignoreUrlsWebFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        http
                .authorizeExchange()
                // 白名单配置
                .pathMatchers(ArrayUtil.toArray(ignoreUrlsConfig.getUrls(),String.class)).permitAll()
                // 鉴权管理器配置
                .anyExchange().access(reactiveAuthorizationManager)
                .and()
                .exceptionHandling()
                // 处理未授权
                .accessDeniedHandler(serverAccessDeniedHandler)
                // 处理未认证
                .authenticationEntryPoint(serverAuthenticationEntryPoint)
                .and()
                .csrf().disable();
        return http.build();
    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthConstants.AUTHORITY_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthConstants.AUTHORITY_CLAIM_NAME);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}
