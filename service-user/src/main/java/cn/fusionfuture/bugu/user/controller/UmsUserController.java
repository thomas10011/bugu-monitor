package cn.fusionfuture.bugu.user.controller;


import cn.fusionfuture.bugu.dispose.annotation.EnableIgnoreResponse;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.api.ResultCode;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import cn.fusionfuture.bugu.user.service.IUmsUserService;
import cn.fusionfuture.bugu.user.vo.UserDetailsVO;
import cn.fusionfuture.bugu.user.vo.WechatBindDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@RestController
@Api(tags = "用户界面相关接口")
public class UmsUserController {

    @Autowired
    IUmsUserService iUmsUserService;

    @Autowired
    HttpServletRequest request;


    @GetMapping("/user/{uid}/details")
    @Valid
    @ApiOperation(value = "获取个人信息")
    CommonResult<?> getPersonalDetails(@ApiParam(value = "用户id") @PathVariable("uid") Long id) {
        Long uid = Long.parseLong(request.getHeader("uid"));
        UserDetailsVO userDetailsVO = iUmsUserService.getPersonalDetails(id, uid);
        if (userDetailsVO == null) {
            return CommonResult.fail(ResultCode.USER_NOT_EXISTED);
        } else {
            return CommonResult.success().append(userDetailsVO);
        }
    }

    @GetMapping("/get-details-for-message")
    @Valid
    @ApiOperation(value = "远程调用获取用户名和头像")
    HashMap<String,String> getDetailsForMessage(@ApiParam(value = "用户id") @RequestParam Long id){
        return iUmsUserService.getDetailsForMessage(id);
    }

    @GetMapping("/user/{uid}/avatar-url")
    @ApiOperation("根据id查询用户头像地址")
    String getUserAvatar(@PathVariable("uid") Long uid) {
        return iUmsUserService.getById(uid).getAvatarUrl();
    }
}
