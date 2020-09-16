package cn.fusionfuture.bugu.oauth.component;

import cn.fusionfuture.bugu.oauth.service.impl.UserDetailsImpl;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author thomas
 * @version 1.0
 * @class JwtTokenEnhancer
 * @description 自定义的jwt内容增强器 可以向jwt中添加自己想添加的信息
 * @date 2020/8/22 5:01 下午
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(oAuth2AccessToken);
        UserDetailsImpl userDetails = (UserDetailsImpl) oAuth2Authentication.getUserAuthentication().getPrincipal();
        HashMap<String, Object> map = new HashMap<>();

//        token.setAdditionalInformation(map);
        return token;
    }
}
