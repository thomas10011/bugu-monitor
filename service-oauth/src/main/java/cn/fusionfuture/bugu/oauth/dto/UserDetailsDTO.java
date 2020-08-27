package cn.fusionfuture.bugu.oauth.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author thomas
 * @version 1.0
 * @class UserDetaisDTO
 * @description TODO
 * @date 2020/8/21 10:58 下午
 */
@Data
public class UserDetailsDTO implements UserDetails {

    private static final long serialVersionUID = 1132551792523360633L;

    private Long id;

    private String userName;

    private String passWord;

    private Boolean enabled;

    public UserDetailsDTO(UserDTO userDTO) {

        this.id = userDTO.getId();
        this.userName = userDTO.getUserName();
        this.passWord = userDTO.getPassword();
        this.enabled = true;

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
