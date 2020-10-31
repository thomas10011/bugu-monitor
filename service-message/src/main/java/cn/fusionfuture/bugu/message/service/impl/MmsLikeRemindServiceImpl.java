package cn.fusionfuture.bugu.message.service.impl;

import cn.fusionfuture.bugu.message.feign.MonitorFeignService;
import cn.fusionfuture.bugu.message.feign.PkFeignService;
import cn.fusionfuture.bugu.message.feign.UserFeignService;
import cn.fusionfuture.bugu.message.mapper.MmsLikeRemindMapper;
import cn.fusionfuture.bugu.message.service.IMmsLikeRemindService;
import cn.fusionfuture.bugu.message.util.PageUtil;
import cn.fusionfuture.bugu.message.vo.LikeVO;
import cn.fusionfuture.bugu.message.vo.feignvo.PunchForMessageDTO;
import cn.fusionfuture.bugu.pojo.entity.MmsLikeRemind;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private MonitorFeignService monitorFeignService;

    @Autowired
    private PkFeignService pkFeignService;

    @Autowired
    private UserFeignService userFeignService;

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
    public PageInfo<LikeVO> getLikeRemind(Integer pn, Integer ps, Long id) {
        final Integer MONITOR_PLAN = 1;
        final Integer PK_PLAN = 2;

        QueryWrapper<MmsLikeRemind> queryWrapper = new QueryWrapper<MmsLikeRemind>();
        queryWrapper.eq("receive_user_id", id)
                .eq("is_hidden",false)
                .orderByDesc("create_time");

        PageHelper.startPage(pn, ps);
        PageInfo<MmsLikeRemind> mmsLikeRemindList = new PageInfo<>(mmsLikeRemindMapper.selectList(queryWrapper)) ;

        MmsLikeRemind updateEntity = new MmsLikeRemind();
        updateEntity.setIsChecked(true);
        mmsLikeRemindMapper.update(updateEntity,queryWrapper);

        System.out.println("查询出数据");
        List<LikeVO> likeVOList = new ArrayList<>();
        for (MmsLikeRemind mmsLikeRemind : mmsLikeRemindList.getList()) {
            LikeVO likeVO = new LikeVO();
            BeanUtils.copyProperties(mmsLikeRemind,likeVO);

            HashMap<String,String> sender = userFeignService.getDetailsForMessage(likeVO.getSendUserId()).getData();
            likeVO.setSendUserName(sender.get("userName"));
            likeVO.setSendAvatarUrl(sender.get("avatarUrl"));

            int planType=mmsLikeRemind.getPlanTypeId();
//            pk计划信息
            Long punchId = mmsLikeRemind.getPunchId();
            if(planType==PK_PLAN){
                PunchForMessageDTO punchForMessageDTO = pkFeignService.queryPunchForMessageDTO(punchId).getData();
                likeVO.setPlanPattern(punchForMessageDTO.getPlanPattern());
                likeVO.setPlanName(punchForMessageDTO.getName());
                likeVO.setPunchContent(punchForMessageDTO.getContent());
                if(punchForMessageDTO.getImageUrls().size()>0){
                    likeVO.setPunchImageUrl(punchForMessageDTO.getImageUrls().get(0));
                }
                likeVO.setLikeCount(punchForMessageDTO.getLikeCount());
            }else{
//                监督计划
                PunchForMessageDTO punchForMessageDTO = monitorFeignService.queryPunchForMessageDTO(punchId).getData();
                likeVO.setPlanPattern(punchForMessageDTO.getPlanPattern());
                likeVO.setPlanName(punchForMessageDTO.getName());
                likeVO.setPunchContent(punchForMessageDTO.getContent());
                if(punchForMessageDTO.getImageUrls().size()>0){
                    likeVO.setPunchImageUrl(punchForMessageDTO.getImageUrls().get(0));
                }
                likeVO.setLikeCount(punchForMessageDTO.getLikeCount());
            }

            likeVOList.add(likeVO);
        }
        PageUtil pageUtil = new PageUtil();
        PageInfo<LikeVO> likeVOPageInfo = new PageInfo<>(likeVOList);
        pageUtil.copyAtrr(mmsLikeRemindList,likeVOPageInfo);
//        BeanUtils.copyProperties(mmsLikeRemindList,likeVOPageInfo);
        return likeVOPageInfo;
    }

    /**
     * 返回当前用户是否对该打卡进行点赞
     * @author LiLan
     * @since 2020/10/28 8:20
     * @param uid, pid
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean isLike(Long uid, Long pid) {
        QueryWrapper<MmsLikeRemind> queryWrapper = new QueryWrapper<MmsLikeRemind>();
        queryWrapper.eq("send_user_id", uid).eq("punch_id",pid);
        List<MmsLikeRemind> mmsLikeReminds = mmsLikeRemindMapper.selectList(queryWrapper);
        if(mmsLikeReminds.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean cancelLike(Long uid, Long pid) {
        QueryWrapper<MmsLikeRemind> queryWrapper = new QueryWrapper<MmsLikeRemind>();
        queryWrapper.eq("send_user_id", uid).eq("punch_id",pid);
        if(mmsLikeRemindMapper.delete(queryWrapper)>=1){
            return true;
        }
        return false;
    }
}