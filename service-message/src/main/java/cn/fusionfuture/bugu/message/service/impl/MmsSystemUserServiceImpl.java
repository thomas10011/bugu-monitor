package cn.fusionfuture.bugu.message.service.impl;

import cn.fusionfuture.bugu.message.mapper.MmsSystemMessageMapper;
import cn.fusionfuture.bugu.message.util.PageUtil;
import cn.fusionfuture.bugu.message.vo.MessageVO;
import cn.fusionfuture.bugu.message.vo.PunchVO;
import cn.fusionfuture.bugu.pojo.entity.MmsPrivateChat;
import cn.fusionfuture.bugu.pojo.entity.MmsSystemMessage;
import cn.fusionfuture.bugu.pojo.entity.MmsSystemUser;
import cn.fusionfuture.bugu.message.mapper.MmsSystemUserMapper;
import cn.fusionfuture.bugu.message.service.IMmsSystemUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
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
public class MmsSystemUserServiceImpl extends ServiceImpl<MmsSystemUserMapper, MmsSystemUser> implements IMmsSystemUserService {

    @Autowired
    MmsSystemUserMapper mmsSystemUserMapper;

    @Autowired
    MmsSystemMessageMapper mmsSystemMessageMapper;

    @Override
    public MmsSystemUser getSystemUser(Long id){
        MmsSystemUser mmsSystemUser= mmsSystemUserMapper.selectById(id);
        return mmsSystemUser;
    }

    @Override
    public PageInfo<MessageVO> getAllSystem(Integer pn, Integer ps, Long id) {
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("receive_user_id",id);
        List<MmsSystemUser> mmsSystemUserList = mmsSystemUserMapper.selectByMap(columnMap);
        Map<Long,MmsSystemUser> pairedMessageMap = new HashMap<>();
        List<MessageVO> messageVOList = new ArrayList<>();
        for(MmsSystemUser mmsSystemUser:mmsSystemUserList){
            if(!pairedMessageMap.containsKey(mmsSystemUser.getSendUserId())) {
                LocalDateTime theTime = mmsSystemUser.getCreateTime();
                LocalDateTime mapTime = pairedMessageMap.get(mmsSystemUser.getSendUserId()).getCreateTime();
                if(theTime.isAfter(mapTime)){
                    pairedMessageMap.put(mmsSystemUser.getSendUserId(),mmsSystemUser);
                }
            }else{
                pairedMessageMap.put(mmsSystemUser.getSendUserId(),mmsSystemUser);
            }
        }
        for(MmsSystemUser mmsSystemUser:pairedMessageMap.values()){
            MessageVO messageVO = new MessageVO();
            messageVO.setId(mmsSystemUser.getId());
            messageVO.setSendTime(mmsSystemUser.getCreateTime());
            messageVO.setReceiveUserId(mmsSystemUser.getReceiveUserId());
            messageVO.setSendUserId(mmsSystemUser.getSendUserId());
            messageVO.setIsChecked(mmsSystemUser.getIsChecked());
            messageVO.setIsHidden(mmsSystemUser.getIsHidden());
            Long systemMessageId = mmsSystemUser.getSystemMessageId();
            MmsSystemMessage mmsSystemMessage = mmsSystemMessageMapper.selectById(systemMessageId);
            messageVO.setMessageContent((mmsSystemMessage.getMessageContent()));
            messageVO.setImageUrl((mmsSystemMessage.getImageUrl()));
            //          TODO:调用其他微服务获取完整数据
            messageVOList.add(messageVO);
        }
//        TODO:合理分页
        return new PageInfo<>(messageVOList);
    }

    @Override
    public PageInfo<MessageVO> getOneSystemAll(Integer pn, Integer ps,Long id, Long sendId) {
        QueryWrapper<MmsSystemUser> queryWrapper = new QueryWrapper<MmsSystemUser>();
        queryWrapper.eq("receive_user_id", id);
        queryWrapper.eq("is_hidden",false);
        queryWrapper.eq("send_user_id",sendId);

        PageInfo<MmsSystemUser> mmsSystemUserList =new PageInfo<>(mmsSystemUserMapper.selectList(queryWrapper)) ;

        MmsSystemUser updateEntity = new MmsSystemUser();
        updateEntity.setIsChecked(true);
        mmsSystemUserMapper.update(updateEntity,queryWrapper);

        List<MessageVO> messageVOList = new ArrayList<>();
        for(MmsSystemUser mmsSystemUser:mmsSystemUserList.getList()) {
            MessageVO messageVO = new MessageVO();
            messageVO.setId(mmsSystemUser.getId());
            messageVO.setSendTime(mmsSystemUser.getCreateTime());
            messageVO.setSendUserId(mmsSystemUser.getSendUserId());
            messageVO.setReceiveUserId(mmsSystemUser.getReceiveUserId());
            messageVO.setIsChecked(mmsSystemUser.getIsChecked());
            messageVO.setIsHidden(mmsSystemUser.getIsHidden());

            MmsSystemMessage mmsSystemMessage = mmsSystemMessageMapper.selectById(mmsSystemUser.getSystemMessageId());
            messageVO.setMessageContent((mmsSystemMessage.getMessageContent()));
            messageVO.setImageUrl((mmsSystemMessage.getImageUrl()));
            //          TODO:调用其他微服务获取完整数据
            messageVOList.add(messageVO);

        }
        PageUtil pageUtil = new PageUtil();
        PageInfo<MessageVO> messageVOPageInfo = new PageInfo<>(messageVOList);
        pageUtil.copyAtrr(mmsSystemUserList,messageVOPageInfo);
        return messageVOPageInfo;
    }
}
