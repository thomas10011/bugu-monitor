package cn.fusionfuture.bugu.user.controller;

import cn.fusionfuture.bugu.user.service.IUmsUserNamePasswordAuthService;
import cn.fusionfuture.bugu.user.vo.UserOauthVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thomas
 * @version 1.0
 * @class UmsUserNamePasswordAuthController
 * @description TODO
 * @date 2020/9/16 12:23 上午
 */
@RestController
public class UmsUserNamePasswordAuthController {

    @Autowired
    IUmsUserNamePasswordAuthService userNamePasswordAuthService;

    @GetMapping(value = "/authentication/name-pwd")
    UserOauthVO getUserNamePassword(@RequestParam("userName") String userName) {
        return userNamePasswordAuthService.getUserDetailsByUserName(userName);
    }
}
