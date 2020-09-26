package cn.fusionfuture.bugu.user.service.impl;

import cn.fusionfuture.bugu.pojo.entity.UmsUser;
import cn.fusionfuture.bugu.user.mapper.UmsUserMapper;
import cn.fusionfuture.bugu.user.service.IUmsUserNamePasswordAuthService;
import cn.fusionfuture.bugu.user.vo.UserOauthVO;
import cn.fusionfuture.bugu.utils.auth.AuthUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thomas
 * @version 1.0
 * @class UmsUserNamePasswordAuthServiceImpl
 * @description TODO
 * @date 2020/9/16 12:25 上午
 */
@Service
public class UmsUserNamePasswordAuthServiceImpl implements IUmsUserNamePasswordAuthService {

    @Autowired
    UmsUserMapper userMapper;

    @Override
    public UserOauthVO getUserDetailsByUserName(String userName) {

        UmsUser userInfo = null;

        if (userName.contains("@")) {
            userInfo = userMapper.selectOne(new QueryWrapper<UmsUser>().eq("email", userName));
        }
        else if (userName.length() == 11) {
            userInfo = userMapper.selectOne(new QueryWrapper<UmsUser>().eq("phone", userName));
        }
        else {
            userInfo = userMapper.selectById(Long.parseLong(userName));
        }

        if (userInfo == null) {
            return null;
        }

        UserOauthVO userOauthVO = new UserOauthVO();

        userOauthVO.setUserName(userInfo.getId().toString());
        userOauthVO.setPassword(userInfo.getPassword());
        userOauthVO.setIsEnabled(userInfo.getIsEnabled());
        userOauthVO.setGrantedAuthorityList(AuthUtil.authNumToList(userInfo.getPrivilege()));

        return userOauthVO;
    }
}
