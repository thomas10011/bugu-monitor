package cn.fusionfuture.bugu.pk.service.impl;

import cn.fusionfuture.bugu.pk.feign.SearchFeignService;
import cn.fusionfuture.bugu.pk.feign.UserFeignService;
import cn.fusionfuture.bugu.pk.mapper.*;
import cn.fusionfuture.bugu.pk.service.IPmsUpdateStateService;
import cn.fusionfuture.bugu.pojo.constants.PkPlanStatus;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPlan;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPunchRecord;
import cn.fusionfuture.bugu.pojo.entity.PmsUserAttendPlan;
import cn.fusionfuture.bugu.pojo.entity.PmsUserCreatePlan;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zws
 * @version 1.0
 * @class PmsUpdateStateImpl
 * @description 用于检测计划状态，打卡结果并更新
 * @date 2020/11/9 20:25
 */
@Service
@Component
@EnableScheduling
public class PmsUpdateStateImpl implements IPmsUpdateStateService {

    @Autowired
    PmsPkPlanMapper pkPlanMapper;

    @Autowired
    PmsUserAttendPlanMapper userAttendPlanMapper;

    @Autowired
    PmsUserCreatePlanMapper userCreatePlanMapper;

    @Autowired
    PmsPkPunchRecordMapper pkPunchRecordMapper;

    @Autowired
    PmsPkUpdateStateMapper pkUpdateStateMapper;

    @Autowired
    SearchFeignService searchFeignService;

    @Autowired
    UserFeignService userFeignService;


    @Override
    @Scheduled(cron="0 1 0 * * ?")
    public void checkPlanIsStart() throws IOException {
        pkUpdateStateMapper.checkPlanIsStart();
    }

    @Override
    @Scheduled(cron="0 1 0 * * ?")
    public void checkPlanIsEnd() throws IOException {
       pkUpdateStateMapper.checkPlanIsEnd();
    }

    @Override
    @Scheduled(cron="0 11 0 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void judgePlanResult() {
        List<PmsUserAttendPlan> userAttendPlans=new ArrayList<>();
        QueryWrapper<PmsUserAttendPlan> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("is_success",2);
        userAttendPlans.addAll(userAttendPlanMapper.selectList(queryWrapper));
        if(userAttendPlans.size()!=0) {
            for (PmsUserAttendPlan userAttendPlan : userAttendPlans
            ) {
                if(pkPlanMapper.selectById(userAttendPlan.getPkPlanId()).getPlanStatusId().equals(PkPlanStatus.COMPLETE.getIndex())) {
                    int midCount = userAttendPlan.getPunchQuantity() / 2;
                    if (userAttendPlan.getPunchVictoryCount() > midCount) {
                        userAttendPlan.setIsSuccess(1);
                        //userFeignService.updateSuccessCount(uid,1);
                        //userFeignService.updatePkSuccessCount(uid,1);
                    } else {
                        userAttendPlan.setIsSuccess(0);
                    }
                    userAttendPlanMapper.updateById(userAttendPlan);
                }
                else{
                    continue;
                }
            }
        }

        List<PmsUserCreatePlan> userCreatePlans=new ArrayList<>();
        QueryWrapper<PmsUserCreatePlan> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("is_success",2);
        userCreatePlans.addAll(userCreatePlanMapper.selectList(queryWrapper1));
        if(userCreatePlans.size()!=0) {
            for (PmsUserCreatePlan userCreatePlan : userCreatePlans
            ) {
                if(pkPlanMapper.selectById(userCreatePlan.getPkPlanId()).getPlanStatusId().equals(PkPlanStatus.COMPLETE.getIndex())) {
                    int midCount = userCreatePlan.getPunchQuantity() / 2;
                    if (userCreatePlan.getPunchVictoryCount() > midCount) {
                        userCreatePlan.setIsSuccess(1);
                        //userFeignService.updateVictoryCount(uid,1);
                    } else {
                        userCreatePlan.setIsSuccess(0);
                    }
                    userCreatePlanMapper.updateById(userCreatePlan);
                }
                else{
                    continue;
                }

            }
        }
    }
//
//    @Override
//    public void checkPunchIsEnd(Long uid) {
//        List<PmsPkPunchRecord> pkPunchRecords=new ArrayList<>();
//        QueryWrapper<PmsPkPunchRecord> queryWrapper1=new QueryWrapper<>();
//        queryWrapper1.eq("user_id",uid).eq("status_id",1);
//        pkPunchRecords.addAll(pkPunchRecordMapper.selectList(queryWrapper1));
//        for (PmsPkPunchRecord pkPunchRecord:pkPunchRecords
//        ) {
//            if(LocalDateTime.now().isAfter(pkPunchRecord.getExpiredTime())){
//               pkPunchRecord.setStatusId(4)
//            }
//            pkPunchRecordMapper.updateById(pkPunchRecord);
//        }
//    }

    @Override
    @Scheduled(cron="0 21 0 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void judgePunchResult() {
        List<PmsPkPunchRecord> pkPunchRecords=new ArrayList<>();
        QueryWrapper<PmsPkPunchRecord> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("status_id",2);
        pkPunchRecords.addAll(pkPunchRecordMapper.selectList(queryWrapper1));
        for (PmsPkPunchRecord pkPunchRecord:pkPunchRecords
        ) {
            if(LocalDateTime.now().isAfter(pkPunchRecord.getExpiredTime())){
                if(pkPunchRecord.getAgreeCount()>=pkPunchRecord.getDisagreeCount()){
                    pkPunchRecord.setStatusId(3);
                    QueryWrapper<PmsUserAttendPlan> queryWrapper2=new QueryWrapper<>();
                    queryWrapper2.eq("pk_plan_id",pkPunchRecord.getPkPlanId());
                    if(userAttendPlanMapper.selectOne(queryWrapper2)!=null){
                        PmsUserAttendPlan userAttendPlan=userAttendPlanMapper.selectOne(queryWrapper2);
                        userAttendPlan.setPunchVictoryCount(userAttendPlan.getPunchVictoryCount()+1);
                        userAttendPlanMapper.updateById(userAttendPlan);
                        //userFeignService.updatePkSuccessCount(uid,1);
                    }
                    else{
                        QueryWrapper<PmsUserCreatePlan> queryWrapper3=new QueryWrapper<>();
                        queryWrapper3.eq("pk_plan_id",pkPunchRecord.getPkPlanId());
                        PmsUserCreatePlan userCreatePlan=userCreatePlanMapper.selectOne(queryWrapper3);
                        userCreatePlan.setPunchVictoryCount(userCreatePlan.getPunchVictoryCount()+1);
                        userCreatePlanMapper.updateById(userCreatePlan);
                        //userFeignService.updatePkSuccessCount(uid,1);
                    }
                }
                else{
                    pkPunchRecord.setStatusId(4);
                }
            }
            pkPunchRecordMapper.updateById(pkPunchRecord);
        }

    }
}