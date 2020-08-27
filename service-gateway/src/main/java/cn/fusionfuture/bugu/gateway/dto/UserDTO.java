package cn.fusionfuture.bugu.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @author thomas
 * @version 1.0
 * @class UserDTO
 * @description TODO
 * @date 2020/8/22 7:54 下午
 */
@Data
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String userName;

    private String password;

    private List<GrantedAuthority> grantedAuthorityList;

    private String clientId;

    private List<String> roles;

}
