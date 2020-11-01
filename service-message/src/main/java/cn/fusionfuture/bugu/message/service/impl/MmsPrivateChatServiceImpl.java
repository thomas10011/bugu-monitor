package cn.fusionfuture.bugu.message.service.impl;
import cn.fusionfuture.bugu.message.feign.UserFeignService;
import cn.fusionfuture.bugu.message.mapper.MmsMessageOwnerMapper;
import cn.fusionfuture.bugu.message.util.PageUtil;
import cn.fusionfuture.bugu.message.vo.LikeVO;
import cn.fusionfuture.bugu.message.vo.MessageVO;
import cn.fusionfuture.bugu.pojo.entity.MmsLikeRemind;
import cn.fusionfuture.bugu.pojo.entity.MmsMessageOwner;
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
import java.util.*;

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
    MmsMessageOwnerMapper mmsMessageOwnerMapper;

    @Autowired
    UserFeignService userFeignService;

    @Override
    public void sendPraivateChat(MmsPrivateChat mmsPrivateChat) {
        mmsPrivateChatMapper.insert(mmsPrivateChat);
        MmsMessageOwner mmsMessageOwner1 = new MmsMessageOwner();
        Long messageId = mmsPrivateChat.getId();

//        System.out.println(mmsPrivateChatMapper.getMessageId());
        mmsMessageOwner1.setMessageId(messageId);
        mmsMessageOwner1.setOwnerId(mmsPrivateChat.getSendUserId());
        mmsMessageOwner1.setOtherId(mmsPrivateChat.getReceiveUserId());
        mmsMessageOwnerMapper.insert(mmsMessageOwner1);
        MmsMessageOwner mmsMessageOwner2 = new MmsMessageOwner();
        mmsMessageOwner2.setMessageId(messageId);
        mmsMessageOwner2.setOwnerId(mmsPrivateChat.getReceiveUserId());
        mmsMessageOwner2.setOtherId(mmsPrivateChat.getSendUserId());
        mmsMessageOwnerMapper.insert(mmsMessageOwner2);
    }

    @Override
    public PageInfo<MessageVO> getAllUserChat(Integer pn, Integer ps, Long id) {
        QueryWrapper<MmsPrivateChat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("receive_user_id",id).
                or().eq("send_user_id",id)
                .orderByDesc("create_time");
        Set<String> pairedChatSet = new HashSet<>();
        List<MmsPrivateChat> mmsPrivateChatArray = new ArrayList<>();


        List<MmsPrivateChat> mmsReceivePrivateChatList = mmsPrivateChatMapper.selectList(queryWrapper);
        for(MmsPrivateChat mmsPrivateChat: mmsReceivePrivateChatList){
            System.out.println(mmsPrivateChat);
            String sendIdStr = mmsPrivateChat.getSendUserId().toString();
            String receiveIdStr = mmsPrivateChat.getReceiveUserId().toString();
            System.out.println(sendIdStr+receiveIdStr);
            if(!pairedChatSet.contains(sendIdStr+receiveIdStr)&&!pairedChatSet.contains(receiveIdStr+sendIdStr)){
                pairedChatSet.add(sendIdStr+receiveIdStr);
                System.out.println("choiced");
                System.out.println(mmsPrivateChat);
                mmsPrivateChatArray.add(mmsPrivateChat);
            }
        }
        List<MessageVO> messageVOList = new ArrayList<>();
        for(MmsPrivateChat mmsPrivateChat:mmsPrivateChatArray) {
            MessageVO messageVO = new MessageVO();
            BeanUtils.copyProperties(mmsPrivateChat,messageVO);
            Long messageId = mmsPrivateChat.getId();
            QueryWrapper<MmsMessageOwner> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("message_id",messageId).eq("owner_id",id);
            List<MmsMessageOwner> mmsMessageOwnerList = mmsMessageOwnerMapper.selectList(queryWrapper1);
            if(mmsMessageOwnerList.size()>=1){
                Boolean isChecked = mmsMessageOwnerList.get(0).getIsChecked();
                messageVO.setIsChecked(isChecked);
            }
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
        queryWrapper.eq("receive_user_id", id)
                .eq("is_hidden",false)
                .eq("send_user_id",sendId);
        queryWrapper.or(wrapper -> wrapper.eq("receive_user_id", sendId).eq("send_user_id",id).eq("is_hidden",false));
        queryWrapper.orderByDesc("create_time");
        PageHelper.startPage(pn, ps);
        PageInfo<MmsPrivateChat> mmsReceivePrivateChatList = new PageInfo<>(mmsPrivateChatMapper.selectList(queryWrapper)) ;

//        更新message_owner表中的消息状态
        QueryWrapper<MmsMessageOwner> ownerQueryWrapper = new QueryWrapper<MmsMessageOwner>();
        ownerQueryWrapper.eq("owner_id",id).eq("other_id",sendId);
        MmsMessageOwner updateEntity = new MmsMessageOwner();
        updateEntity.setIsChecked(true);
        mmsMessageOwnerMapper.update(updateEntity,ownerQueryWrapper);

//        MmsPrivateChat updateEntity = new MmsPrivateChat();
//        updateEntity.setIsChecked(true);
//        mmsPrivateChatMapper.update(updateEntity,queryWrapper);

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
//        BeanUtils.copyProperties(mmsReceivePrivateChatList,messageVOPageInfo);
        return messageVOPageInfo;
    }
}
