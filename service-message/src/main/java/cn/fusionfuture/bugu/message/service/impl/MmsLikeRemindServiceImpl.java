package cn.fusionfuture.bugu.message.service.impl;

import cn.fusionfuture.bugu.message.mapper.MmsLikeRemindMapper;
import cn.fusionfuture.bugu.message.service.IMmsLikeRemindService;
import cn.fusionfuture.bugu.message.vo.LikeVO;
import cn.fusionfuture.bugu.message.vo.PunchVO;
import cn.fusionfuture.bugu.pojo.entity.MmsLikeRemind;
import cn.fusionfuture.bugu.pojo.entity.MmsPunchRemind;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiLan
 * @version 1.0
 * @class MmsLikeRemindServiceImpl
 * @description 实现点赞逻辑
 * @date 2020/8/22 14:29
 */

@Service
public class MmsLikeRemindServiceImpl extends ServiceImpl<MmsLikeRemindMapper, MmsLikeRemind> implements IMmsLikeRemindService {

    @Autowired
    MmsLikeRemindMapper mmsLikeRemindMapper;

    /**
     * TODO
     * @author LiLan
     * @since 2020/8/22 14:32
     * @param mmsLikeRemind
     * @return void
     **/
    @Override
    public void addLikeRemind(MmsLikeRemind mmsLikeRemind) {
        mmsLikeRemindMapper.insert(mmsLikeRemind);
    }

    @Override
    public List<LikeVO> getLikeRemind(Long id) {

        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("receive_user_id", id);
        List<MmsLikeRemind> mmsLikeRemindList = mmsLikeRemindMapper.selectByMap(columnMap);
        System.out.println("查询出数据");
        List<LikeVO> likeVOList = new ArrayList<>();
        for (MmsLikeRemind mmsLikeRemind : mmsLikeRemindList) {
            LikeVO likeVO = new LikeVO();
            likeVO.setId(mmsLikeRemind.getId());
            likeVO.setSendTime(mmsLikeRemind.getCreateTime());
            likeVO.setSendUserId(mmsLikeRemind.getSendUserId());
            likeVO.setReceiveUserId(mmsLikeRemind.getReceiveUserId());
            likeVO.setPunchId(mmsLikeRemind.getPunchId());
            likeVO.setPlanTypeId(mmsLikeRemind.getPlanTypeId());
            likeVO.setIsChecked(mmsLikeRemind.getIsChecked());
            likeVO.setIsHidden(mmsLikeRemind.getIsHidden());
            //          TODO:调用其他微服务获取完整数据

            likeVOList.add(likeVO);
        }
        return likeVOList;
    }
}