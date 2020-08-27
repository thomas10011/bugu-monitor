package cn.fusionfuture.bugu.message.controller;

import cn.fusionfuture.bugu.message.service.IMmsPrivateChatService;
import cn.fusionfuture.bugu.message.vo.PrivateChatVO;
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
    public List<PrivateChatVO> getAllUser(@RequestParam(name = "id") Long id){
        List<PrivateChatVO> privateChatVOList = iMmsPrivateChatService.getAllUserChat(id);
        return privateChatVOList;
    }

    @GetMapping(value = "private-chat")
    public List<PrivateChatVO> getOneUser(@RequestParam(name = "id") Long id,@RequestParam(name="sendId")Long sendId){
        List<PrivateChatVO> privateChatVOList = iMmsPrivateChatService.getOneUserAllChat(id,sendId);
        return privateChatVOList;
    }

}
