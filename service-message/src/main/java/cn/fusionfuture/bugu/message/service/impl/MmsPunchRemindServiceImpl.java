package cn.fusionfuture.bugu.message.service.impl;

import cn.fusionfuture.bugu.message.feign.MonitorFeignService;
import cn.fusionfuture.bugu.message.feign.PkFeignService;
import cn.fusionfuture.bugu.message.feign.UserFeignService;
import cn.fusionfuture.bugu.message.util.PageUtil;
import cn.fusionfuture.bugu.message.vo.PunchVO;
import cn.fusionfuture.bugu.message.vo.feignvo.SimpleMonitorPlanVO;
import cn.fusionfuture.bugu.message.vo.feignvo.SimplePkPlanVO;
import cn.fusionfuture.bugu.pojo.entity.MmsPunchRemind;
import cn.fusionfuture.bugu.message.mapper.MmsPunchRemindMapper;
import cn.fusionfuture.bugu.message.service.IMmsPunchRemindService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
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
 * @class MmsPunchRemindServiceImpl
 * @description TODO
 * @date 2020/8/22 12:04
 */
@Service
public class MmsPunchRemindServiceImpl extends ServiceImpl<MmsPunchRemindMapper, MmsPunchRemind> implements IMmsPunchRemindService {
    final Integer MONITOR_PLAN = 1;
    final Integer PK_PLAN = 2;
    @Autowired
    MmsPunchRemindMapper mmsPunchRemindMapper;

    @Autowired
    private MonitorFeignService monitorFeignService;

    @Autowired
    private PkFeignService pkFeignService;

    @Autowired
    private UserFeignService userFeignService;

    @Override
    public void addPunchRemind(MmsPunchRemind mmsPunchRemind) {
        mmsPunchRemindMapper.insert(mmsPunchRemind);

    }

    @Override
    public PageInfo<PunchVO> getPunchRemind(Integer pn, Integer ps, Long id) {
        QueryWrapper<MmsPunchRemind> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("receive_user_id", id)
                .eq("is_hidden",false)
                .orderByDesc("create_time");

        PageHelper.startPage(pn, ps);
        PageInfo<MmsPunchRemind> mmsPunchRemindList = new PageInfo<>(mmsPunchRemindMapper.selectList(queryWrapper));
        System.out.println("查询出数据");

        MmsPunchRemind updateEntity = new MmsPunchRemind();
        updateEntity.setIsChecked(true);
        mmsPunchRemindMapper.update(updateEntity,queryWrapper);

        List<PunchVO> punchVOList = new ArrayList<>();
        for (MmsPunchRemind mmsPunchRemind : mmsPunchRemindList.getList()) {
            PunchVO punchVO = new PunchVO();
            BeanUtils.copyProperties(mmsPunchRemind,punchVO);
            
            HashMap<String,String> sender = userFeignService.getDetailsForMessage(punchVO.getSendUserId()).getData();
            punchVO.setSendUserName(sender.get("userName"));
            punchVO.setSendAvatarUrl(sender.get("avatarUrl"));

            int planType=mmsPunchRemind.getPlanTypeId();
            Long planId = mmsPunchRemind.getPlanId();

//            pk计划
            if(planType==PK_PLAN){
                SimplePkPlanVO simplePkPlanVO = pkFeignService.querySimplePkPlanVO(planId).getData();
                punchVO.setPlanPattern(simplePkPlanVO.getPkPattern());
                punchVO.setPlanName(simplePkPlanVO.getName());
            }else{
                SimpleMonitorPlanVO simpleMonitorPlanVO = monitorFeignService.querySimpleMonitorPlanVO(planId).getData();
                punchVO.setPlanPattern(simpleMonitorPlanVO.getPlanPattern());
                punchVO.setPlanName(simpleMonitorPlanVO.getName());
            }

            punchVOList.add(punchVO);
//          TODO:调用其他微服务获取完整数据

        }
        PageUtil pageUtil = new PageUtil();
        PageInfo<PunchVO> punchVOPageInfo = new PageInfo<>(punchVOList);
        pageUtil.copyAtrr(mmsPunchRemindList,punchVOPageInfo);
//        BeanUtils.copyProperties(mmsPunchRemindList,punchVOPageInfo);
        return punchVOPageInfo;
    }
}
