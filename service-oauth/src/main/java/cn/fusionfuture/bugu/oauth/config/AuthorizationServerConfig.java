package cn.fusionfuture.bugu.oauth.config;

import cn.fusionfuture.bugu.oauth.service.UserNamePasswordUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author thomas
 * @version 1.0
 * @class AuthorizationServerConfig
 * @description 认证服务配置
 * @date 2020/8/21 10:35 下午
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserNamePasswordUserDetailsService userNamePasswordUserDetailsService;

    @Autowired
    private TokenEnhancer jwtTokenEnhancer;

    @Autowired
    @Qualifier(value = "redisTokenService")
    private TokenStore tokenStore;

    @Autowired
    KeyPair keyPair;

    /**
     * 配置客户端申请认证时需要哪些信息
     * @author thomas
     * @since 2020/8/24 1:23 上午
     * @param clients 包含客户端信息
     **/
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client-app")
                .secret(passwordEncoder.encode("123456"))
                // 配置访问token的有效期
                .accessTokenValiditySeconds(60)
                // 配置刷新token的有效期
                .refreshTokenValiditySeconds(864000)
                // 配置redirect_uri，用于授权成功后跳转
                .redirectUris("http://www.baidu.com")
                // 配置申请的权限范围
                .scopes("all")
                // 配置grant_type，表示授权类型 目前支持授权码模式、密码模式、刷新token
                .authorizedGrantTypes("authorization_code", "password", "refresh_token");
    }

    /**
     * 配置授权端点的url。默认的url如下。可以用pathMapping()方法修改默认的链接。
     * /oauth/authorize 授权端点
     * /oauth/token 令牌端点
     * /oauth/confirm_access 用户确认授权提交端点
     * /oauth/error 授权服务错误信息端点
     * /oauth/check_token 用于资源服务访问的令牌解析端点
     * /oauth/token_key 对于jwt令牌提供公钥的额端点
     *
     * @author thomas
     * @since 2020/8/23 4:17 下午
     * @param endpoints 传入的配置对象
     **/
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> tokenEnhancers = new ArrayList<>();
        // 配置JWT的内容增强器
        tokenEnhancers.add(accessTokenConverter());
        tokenEnhancers.add(jwtTokenEnhancer);
        enhancerChain.setTokenEnhancers(tokenEnhancers);

        endpoints
                .authenticationManager(authenticationManager)
                // 配置加载用户信息的服务 认证和刷新令牌都将调用该服务
                .userDetailsService(userNamePasswordUserDetailsService)
                .accessTokenConverter(accessTokenConverter())
                .tokenEnhancer(enhancerChain)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST)
                .tokenStore(tokenStore);
    }

    /**
     * 用来配置令牌端点的安全约束
     * @author thomas
     * @since 2020/8/23 4:13 下午
     * @param security 传入的安全配置
     **/
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // 允许表单验证
                .allowFormAuthenticationForClients()
                .checkTokenAccess("permitAll()")
                // 让资源服务获取公钥
                .tokenKeyAccess("permitAll()");
    }

    /**
     * TokenEnhancer的实现类 负责jwt的双向转换 可以自己设定jwt的秘钥
     * @author thomas
     * @since 2020/8/24 1:22 上午
     * @return org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
     **/
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//        jwtAccessTokenConverter
        jwtAccessTokenConverter.setKeyPair(keyPair);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", 123456);
        jwtAccessTokenConverter.extractAccessToken("id", map);

        return jwtAccessTokenConverter;
    }

    /**
     * 我们采用HS+RSA的签名方式 这里从resources文件夹下的证书中获取RSA秘钥对
     * @author thomas
     * @since 2020/8/24 1:21 上午
     * @return java.security.KeyPair
     **/
    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "123456".toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", "123456".toCharArray());
    }

    /**
     * 设置授权码模式的授权码存取方式
     * @author thomas
     * @since 2020/8/23 5:35 下午
     * @return org.springframework.security.oauth2.provider.code.AuthorizationCodeServices
     **/
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }
}
