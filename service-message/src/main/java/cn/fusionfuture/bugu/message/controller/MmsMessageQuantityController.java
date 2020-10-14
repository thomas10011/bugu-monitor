package cn.fusionfuture.bugu.message.controller;

import cn.fusionfuture.bugu.message.service.IMmsMessageQuantityService;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author DELL
 * @version 1.0
 * @class MmsMessageQuantityController
 * @description TODO
 * @date 2020/10/10 11:32
 */
@RestController
@Api(tags="获取消息数目")
public class MmsMessageQuantityController {
    @Autowired
    IMmsMessageQuantityService iMmsMessageQuantityService;

    @GetMapping(value = "/message-quantity")
    @ApiOperation(value = "获取消息数目")
    public Map<String, Integer> getMessageQuantity(@ApiParam(value = "当前用户id") @RequestParam(value = "userId") Long userId){
        return iMmsMessageQuantityService.GetMessageQuantity(userId);
    }
}
