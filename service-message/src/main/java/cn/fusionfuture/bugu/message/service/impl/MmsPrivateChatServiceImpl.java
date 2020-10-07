package cn.fusionfuture.bugu.message.service.impl;
import cn.fusionfuture.bugu.message.feign.UserFeignService;
import cn.fusionfuture.bugu.message.util.PageUtil;
import cn.fusionfuture.bugu.message.vo.LikeVO;
import cn.fusionfuture.bugu.message.vo.MessageVO;
import cn.fusionfuture.bugu.pojo.entity.MmsLikeRemind;
import cn.fusionfuture.bugu.pojo.entity.MmsPrivateChat;
import cn.fusionfuture.bugu.message.mapper.MmsPrivateChatMapper;
import cn.fusionfuture.bugu.message.service.IMmsPrivateChatService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiLan
 * @version 1.0
 * @class MmsPrivateChatServiceImpl
 * @description TODO
 * @date 2020/8/27 10:14
 */
@Service
public class MmsPrivateChatServiceImpl extends ServiceImpl<MmsPrivateChatMapper, MmsPrivateChat> implements IMmsPrivateChatService {

    @Autowired
    MmsPrivateChatMapper mmsPrivateChatMapper;

    @Autowired
    UserFeignService userFeignService;

    @Override
    public void sendPraivateChat(MmsPrivateChat mmsPrivateChat) {
        mmsPrivateChatMapper.insert(mmsPrivateChat);
    }

    @Override
    public PageInfo<MessageVO> getAllUserChat(Integer pn, Integer ps, Long id) {
        QueryWrapper<MmsPrivateChat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("receive_user_id",id);
        queryWrapper.orderByDesc("create_time");
        Map<Long,MmsPrivateChat> pairedChatMap = new HashMap<>();


        List<MmsPrivateChat> mmsReceivePrivateChatList = mmsPrivateChatMapper.selectList(queryWrapper);
        QueryWrapper<MmsPrivateChat> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("send_user_id",id);
        queryWrapper1.orderByDesc("create_time");
        List<MmsPrivateChat> mmsSendPrivateChatList = mmsPrivateChatMapper.selectList(queryWrapper1);
        for(MmsPrivateChat mmsPrivateChat: mmsReceivePrivateChatList){
            if(!pairedChatMap.containsKey(mmsPrivateChat.getSendUserId())){
                pairedChatMap.put(mmsPrivateChat.getSendUserId(),mmsPrivateChat);
            }
        }
        for(MmsPrivateChat mmsPrivateChat: mmsSendPrivateChatList){
            if(!pairedChatMap.containsKey(mmsPrivateChat.getReceiveUserId())){
                pairedChatMap.put(mmsPrivateChat.getReceiveUserId(),mmsPrivateChat);
            }
        }

        System.out.println("查询出数据");
        List<MessageVO> messageVOList = new ArrayList<>();
        for(MmsPrivateChat mmsPrivateChat:pairedChatMap.values()) {
            MessageVO messageVO = new MessageVO();
            BeanUtils.copyProperties(mmsPrivateChat,messageVO);
            //  调用其他微服务获取完整数据
            HashMap<String,String> sender = userFeignService.getDetailsForMessage(messageVO.getSendUserId()).getData();
            messageVO.setSendUserName(sender.get("userName"));
            messageVO.setSendAvatarUrl(sender.get("avatarUrl"));

            HashMap<String,String> reciever = userFeignService.getDetailsForMessage(messageVO.getReceiveUserId()).getData();
            messageVO.setReceiveUserName(reciever.get("userName"));
            messageVO.setReceiveAvatarUrl(reciever.get("avatarUrl"));
            messageVOList.add(messageVO);

        }
//        TODO: 如何合理进行分页
        PageHelper.startPage(pn, ps);
        return new PageInfo<>(messageVOList);
    }

    @Override
    public PageInfo<MessageVO> getOneUserAllChat(Integer pn, Integer ps, Long id, Long sendId) {
        QueryWrapper<MmsPrivateChat> queryWrapper = new QueryWrapper<MmsPrivateChat>();
        queryWrapper.eq("receive_user_id", id);
        queryWrapper.eq("is_hidden",false);
        queryWrapper.eq("send_user_id",sendId);

        PageHelper.startPage(pn, ps);
        PageInfo<MmsPrivateChat> mmsReceivePrivateChatList = new PageInfo<>(mmsPrivateChatMapper.selectList(queryWrapper)) ;

        MmsPrivateChat updateEntity = new MmsPrivateChat();
        updateEntity.setIsChecked(true);
        mmsPrivateChatMapper.update(updateEntity,queryWrapper);

        List<MessageVO> messageVOList = new ArrayList<>();
        for(MmsPrivateChat mmsPrivateChat:mmsReceivePrivateChatList.getList()) {
            MessageVO messageVO = new MessageVO();
            BeanUtils.copyProperties(mmsPrivateChat,messageVO);
            //调用其他微服务获取完整数据
            HashMap<String,String> sender = userFeignService.getDetailsForMessage(messageVO.getSendUserId()).getData();
            messageVO.setSendUserName(sender.get("userName"));
            messageVO.setSendAvatarUrl(sender.get("avatarUrl"));

            HashMap<String,String> reciever = userFeignService.getDetailsForMessage(messageVO.getReceiveUserId()).getData();
            messageVO.setReceiveUserName(reciever.get("userName"));
            messageVO.setReceiveAvatarUrl(reciever.get("avatarUrl"));
            messageVOList.add(messageVO);

        }
        PageUtil pageUtil = new PageUtil();
        PageInfo<MessageVO> messageVOPageInfo = new PageInfo<>(messageVOList);
        pageUtil.copyAtrr(mmsReceivePrivateChatList,messageVOPageInfo);
        return messageVOPageInfo;
    }
}
