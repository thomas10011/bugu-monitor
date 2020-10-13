package cn.fusionfuture.bugu.message.service.impl;

import cn.fusionfuture.bugu.message.feign.MonitorFeignService;
import cn.fusionfuture.bugu.message.feign.PkFeignService;
import cn.fusionfuture.bugu.message.feign.UserFeignService;
import cn.fusionfuture.bugu.message.util.PageUtil;
import cn.fusionfuture.bugu.message.vo.CommentVO;
import cn.fusionfuture.bugu.message.vo.EnrollVO;
import cn.fusionfuture.bugu.message.vo.feignvo.SimpleMonitorPlanVO;
import cn.fusionfuture.bugu.message.vo.feignvo.SimplePkPlanVO;
import cn.fusionfuture.bugu.pojo.entity.MmsCommentRemind;
import cn.fusionfuture.bugu.pojo.entity.MmsEnrollRemind;
import cn.fusionfuture.bugu.message.mapper.MmsEnrollRemindMapper;
import cn.fusionfuture.bugu.message.service.IMmsEnrollRemindService;
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
 * <p>
 *  服务实现类
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@Service
public class MmsEnrollRemindServiceImpl extends ServiceImpl<MmsEnrollRemindMapper, MmsEnrollRemind> implements IMmsEnrollRemindService {
    final Integer MONITOR_PLAN = 1;
    final Integer PK_PLAN = 2;

    @Autowired
    MmsEnrollRemindMapper mmsEnrollRemindMapper;

    @Autowired
    private MonitorFeignService monitorFeignService;

    @Autowired
    private PkFeignService pkFeignService;

    @Autowired
    private UserFeignService userFeignService;

    /**
     * TODO
     * @author LiLan
     * @since 2020/8/21 17:48
     * @param mmsEnrollRemind
     * @return void
     **/
    @Override
    public void addEnrollRemind(MmsEnrollRemind mmsEnrollRemind){
        mmsEnrollRemindMapper.insert(mmsEnrollRemind);

    }

    /**
     * TODO
     * @author LiLan
     * @since 2020/8/21 18:01
     * @param receiveUserId
     * @return java.util.List<cn.fusionfuture.bugu.message.vo.EnrollVO>
     **/
    @Override
    public PageInfo<EnrollVO> getEnrollRemind(Integer pn, Integer ps, Long receiveUserId){
        QueryWrapper<MmsEnrollRemind> queryWrapper = new QueryWrapper<MmsEnrollRemind>();
        queryWrapper.eq("receive_user_id", receiveUserId)
                .eq("is_hidden",false)
                .orderByDesc("create_time");

        PageHelper.startPage(pn, ps);
        PageInfo<MmsEnrollRemind> mmsEnrollRemindList =new PageInfo<>(mmsEnrollRemindMapper.selectList(queryWrapper)) ;

//      更新为已读
        MmsEnrollRemind updateEntity = new MmsEnrollRemind();
        updateEntity.setIsChecked(true);
        mmsEnrollRemindMapper.update(updateEntity,queryWrapper);
        List<EnrollVO> enrollVOList = new ArrayList<>();
        for(MmsEnrollRemind mmsEnrollRemind:mmsEnrollRemindList.getList()){
            EnrollVO enrollVO = new EnrollVO();
            BeanUtils.copyProperties(mmsEnrollRemind,enrollVO);

            HashMap<String,String> sender = userFeignService.getDetailsForMessage(enrollVO.getSendUserId()).getData();
            enrollVO.setSendUserName(sender.get("userName"));
            enrollVO.setSendAvatarUrl(sender.get("avatarUrl"));

            int planType=mmsEnrollRemind.getPlanTypeId();
            Long planId = mmsEnrollRemind.getPlanId();

//            pk计划
            if(planType==PK_PLAN){
                SimplePkPlanVO simplePkPlanVO = pkFeignService.querySimplePkPlanVO(planId).getData();
                enrollVO.setCurrentEnrollCount(simplePkPlanVO.getEnrolledQuantity());
                enrollVO.setMaxEnrollQuantity(simplePkPlanVO.getPkQuantity());
                enrollVO.setPlanPattern(simplePkPlanVO.getPkPattern());
                enrollVO.setPlanName(simplePkPlanVO.getName());
            }else{
                SimpleMonitorPlanVO simpleMonitorPlanVO = monitorFeignService.querySimpleMonitorPlanVO(planId).getData();
//                enrollVO.setCurrentEnrollCount(simpleMonitorPlanVO.getEnrolledQuantity());
                enrollVO.setMaxEnrollQuantity(simpleMonitorPlanVO.getMonitorQuantity());
                enrollVO.setPlanPattern(simpleMonitorPlanVO.getPlanPattern());
                enrollVO.setPlanName(simpleMonitorPlanVO.getName());
            }

            enrollVOList.add(enrollVO);
//          TODO:调用其他微服务获取完整数据

        }
        PageUtil pageUtil = new PageUtil();
        PageInfo<EnrollVO> enrollVOPageInfo = new PageInfo<>(enrollVOList);
//        BeanUtils.copyProperties(mmsEnrollRemindList,enrollVOPageInfo);
        pageUtil.copyAtrr(mmsEnrollRemindList,enrollVOPageInfo);
        return enrollVOPageInfo;
    }
}
