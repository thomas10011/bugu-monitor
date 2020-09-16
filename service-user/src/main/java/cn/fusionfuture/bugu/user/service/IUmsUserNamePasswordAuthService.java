package cn.fusionfuture.bugu.user.service;

import cn.fusionfuture.bugu.user.vo.UserOauthVO;

/**
 * @author thomas
 * @version 1.0
 * @class IUmsUserNamePasswordAuthService
 * @description TODO
 * @date 2020/9/16 12:28 上午
 */
public interface IUmsUserNamePasswordAuthService {

    /**
     * 根据传入的用户名查询用户认证需要的信息
     * @author thomas
     * @since 2020/9/16 12:30 上午
     * @param userName 传入的用户名，可能是手机号 邮箱和用户的id
     * @return cn.fusionfuture.bugu.user.vo.UserOauthVO
     **/
    UserOauthVO getUserDetailsByUserName(String userName);
}
