package cn.fusionfuture.bugu.message.controller;

import cn.fusionfuture.bugu.message.service.IMmsPrivateChatService;
import cn.fusionfuture.bugu.message.vo.MessageVO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.entity.MmsPrivateChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author LiLan
 * @version 1.0
 * @class MmsPrivateChatController
 * @description TODO
 * @date 2020/8/27 10:49
 */
@Controller
public class MmsPrivateChatController {

    @Autowired
    IMmsPrivateChatService iMmsPrivateChatService;

    @PostMapping(value="/private-chat")
    public CommonResult<?> sendChat(MmsPrivateChat mmsPrivateChat){
        iMmsPrivateChatService.sendPraivateChat(mmsPrivateChat);
        return CommonResult.success();
    }

    @GetMapping(value = "private-chat")
    public List<MessageVO> getAllUser(@RequestParam(name = "id") Long id){
        List<MessageVO> messageVOList = iMmsPrivateChatService.getAllUserChat(id);
        return messageVOList;
    }

    @GetMapping(value = "private-detail")
    public List<MessageVO> getOneUser(@RequestParam(name = "id") Long id, @RequestParam(name="sendId")Long sendId){
        List<MessageVO> messageVOList = iMmsPrivateChatService.getOneUserAllChat(id,sendId);
        return messageVOList;
    }

}
