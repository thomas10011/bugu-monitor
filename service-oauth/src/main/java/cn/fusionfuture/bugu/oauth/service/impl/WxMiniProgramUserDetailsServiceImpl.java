package cn.fusionfuture.bugu.oauth.service.impl;

import cn.fusionfuture.bugu.oauth.vo.UserOauthVO;
import cn.fusionfuture.bugu.oauth.feign.UserFeignService;
import cn.fusionfuture.bugu.oauth.service.WxMiniProgramUserDetailsService;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author thomas
 * @version 1.0
 * @class WxMiniProgramUserDetailsService
 * @description TODO
 * @date 2020/9/14 10:48 上午
 */
public class WxMiniProgramUserDetailsServiceImpl implements WxMiniProgramUserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return getUserDetailsByOpenId(s);
    }


    @Override
    public UserDetails getUserDetailsByOpenId(String openId) {
        return null;
    }
}
