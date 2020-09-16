package cn.fusionfuture.bugu.user.controller;


import cn.fusionfuture.bugu.user.service.IUmsUserWxMiniProgramAuthService;
import cn.fusionfuture.bugu.user.vo.UserOauthVO;
import cn.fusionfuture.bugu.user.vo.WechatBindDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@RestController
public class UmsUserWxMiniProgramAuthController {
    @Autowired
    IUmsUserWxMiniProgramAuthService iUmsUserAuthWechatService;

    @PostMapping("/wechat-bind")
    @Valid
    WechatBindDetailsVO getWechatBind(@RequestBody HashMap<String, Object> param) {
        return iUmsUserAuthWechatService.getWechatBind(
                (String) param.get("code"),
                (String) param.get("username"),
                (String) param.get("avatarUrl"),
                (Integer) param.get("gender")
        );
    }

    @GetMapping(value = "/authentication/open-id")
    UserOauthVO getBindUid(@RequestParam String openId) {
        return iUmsUserAuthWechatService.getBindUid(openId);
    }
}
