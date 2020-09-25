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
@Service
public class WxMiniProgramUserDetailsServiceImpl implements WxMiniProgramUserDetailsService {

    @Autowired
    UserFeignService userFeignService;

    @Override
    public UserDetails getUserDetailsByOpenId(String openId) {
        CommonResult<UserOauthVO> result = userFeignService.getBindUid(openId);
        UserOauthVO userVO = result.getData();
        if (userVO == null) {
//            throw new UsernameNotFoundException("用户名或密码错误！");
            return null;
        }
        return new UserDetailsImpl(userVO);
    }
}
