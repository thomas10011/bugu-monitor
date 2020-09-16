package cn.fusionfuture.bugu.oauth.service.impl;

import cn.fusionfuture.bugu.oauth.vo.UserOauthVO;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author thomas
 * @version 1.0
 * @class UserDetaisDTO
 * @description TODO
 * @date 2020/8/21 10:58 下午
 */
@Data
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1132551792523360633L;

    private String userName;

    private String passWord;

    private List<GrantedAuthority> grantedAuthorityList;

    private Boolean enabled;

    public UserDetailsImpl(UserOauthVO userVO) {

        this.userName = userVO.getUserName();
        this.passWord = userVO.getPassword();
        this.grantedAuthorityList = AuthorityUtils.createAuthorityList(userVO.getGrantedAuthorityList().toArray(new String[]{}));
        this.enabled = userVO.getIsEnabled();

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return passWord;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
