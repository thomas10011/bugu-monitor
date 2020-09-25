package cn.fusionfuture.bugu.message.controller;

import cn.fusionfuture.bugu.message.service.IMmsPrivateChatService;
import cn.fusionfuture.bugu.message.vo.MessageVO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.entity.MmsPrivateChat;
import cn.fusionfuture.bugu.utils.oss.OssUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author LiLan
 * @version 1.0
 * @class MmsPrivateChatController
 * @description 私信
 * @date 2020/8/27 10:49
 */
@RestController
public class MmsPrivateChatController {

    @Autowired
    IMmsPrivateChatService iMmsPrivateChatService;

    @Autowired(required = false)
    private HttpServletRequest request;

    /**
     * 发送私信
     * @author LiLan
     * @since 2020/9/25 13:18
     * @param mmsPrivateChat 发送的私信
     * @return cn.fusionfuture.bugu.pojo.api.CommonResult<?>
     **/
    @PostMapping(value="/private-chat")
    @ApiOperation(value = "发送私信")
    public CommonResult<?> sendChat(MmsPrivateChat mmsPrivateChat){
        iMmsPrivateChatService.sendPraivateChat(mmsPrivateChat);
        return CommonResult.success();
    }
    /**
     * 获取消息列表
     * @author LiLan
     * @since 2020/9/25 13:18
     * @param pn 当前所在页id
     * @param ps 页面size
     * @param id 接收者的id，即当前用户id
     * @return com.github.pagehelper.PageInfo<?>
     **/
    @GetMapping(value = "/private-chat")
    @ApiOperation(value = "获取私信列表")
    public PageInfo<?> getAllUser(@RequestParam(defaultValue = "1") Integer pn, @RequestParam(defaultValue = "5") Integer ps,@RequestParam(name = "id") Long id){
        PageInfo<MessageVO> messageVOList = iMmsPrivateChatService.getAllUserChat(pn, ps, id);
        return messageVOList;
    }

    /**
     * 获取某个用户发送的全部信息
     * @author LiLan
     * @since 2020/9/25 13:19
     * @param pn 当前所在页id
     * @param ps 页面size
     * @param mid 发送者的id
     * @param sid 接收者的id，即当前用户id
     * @return java.util.List<cn.fusionfuture.bugu.message.vo.MessageVO>
     **/
    @GetMapping(value = "/private-detail")
    @ApiOperation(value = "获取某个用户发送的信息")
    public PageInfo<MessageVO> getOneUser(@RequestParam(defaultValue = "1") Integer pn, @RequestParam(defaultValue = "5") Integer ps, @RequestParam(name = "mid") Long mid, @RequestParam(name="sid")Long sid){
        PageInfo<MessageVO> messageVOList = iMmsPrivateChatService.getOneUserAllChat(pn, ps, mid,sid);
        return messageVOList;
    }

    /**
     * 获取oss的policy用于上传图片
     * @author LiLan
     * @since 2020/9/25 15:19
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    @GetMapping(value = "/privateMessage/policy")
    @ApiOperation(value = "获取oss的policy用于上传图片发送私信的")
    public Map<String, String> getPolicy() throws IOException, ServletException {
        return OssUtil.getPolicy("privateMessage/");
    }

}
