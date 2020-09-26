package cn.fusionfuture.bugu.oauth.service.impl;

import cn.fusionfuture.bugu.oauth.feign.UserFeignService;
import cn.fusionfuture.bugu.oauth.service.UserNamePasswordUserDetailsService;
import cn.fusionfuture.bugu.oauth.vo.UserOauthVO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author thomas
 * @version 1.0
 * @class UserDetailsServiceImpl
 * @description TODO
 * @date 2020/8/21 10:41 下午
 */
@Service
public class UserNamePasswordUserDetailsServiceImpl implements UserNamePasswordUserDetailsService {

    @Autowired
    private UserFeignService userFeignService;

    /**
     * 用户名密码方式登陆时 远程调用user service的接口查询用户信息
     * 用户名可以是手机号和邮箱
     * 刷新token时 传入用户名是用户的id
     * TODO 待实现的逻辑：可以从内存或者redis缓存中获取userdetail 登陆是否判断是否过期
     * @author thomas
     * @since 2020/9/15 11:45 下午
     * @param userName 前端传入的手机号或者邮箱
     * @return org.springframework.security.core.userdetails.UserDetails
     **/
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserOauthVO userOauthVO = userFeignService.getUserNamePassword(userName).getData();

        if (userOauthVO == null) {
            return getUserDetailsByOpenId(userName);
        }
        UserDetailsImpl securityUser = new UserDetailsImpl(userOauthVO);
        if (!securityUser.isEnabled()) {
            throw new DisabledException("该账户已被禁用，请联系管理员!");
        }
        // 以下状态暂时都未实现
        else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定，请联系管理员!");
        }
        else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期，请联系管理员!");
        }
        else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("该账户的登录凭证已过期，请重新登录!");
        }
        return securityUser;

    }

    public UserDetails getUserDetailsByOpenId(String openId) throws UsernameNotFoundException {

        CommonResult<UserOauthVO> result = userFeignService.getBindUid(openId);
        UserOauthVO userVO = result.getData();
        if (userVO == null) {
//            throw new UsernameNotFoundException("用户名或密码错误！");
            return null;
        }
        return new UserDetailsImpl(userVO);

    }
}
