package cn.fusionfuture.bugu.monitor.service.impl;

import cn.fusionfuture.bugu.monitor.feign.UserFeignService;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPlanMapper;
import cn.fusionfuture.bugu.monitor.vo.plan.BasicMonitorPlanVO;
import cn.fusionfuture.bugu.pojo.constants.MonitorPlanStatus;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPlan;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorUserGrabTicket;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorUserGrabTicketMapper;
import cn.fusionfuture.bugu.monitor.service.IPmsMonitorUserGrabTicketService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author thomas
 * @since 2020-09-11
 */
@Service
public class PmsMonitorUserGrabTicketServiceImpl extends ServiceImpl<PmsMonitorUserGrabTicketMapper, PmsMonitorUserGrabTicket> implements IPmsMonitorUserGrabTicketService {

    @Autowired
    PmsMonitorUserGrabTicketMapper monitorUserGrabTicketMapper;

    @Autowired
    PmsMonitorPlanMapper monitorPlanMapper;

    @Autowired
    UserFeignService userFeignService;

    @Override
    public String userGrabTicket(Long userId,Long planId){

        PmsMonitorPlan monitorPlan=monitorPlanMapper.selectById(planId);
        Integer planStatusId=monitorPlan.getPlanStatusId();
        //检查监督计划是否处在报名中或进行中
        if(planStatusId.equals(MonitorPlanStatus.REGISTERING.getIndex()) ||planStatusId.equals(MonitorPlanStatus.UNDERWAY.getIndex())) {
            //检查用户是否已经抢票
            QueryWrapper<PmsMonitorUserGrabTicket> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userId).eq("monitor_plan_id", planId);
            if (monitorUserGrabTicketMapper.selectOne(queryWrapper) != null) {
                return "不能重复对计划抢票";
            } else {
                //插入一条用户抢票获得监督机会的记录
                PmsMonitorUserGrabTicket userMonitorPlanRecord = new PmsMonitorUserGrabTicket();
                userMonitorPlanRecord.setUserId(userId).setMonitorPlanId(planId);
                monitorUserGrabTicketMapper.insert(userMonitorPlanRecord);
                userFeignService.updatePlanCount(userId,1);
                return "抢票成功";
            }
        }
        else{
            return "计划已停止抢票";
        }
    }
    @Override
    public PageInfo<BasicMonitorPlanVO> queryUserVotePlanByUserId(Integer pn, Integer ps, Long uid){

        PageHelper.startPage(pn,ps);
        return new PageInfo<>(monitorUserGrabTicketMapper.queryUserVotePlanByUserId(uid));
    }
}
