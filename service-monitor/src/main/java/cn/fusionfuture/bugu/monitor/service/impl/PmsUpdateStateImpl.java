package cn.fusionfuture.bugu.monitor.service.impl;

import cn.fusionfuture.bugu.monitor.feign.SearchFeignService;
import cn.fusionfuture.bugu.monitor.feign.UserFeignService;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPlanMapper;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPunchRecordMapper;
import cn.fusionfuture.bugu.monitor.mapper.PmsUserMonitorPlanMapper;
import cn.fusionfuture.bugu.monitor.service.IPmsUpdateStateService;
import cn.fusionfuture.bugu.pojo.constants.MonitorPlanStatus;
import cn.fusionfuture.bugu.pojo.entity.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zws
 * @version 1.0
 * @class PmsUpdateStateImpl
 * @description 用于检测计划状态，打卡结果并更新
 * @date 2020/11/12 11:33
 */
@Service
public class PmsUpdateStateImpl implements IPmsUpdateStateService {

    @Autowired
    PmsMonitorPlanMapper monitorPlanMapper;

    @Autowired
    PmsUserMonitorPlanMapper userMonitorPlanMapper;

    @Autowired
    PmsMonitorPunchRecordMapper monitorPunchRecordMapper;

    @Autowired
    SearchFeignService searchFeignService;

    @Autowired
    UserFeignService userFeignService;


    @Override
    public void checkPlanIsStart(Long uid) throws IOException {
        List<PmsMonitorPlan> monitorPlans=new ArrayList<>();
        QueryWrapper<PmsMonitorPlan> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",uid).eq("plan_status_id",MonitorPlanStatus.REGISTERING.getIndex());
        if(monitorPlanMapper.selectList(queryWrapper).size()!=0) {
            monitorPlans.addAll(monitorPlanMapper.selectList(queryWrapper));
            for (PmsMonitorPlan monitorPlan : monitorPlans
            ) {
                if (LocalDateTime.now().isAfter(monitorPlan.getStartTime())) {
                    monitorPlan.setPlanStatusId(MonitorPlanStatus.UNDERWAY.getIndex());
                    searchFeignService.updatePlanStatus(monitorPlan.getId(), MonitorPlanStatus.UNDERWAY.getValue());
                }
                monitorPlanMapper.updateById(monitorPlan);
            }
        }
    }

    @Override
    public void checkPlanIsEnd(Long uid) throws IOException {
        List<PmsMonitorPlan> monitorPlans=new ArrayList<>();
        QueryWrapper<PmsMonitorPlan> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",uid).eq("plan_status_id",MonitorPlanStatus.UNDERWAY.getIndex());
        if(monitorPlanMapper.selectList(queryWrapper).size()!=0) {
            monitorPlans.addAll(monitorPlanMapper.selectList(queryWrapper));
            for (PmsMonitorPlan monitorPlan : monitorPlans
            ) {
                if (LocalDateTime.now().isAfter(monitorPlan.getEndTime())) {
                    monitorPlan.setPlanStatusId(MonitorPlanStatus.COMPLETE.getIndex());
                    searchFeignService.updatePlanStatus(monitorPlan.getId(), MonitorPlanStatus.COMPLETE.getValue());
                }
                monitorPlanMapper.updateById(monitorPlan);
            }
        }
    }

    @Override
    public void judgePlanResult(Long uid) {
        List<PmsUserMonitorPlan> userMonitorPlans=new ArrayList<>();
        QueryWrapper<PmsUserMonitorPlan> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",uid).eq("is_success",2);
        userMonitorPlans.addAll(userMonitorPlanMapper.selectList(queryWrapper));
        if(userMonitorPlans.size()!=0) {
            for (PmsUserMonitorPlan userMonitorPlan : userMonitorPlans) {
                PmsMonitorPlan monitorPlan=monitorPlanMapper.selectById(userMonitorPlan.getMonitorPlanId());
                if(monitorPlan.getPlanStatusId().equals(MonitorPlanStatus.COMPLETE.getIndex())) {
                    int midCount = monitorPlan.getPunchQuantity() / 2;
                    if (userMonitorPlan.getPunchVictoryCount() > midCount) {
                        userMonitorPlan.setIsSuccess(1);
                        //userFeignService.updateSuccessCount(uid,1);

                    } else {
                        userMonitorPlan.setIsSuccess(0);
                    }
                    userMonitorPlanMapper.updateById(userMonitorPlan);
                }
                else{
                    continue;
                }
            }
        }
    }

    @Override
    public void judgePunchResult(Long uid) {
        List<PmsMonitorPunchRecord> monitorPunchRecords=new ArrayList<>();
        QueryWrapper<PmsMonitorPunchRecord> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("user_id",uid).eq("status_id",2);
        monitorPunchRecords.addAll(monitorPunchRecordMapper.selectList(queryWrapper1));
        for (PmsMonitorPunchRecord monitorPunchRecord:monitorPunchRecords
        ) {
            if(LocalDateTime.now().isAfter(monitorPunchRecord.getExpiredTime())){
                if(monitorPunchRecord.getAgreeCount()>=monitorPunchRecord.getDisagreeCount()){
                    monitorPunchRecord.setStatusId(3);
                    QueryWrapper<PmsUserMonitorPlan> queryWrapper2=new QueryWrapper<>();
                    queryWrapper2.eq("user_id",uid).eq("monitor_plan_id",monitorPunchRecord.getMonitorPlanId());
                    if(userMonitorPlanMapper.selectOne(queryWrapper2)!=null){
                        PmsUserMonitorPlan userMonitorPlan=userMonitorPlanMapper.selectOne(queryWrapper2);
                        userMonitorPlan.setPunchVictoryCount(userMonitorPlan.getPunchVictoryCount()+1);
                        userMonitorPlanMapper.updateById(userMonitorPlan);
                        userFeignService.updateMonitorSuccessCount(uid,1);
                    }
                }
                else{
                    monitorPunchRecord.setStatusId(4);
                }
            }
            monitorPunchRecordMapper.updateById(monitorPunchRecord);
        }
    }
}