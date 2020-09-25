package cn.fusionfuture.bugu.oauth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author thomas
 * @version 1.0
 * @class WxMiniProgramUserDetailsService
 * @description TODO
 * @date 2020/9/14 10:51 上午
 */
public interface WxMiniProgramUserDetailsService {

    /**
     * 根据openid远程调用user service查询用户信息
     * @author thomas
     * @since 2020/9/14 10:53 上午
     * @param openId 用户的微信唯一标识
     * @return org.springframework.security.core.userdetails.UserDetails
     **/
    UserDetails getUserDetailsByOpenId(String openId);
}
