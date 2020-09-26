package cn.fusionfuture.bugu.message.service.impl;

import cn.fusionfuture.bugu.message.vo.MessageVO;
import cn.fusionfuture.bugu.pojo.entity.MmsPrivateChat;
import cn.fusionfuture.bugu.pojo.entity.MmsSystemMessage;
import cn.fusionfuture.bugu.message.mapper.MmsSystemMessageMapper;
import cn.fusionfuture.bugu.message.service.IMmsSystemMessageService;
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
 * @class MmsSystemMessageServiceImpl
 * @description TODO
 * @date 2020/8/27 11:04
 */
@Service
public class MmsSystemMessageServiceImpl extends ServiceImpl<MmsSystemMessageMapper, MmsSystemMessage> implements IMmsSystemMessageService  {
    @Autowired
    MmsSystemMessageMapper mmsSystemMessageMapper;
    @Override
    public List<MessageVO> getAllSystem(Long id) {
//        Map<String,Object> columnMap = new HashMap<>();
//        columnMap.put("receive_user_id",id);
//        List<MmsSystemMessage> mmsSystemMessageList = mmsSystemMessageMapper.selectByMap(columnMap);
//        Map<Long,MmsSystemMessage> pairedMessageMap = new HashMap<>();
//        for(MmsSystemMessage mmsSystemMessage:mmsSystemMessageList){
//            if(pairedMessageMap.containsKey(mmsSystemMessage.getSendUserId())){
//                LocalDateTime theTime = mmsSystemMessage.getCreateTime();
//                LocalDateTime mapTime = pairedMessageMap.get(mmsSystemMessage.getSendUserId()).getCreateTime();
//                if(theTime.isAfter(mapTime)){
//                    pairedMessageMap.put(mmsSystemMessage.getSendUserId(),mmsSystemMessage);
//                }
//            }else{
//                pairedMessageMap.put(mmsSystemMessage.getSendUserId(),mmsSystemMessage);
//            }
//        }
//        List<MessageVO> messageVOList = new ArrayList<>();
//        for(MmsPrivateChat mmsSystemMessage:mmsReceivePrivateChatList) {
//            MessageVO messageVO = new MessageVO();
//            messageVO.setId(mmsSystemMessage.getId());
//            messageVO.setSendTime(mmsSystemMessage.getCreateTime());
//            messageVO.setSendUserId(mmsSystemMessage.getSendUserId());
//            messageVO.setReceiveUserId(mmsSystemMessage.getReceiveUserId());
//            messageVO.setIsChecked(mmsSystemMessage.getIsChecked());
//            messageVO.setIsHidden(mmsSystemMessage.getIsHidden());
//            messageVO.setMessageContent((mmsSystemMessage.getMessageContent()));
//            messageVO.setImageUrl((mmsSystemMessage.getImageUrl()));
//            messageVO.setSendTime(mmsSystemMessage.getCreateTime());
//            //          TODO:调用其他微服务获取完整数据
//            messageVOList.add(messageVO);

//        }
        return null;
    }

    @Override
    public List<MessageVO> getOneSystemAll(Long id, Long systemId) {
        return null;
    }
}
