package cn.fusionfuture.bugu.oauth.config;

import cn.fusionfuture.bugu.oauth.component.WxMiniProgramAuthenticationProvider;
import cn.fusionfuture.bugu.oauth.service.UserNamePasswordUserDetailsService;
import cn.fusionfuture.bugu.oauth.service.WxMiniProgramUserDetailsService;
import cn.fusionfuture.bugu.oauth.service.impl.UserNamePasswordUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author thomas
 * @version 1.0
 * @class WebSecurityConfig
 * @description 网关安全配置
 * @date 2020/8/21 10:18 下午
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserNamePasswordUserDetailsService userNamePasswordUserDetailsService;

    @Autowired
    WxMiniProgramAuthenticationProvider wxMiniProgramAuthenticationProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userNamePasswordUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 安全拦截机制
     * @author thomas
     * @since 2020/8/21 11:51 下午
     * @param http 请求
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .antMatchers("/rsa/publicKey").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }

    /**
     * 添加自定义的用于认证的provider
     * @author thomas
     * @since 2020/9/14 12:08 下午
     * @param auth aut可以用于配置认证对象
     * @return void
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.authenticationProvider(wxMiniProgramAuthenticationProvider)
            .authenticationProvider(daoAuthenticationProvider());
    }
}
