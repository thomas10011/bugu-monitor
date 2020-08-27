package cn.fusionfuture.bugu.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author thomas
 * @version 1.0
 * @class RedisTokenStoreConfig
 * @description 将token缓存到redis中的配置类
 * @date 2020/8/24 12:44 上午
 */
@Configuration
public class RedisTokenStoreConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 返回RedisTokenStore的Bean 用于将Token保存到Redis中
     * @author thomas
     * @since 2020/8/24 1:36 上午
     * @return org.springframework.security.oauth2.provider.token.TokenStore
     **/
    @Bean(value = "redisTokenService")
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

}
