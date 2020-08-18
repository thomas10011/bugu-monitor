package cn.fusionfuture.bugu.user.controller;


import cn.fusionfuture.bugu.pojo.entity.UmsUserFollow;
import cn.fusionfuture.bugu.user.service.IUmsUserFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/user/ums-monitor-achievement")
public class UmsMonitorAchievementController {

    @Autowired
    IUmsUserFollowService iUmsUserFollowService;

    @GetMapping(value = "/test/swagger")
    UmsUserFollow testSwagger() {
        return iUmsUserFollowService.getById(1);
    }
}
