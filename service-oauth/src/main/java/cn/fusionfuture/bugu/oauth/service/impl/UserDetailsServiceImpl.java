package cn.fusionfuture.bugu.oauth.service.impl;

import cn.fusionfuture.bugu.oauth.dto.UserDTO;
import cn.fusionfuture.bugu.oauth.dto.UserDetailsDTO;
import cn.hutool.core.collection.CollUtil;
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
public class UserDetailsServiceImpl implements UserDetailsService {

    private List<UserDTO> userDTOList;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initUserDTOList() {
        String password = passwordEncoder.encode("123456");
        userDTOList = new ArrayList<>();
        userDTOList.add(new UserDTO(1L, "thomas", password, AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN")));
        userDTOList.add(new UserDTO(2L, "andy", password, AuthorityUtils.commaSeparatedStringToAuthorityList("TEST")));
        userDTOList.add(new UserDTO(3L, "mark", password, AuthorityUtils.commaSeparatedStringToAuthorityList("TEST")));
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<UserDTO> findUserList = userDTOList.stream().filter(item -> item.getUserName().equals(userName)).collect(Collectors.toList());
        if (CollUtil.isEmpty(findUserList)) {
            throw new UsernameNotFoundException("用户名或密码错误！");
        }
        UserDetailsDTO securityUser = new UserDetailsDTO(findUserList.get(0));
        if (!securityUser.isEnabled()) {
            throw new DisabledException("该账户已被禁用，请联系管理员!");
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定，请联系管理员!");
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期，请联系管理员!");
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("该账户的登录凭证已过期，请重新登录!");
        }
        return securityUser;
    }
}
