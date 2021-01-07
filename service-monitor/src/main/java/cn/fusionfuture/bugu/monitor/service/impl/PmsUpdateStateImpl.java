package cn.fusionfuture.bugu.monitor.service.impl;

import cn.fusionfuture.bugu.monitor.feign.SearchFeignService;
import cn.fusionfuture.bugu.monitor.feign.UserFeignService;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPlanMapper;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPunchRecordMapper;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorUpdateStateMapper;
import cn.fusionfuture.bugu.monitor.mapper.PmsUserMonitorPlanMapper;
import cn.fusionfuture.bugu.monitor.service.IPmsUpdateStateService;
import cn.fusionfuture.bugu.pojo.constants.MonitorPlanStatus;
import cn.fusionfuture.bugu.pojo.entity.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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
@Component
@EnableScheduling
public class PmsUpdateStateImpl implements IPmsUpdateStateService {

    @Autowired
    PmsMonitorPlanMapper monitorPlanMapper;

    @Autowired
    PmsUserMonitorPlanMapper userMonitorPlanMapper;

    @Autowired
    PmsMonitorPunchRecordMapper monitorPunchRecordMapper;

    @Autowired
    PmsMonitorUpdateStateMapper monitorUpdateStateMapper;

    @Autowired
    SearchFeignService searchFeignService;

    @Autowired
    UserFeignService userFeignService;


    @Override
    @Scheduled(cron="0 1 0 * * ?")
    public void checkPlanIsStart() throws IOException {
        monitorUpdateStateMapper.checkPlanIsStart();
    }

    @Scheduled(cron="0 11 0 * * ?")
    @Override
    public void checkPlanIsEnd() throws IOException {
        monitorUpdateStateMapper.checkPlanIsEnd();
    }

    @Scheduled(cron="0 21 0 * * ?")
    @Override
    public void judgePlanResult() {
        List<PmsUserMonitorPlan> userMonitorPlans=new ArrayList<>();
        QueryWrapper<PmsUserMonitorPlan> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("is_success",2);
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

    @Scheduled(cron="0 31 0 * * ?")
    @Override
    public void judgePunchResult() {
        List<PmsMonitorPunchRecord> monitorPunchRecords=new ArrayList<>();
        QueryWrapper<PmsMonitorPunchRecord> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("status_id",2);
        monitorPunchRecords.addAll(monitorPunchRecordMapper.selectList(queryWrapper1));
        for (PmsMonitorPunchRecord monitorPunchRecord:monitorPunchRecords
        ) {
            if(LocalDateTime.now().isAfter(monitorPunchRecord.getExpiredTime())){
                if(monitorPunchRecord.getAgreeCount()>=monitorPunchRecord.getDisagreeCount()){
                    monitorPunchRecord.setStatusId(3);
                    QueryWrapper<PmsUserMonitorPlan> queryWrapper2=new QueryWrapper<>();
                    queryWrapper2.eq("monitor_plan_id",monitorPunchRecord.getMonitorPlanId());
                    if(userMonitorPlanMapper.selectOne(queryWrapper2)!=null){
                        PmsUserMonitorPlan userMonitorPlan=userMonitorPlanMapper.selectOne(queryWrapper2);
                        userMonitorPlan.setPunchVictoryCount(userMonitorPlan.getPunchVictoryCount()+1);
                        userMonitorPlanMapper.updateById(userMonitorPlan);
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