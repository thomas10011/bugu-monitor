package cn.fusionfuture.bugu.oauth.component;

import cn.fusionfuture.bugu.oauth.service.UserNamePasswordUserDetailsService;
import cn.fusionfuture.bugu.oauth.service.WxMiniProgramUserDetailsService;
import cn.fusionfuture.bugu.oauth.feign.UserFeignService;
import cn.fusionfuture.bugu.oauth.service.impl.UserDetailsImpl;
import cn.fusionfuture.bugu.oauth.vo.UserOauthVO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

/**
 * @author thomas
 * @version 1.0
 * @class WxMiniProgramAuthenticationProvider
 * @description 自定义小程序登陆流程
 * @date 2020/9/14 2:59 下午
 */
public class WxMiniProgramAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserFeignService userFeignService;

    @Autowired
    private UserNamePasswordUserDetailsService userNamePasswordUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // 假设用户名的值是openId
        String openId = authentication.getName();


        UserDetailsImpl userDetails = (UserDetailsImpl) userNamePasswordUserDetailsService.loadUserByUsername(openId);

        if(userDetails == null) {
            throw new AuthenticationCredentialsNotFoundException("该用户尚未注册！");
//            return null;
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), authentication.getCredentials(), userDetails.getAuthorities());
        token.setDetails(userDetails);
        return token;

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
