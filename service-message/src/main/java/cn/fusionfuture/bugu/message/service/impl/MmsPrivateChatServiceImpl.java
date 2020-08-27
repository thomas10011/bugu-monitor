package cn.fusionfuture.bugu.message.service.impl;
import cn.fusionfuture.bugu.message.vo.PrivateChatVO;
import cn.fusionfuture.bugu.pojo.entity.MmsPrivateChat;
import cn.fusionfuture.bugu.message.mapper.MmsPrivateChatMapper;
import cn.fusionfuture.bugu.message.service.IMmsPrivateChatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    @Override
    public void sendPraivateChat(MmsPrivateChat mmsPrivateChat) {
        mmsPrivateChatMapper.insert(mmsPrivateChat);
    }

    @Override
    public List<PrivateChatVO> getAllUserChat(Long id) {
        Map<Long,MmsPrivateChat> pairedChatMap = new HashMap<>();
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("receive_user_id",id);
        List<MmsPrivateChat> mmsReceivePrivateChatList = mmsPrivateChatMapper.selectByMap(columnMap);
        Map<String,Object> columnMap1 = new HashMap<>();
        columnMap1.put("send_user_id",id);
        List<MmsPrivateChat> mmsSendPrivateChatList = mmsPrivateChatMapper.selectByMap(columnMap1);
        for(MmsPrivateChat mmsPrivateChat: mmsReceivePrivateChatList){
            if(pairedChatMap.containsKey(mmsPrivateChat.getSendUserId())){
               LocalDateTime theTime = mmsPrivateChat.getCreateTime();
               LocalDateTime mapTime = pairedChatMap.get(mmsPrivateChat.getSendUserId()).getCreateTime();
                if(theTime.isAfter(mapTime)){
                    pairedChatMap.put(mmsPrivateChat.getSendUserId(),mmsPrivateChat);
                }
            }else{
                pairedChatMap.put(mmsPrivateChat.getSendUserId(),mmsPrivateChat);
            }
        }
        for(MmsPrivateChat mmsPrivateChat: mmsSendPrivateChatList){
            if(pairedChatMap.containsKey(mmsPrivateChat.getReceiveUserId())){
                LocalDateTime theTime = mmsPrivateChat.getCreateTime();
                LocalDateTime mapTime = pairedChatMap.get(mmsPrivateChat.getSendUserId()).getCreateTime();
                if(theTime.isAfter(mapTime)){
                    pairedChatMap.put(mmsPrivateChat.getReceiveUserId(),mmsPrivateChat);
                }
            }else{
                pairedChatMap.put(mmsPrivateChat.getReceiveUserId(),mmsPrivateChat);
            }
        }

        System.out.println("查询出数据");
        List<PrivateChatVO> privateChatVOList = new ArrayList<>();
        for(MmsPrivateChat mmsPrivateChat:pairedChatMap.values()) {
            PrivateChatVO privateChatVO = new PrivateChatVO();
            privateChatVO.setId(mmsPrivateChat.getId());
            privateChatVO.setSendTime(mmsPrivateChat.getCreateTime());
            privateChatVO.setSendUserId(mmsPrivateChat.getSendUserId());
            privateChatVO.setReceiveUserId(mmsPrivateChat.getReceiveUserId());
            privateChatVO.setIsChecked(mmsPrivateChat.getIsChecked());
            privateChatVO.setIsHidden(mmsPrivateChat.getIsHidden());
            privateChatVO.setMessageContent((mmsPrivateChat.getMessageContent()));
            privateChatVO.setImageUrl((mmsPrivateChat.getImageUrl()));
            privateChatVO.setSendTime(mmsPrivateChat.getCreateTime());
            //          TODO:调用其他微服务获取完整数据
            privateChatVOList.add(privateChatVO);

        }
        return privateChatVOList;
    }

    @Override
    public List<PrivateChatVO> getOneUserAllChat(Long id, Long sendId) {
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("receive_user_id",id);
        columnMap.put("send_user_id",sendId);
        List<MmsPrivateChat> mmsReceivePrivateChatList = mmsPrivateChatMapper.selectByMap(columnMap);
        List<PrivateChatVO> privateChatVOList = new ArrayList<>();
        for(MmsPrivateChat mmsPrivateChat:mmsReceivePrivateChatList) {
            PrivateChatVO privateChatVO = new PrivateChatVO();
            privateChatVO.setId(mmsPrivateChat.getId());
            privateChatVO.setSendTime(mmsPrivateChat.getCreateTime());
            privateChatVO.setSendUserId(mmsPrivateChat.getSendUserId());
            privateChatVO.setReceiveUserId(mmsPrivateChat.getReceiveUserId());
            privateChatVO.setIsChecked(mmsPrivateChat.getIsChecked());
            privateChatVO.setIsHidden(mmsPrivateChat.getIsHidden());
            privateChatVO.setMessageContent((mmsPrivateChat.getMessageContent()));
            privateChatVO.setImageUrl((mmsPrivateChat.getImageUrl()));
            privateChatVO.setSendTime(mmsPrivateChat.getCreateTime());
            //          TODO:调用其他微服务获取完整数据
            privateChatVOList.add(privateChatVO);

        }
        return privateChatVOList;
    }
}
