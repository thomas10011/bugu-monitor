package cn.fusionfuture.bugu.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author thomas
 * @version 1.0
 * @class UserDTO
 * @description TODO
 * @date 2020/8/21 11:06 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserOauthVO {

    private String userName;

    private String password;

    private List<String> grantedAuthorityList;

    private Boolean isEnabled;

}
