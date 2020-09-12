package cn.fusionfuture.bugu.user.controller;


import cn.fusionfuture.bugu.pojo.entity.UmsUserFollow;
import cn.fusionfuture.bugu.user.service.IUmsUserFollowService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/test")
public class UmsMonitorAchievementController {

    @Autowired
    IUmsUserFollowService iUmsUserFollowService;

    @GetMapping(value = "/swagger")
    @Valid
    PageInfo<?> testSwagger(@RequestParam Integer pn, @RequestParam Integer ps) {

        return iUmsUserFollowService.queryUmsUserFollowByPage(pn, ps);
    }

    @GetMapping(value = "security")
    String testSecurity() {
        return "test seurity result";
    }
}
