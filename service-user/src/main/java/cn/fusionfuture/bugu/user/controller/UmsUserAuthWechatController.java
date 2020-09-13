package cn.fusionfuture.bugu.user.controller;


import cn.fusionfuture.bugu.user.service.IUmsUserAuthWechatService;
import cn.fusionfuture.bugu.user.vo.WechatBindDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class UmsUserAuthWechatController {
    @Autowired
    IUmsUserAuthWechatService iUmsUserAuthWechatService;

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
}
