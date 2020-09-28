package cn.fusionfuture.bugu.message.controller;

import cn.fusionfuture.bugu.message.service.IMmsSystemUserService;
import cn.fusionfuture.bugu.message.vo.MessageVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiLan
 * @version 1.0
 * @class MmsSystemUserController
 * @description 系统消息提醒
 * @date 2020/8/27 15:23
 */
@RestController
@Api(tags="系统消息提醒")
public class MmsSystemUserController {

    @Autowired
    IMmsSystemUserService iMmsSystemUserService;
    /**
     * 接收系统消息列表
     * @author LiLan
     * @since 2020/9/25 13:28
     * @param pn 当前所在页id
     * @param ps 页面size
     * @param id 接收者的id，即当前用户id
     * @return com.github.pagehelper.PageInfo<?>
     **/
    @GetMapping(value = "/system-message")
    @ApiOperation(value = "接收系统消息列表")
    public PageInfo<?> getMessageList(@ApiParam(value = "当前所在页") @RequestParam(defaultValue = "1") Integer pn, @ApiParam(value = "页面size") @RequestParam(defaultValue = "5") Integer ps, @ApiParam(value = "接收者的id，即当前用户id") @RequestParam(name = "id") Long id){
        PageInfo<MessageVO> messageVOList = iMmsSystemUserService.getAllSystem(pn, ps, id);
        return messageVOList;
    }

    /**
     * 接收具体某个系统发送的消息
     * @author LiLan
     * @since 2020/9/25 13:28
     * @param pn 当前所在页id
     * @param ps 页面size
     * @param id 接收者的id，即当前用户id
     * @param sid 发送消息的系统id
     * @return com.github.pagehelper.PageInfo<?>
     **/
    @GetMapping(value = "/system-detail")
    @ApiOperation(value = "接收具体某个系统发送的消息")
    public PageInfo<?> getSystemMessage(@ApiParam(value = "当前所在页") @RequestParam(defaultValue = "1") Integer pn, @ApiParam(value = "页面size") @RequestParam(defaultValue = "5") Integer ps,@ApiParam(value = "当前用户id") @RequestParam(name = "id")Long id,@ApiParam(value = "系统id") @RequestParam(name = "sid")Long sid){
        PageInfo<MessageVO> messageVOList = iMmsSystemUserService.getOneSystemAll(pn, ps,id,sid);
        return messageVOList;
    }
}
