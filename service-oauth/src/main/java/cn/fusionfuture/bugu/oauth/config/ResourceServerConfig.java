package cn.fusionfuture.bugu.oauth.config;

import cn.fusionfuture.bugu.pojo.constants.AuthConstants;
import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author thomas
 * @version 1.0
 * @class ResourceServerConfig
 * @description 资源服务器设置
 * @date 2020/8/22 12:12 上午
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 要保护的资源和权限对应关系持久化到redis中
     * @author thomas
     * @since 2020/8/24 2:40 上午
     **/
    @PostConstruct
    public void initData() {
        Map<String, List<String>> resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("/api/hello", CollUtil.toList("ADMIN"));
        resourceRolesMap.put("/user-service/test/swagger", CollUtil.toList("ADMIN", "TEST"));
        redisTemplate.opsForHash().putAll(AuthConstants.RESOURCE_ROLES_MAP_KEY, resourceRolesMap);
    }

    /**
     * 资源服务保护
     * @author thomas
     * @since 2020/8/24 1:45 上午
     * @param http 传入的请求
     **/
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
//                .anyRequest().authenticated()
                .and()
                .requestMatchers()
                // 配置需要保护的资源路径
                .antMatchers("/**");
    }
}
