package cn.fusionfuture.bugu.oauth.service.impl;

import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.stereotype.Service;

/**
 * @author thomas
 * @version 1.0
 * @class AuthorizationCodeServicesImpl
 * @description TODO 授权码模式实现类，用于之后实现授权码模式
 * @date 2020/9/15 11:39 下午
 */
@Service
public class AuthorizationCodeServicesImpl implements AuthorizationCodeServices {
    @Override
    public String createAuthorizationCode(OAuth2Authentication oAuth2Authentication) {
        return null;
    }

    @Override
    public OAuth2Authentication consumeAuthorizationCode(String s) throws InvalidGrantException {
        return null;
    }
}
