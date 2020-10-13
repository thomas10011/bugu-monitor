package cn.fusionfuture.bugu.message.service.impl;

import cn.fusionfuture.bugu.message.feign.MonitorFeignService;
import cn.fusionfuture.bugu.message.feign.PkFeignService;
import cn.fusionfuture.bugu.message.feign.UserFeignService;
import cn.fusionfuture.bugu.message.util.PageUtil;
import cn.fusionfuture.bugu.message.vo.VoteVO;
import cn.fusionfuture.bugu.pojo.entity.MmsPunchRemind;
import cn.fusionfuture.bugu.pojo.entity.MmsVoteRemind;
import cn.fusionfuture.bugu.pojo.entity.MmsVoteRemind;
import cn.fusionfuture.bugu.message.mapper.MmsVoteRemindMapper;
import cn.fusionfuture.bugu.message.service.IMmsVoteRemindService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.fusionfuture.bugu.message.vo.feignvo.BasicPunchVO;
import cn.fusionfuture.bugu.message.vo.feignvo.PunchWithImageVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiLan
 * @version 1.0
 * @class MmsVoteRemindServiceImpl
 * @description TODO
 * @date 2020/8/22 14:56
 */
@Service
public class MmsVoteRemindServiceImpl extends ServiceImpl<MmsVoteRemindMapper, MmsVoteRemind> implements IMmsVoteRemindService {
    @Autowired
    MmsVoteRemindMapper mmsVoteRemindMapper;

    @Autowired
    private MonitorFeignService monitorFeignService;

    @Autowired
    private PkFeignService pkFeignService;

    @Autowired
    private UserFeignService userFeignService;


    @Override
    public void addVoteRemind(MmsVoteRemind mmsVoteRemind) {
        mmsVoteRemindMapper.insert(mmsVoteRemind);
    }

    @Override
    public PageInfo<VoteVO> getVoteRemind(Integer pn, Integer ps, Long id) {
        final Integer MONITOR_PLAN = 1;
        final Integer PK_PLAN = 2;

        QueryWrapper<MmsVoteRemind> queryWrapper = new QueryWrapper<MmsVoteRemind>();
        queryWrapper.eq("receive_user_id", id)
                .eq("is_hidden",false)
                .orderByDesc("create_time");

        PageHelper.startPage(pn, ps);
        PageInfo<MmsVoteRemind> mmsVoteRemindList =new PageInfo<>(mmsVoteRemindMapper.selectList(queryWrapper)) ;
        System.out.println("查询出数据");

        MmsVoteRemind updateEntity = new MmsVoteRemind();
        updateEntity.setIsChecked(true);
        mmsVoteRemindMapper.update(updateEntity,queryWrapper);

        List<VoteVO> voteVOList = new ArrayList<>();
        for (MmsVoteRemind mmsVoteRemind : mmsVoteRemindList.getList()) {
            VoteVO voteVO = new VoteVO();
            BeanUtils.copyProperties(mmsVoteRemind,voteVO);

            // 调用其他微服务获取完整数据
            HashMap<String,String> sender = userFeignService.getDetailsForMessage(voteVO.getSendUserId()).getData();
            voteVO.setSendUserName(sender.get("userName"));
            voteVO.setSendAvatarUrl(sender.get("avatarUrl"));

            int planType=mmsVoteRemind.getPlanTypeId();
//            pk计划信息
            Long punchId = mmsVoteRemind.getPunchId();
            if(planType==PK_PLAN){
                PunchWithImageVO punchWithImageVO = pkFeignService.queryPunchWithImageVO(punchId).getData();
                voteVO.setPlanPattern(punchWithImageVO.getPlanPattern());
                voteVO.setPlanName(punchWithImageVO.getName());
                voteVO.setPunchContent(punchWithImageVO.getContent());
                if(punchWithImageVO.getImageUrls().size()>0){
                    voteVO.setPunchImageUrl(punchWithImageVO.getImageUrls().get(0));
                }
                voteVO.setVoteCount(punchWithImageVO.getAgreeCount()+punchWithImageVO.getDisagreeCount());
                if(voteVO.getIsApproved()){
                    voteVO.setCurrentVoteCount(punchWithImageVO.getAgreeCount());
                }else{
                    voteVO.setCurrentVoteCount(punchWithImageVO.getDisagreeCount());
                }
            }else{
//                监督计划
                BasicPunchVO basicPunchVO = monitorFeignService.queryBasicPunchVO(punchId).getData();
                voteVO.setPlanPattern(basicPunchVO.getPlanPattern());
                voteVO.setPlanName(basicPunchVO.getName());
                voteVO.setPunchContent(basicPunchVO.getContent());
                if(basicPunchVO.getImageUrls().size()>0){
                    voteVO.setPunchImageUrl(basicPunchVO.getImageUrls().get(0));
                }
                voteVO.setVoteCount(basicPunchVO.getAgreeCount()+basicPunchVO.getDisagreeCount());
                if(voteVO.getIsApproved()){
                    voteVO.setCurrentVoteCount(basicPunchVO.getAgreeCount());
                }else{
                    voteVO.setCurrentVoteCount(basicPunchVO.getDisagreeCount());
                }
            }

            voteVOList.add(voteVO);
        }
        PageUtil pageUtil = new PageUtil();
        PageInfo<VoteVO> voteVOPageInfo = new PageInfo<>(voteVOList);
        pageUtil.copyAtrr(mmsVoteRemindList,voteVOPageInfo);
//        BeanUtils.copyProperties(mmsVoteRemindList,voteVOPageInfo);
        return voteVOPageInfo;
    }
}
