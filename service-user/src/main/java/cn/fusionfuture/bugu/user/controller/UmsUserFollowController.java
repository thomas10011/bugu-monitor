package cn.fusionfuture.bugu.user.controller;


import cn.fusionfuture.bugu.user.service.IUmsUserFollowService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/user/ums-user-follow")
@Api(tags = "查询用户之间的关注信息")
public class UmsUserFollowController {

    @Autowired
    IUmsUserFollowService userFollowService;

    @ApiOperation(value = "根据用户ID查询用户关注列表")
    @GetMapping(value = "/follow-list/{uid}")
    PageInfo<?> queryFollowListByPage(@PathVariable Long uid, @RequestParam Integer pn, @RequestParam Integer ps) {
        return userFollowService.queryUmsUserFollowByPage(pn, ps, uid);
    }

    @ApiOperation(value = "根据用户ID查询用户粉丝列表")
    @GetMapping(value = "/fans-list/{uid}")
    PageInfo<?> queryFansListByPage(@PathVariable Long uid, @RequestParam Integer pn, @RequestParam Integer ps) {
        return userFollowService.queryUmsUserFansByPage(pn, ps, uid);
    }

    @ApiOperation(value = "关注用户")
    @GetMapping(value = "/follow/{uid}")
    String followUser(@PathVariable Long uid, @RequestParam Long fuid) {
        if (userFollowService.followUser(uid, fuid)) {
            return "关注成功！";
        }
        return "您已关注过该用户！";
    }

    @ApiOperation(value = "取消关注用户")
    @DeleteMapping(value = "/follow/{uid}")
    void unFollowUser(@PathVariable Long uid, @RequestParam Long fuid) {
        userFollowService.unFollowUser(uid, fuid);
    }
}
