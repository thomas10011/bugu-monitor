package cn.fusionfuture.bugu.message.controller;

import cn.fusionfuture.bugu.message.service.IMmsSystemUserService;
import cn.fusionfuture.bugu.message.vo.MessageVO;
import cn.fusionfuture.bugu.pojo.entity.MmsSystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiLan
 * @version 1.0
 * @class MmsSystemUserController
 * @description TODO
 * @date 2020/8/27 15:23
 */
@RestController
public class MmsSystemUserController {

    @Autowired
    IMmsSystemUserService iMmsSystemUserService;
    @GetMapping(value = "/system-message")
    public List<MessageVO> getMessageList(@RequestParam(name = "id")Long id){
      List<MessageVO> messageVOList = iMmsSystemUserService.getAllSystem(id);
      return messageVOList;
    }

    @GetMapping(value = "/system-detail")
    public List<MessageVO> getSystemMessage(@RequestParam(name = "id")Long id,@RequestParam(name = "sid")Long sendId){
        List<MessageVO> messageVOList = iMmsSystemUserService.getOneSystemAll(id,sendId);
        return messageVOList;
    }
}
