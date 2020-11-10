package cn.fusionfuture.bugu.pk.service.impl;

import cn.fusionfuture.bugu.pk.mapper.PmsPkPlanMapper;
import cn.fusionfuture.bugu.pk.mapper.PmsPkPunchRecordMapper;
import cn.fusionfuture.bugu.pk.mapper.PmsUserAttendPlanMapper;
import cn.fusionfuture.bugu.pk.mapper.PmsUserCreatePlanMapper;
import cn.fusionfuture.bugu.pk.service.IPmsUpdateStateService;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPlan;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPunchRecord;
import cn.fusionfuture.bugu.pojo.entity.PmsUserAttendPlan;
import cn.fusionfuture.bugu.pojo.entity.PmsUserCreatePlan;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zws
 * @version 1.0
 * @class PmsUpdateStateImpl
 * @description TODO
 * @date 2020/11/9 20:25
 */
@Service
public class PmsUpdateStateImpl implements IPmsUpdateStateService {

    @Autowired
    PmsPkPlanMapper pkPlanMapper;

    @Autowired
    PmsUserAttendPlanMapper userAttendPlanMapper;

    @Autowired
    PmsUserCreatePlanMapper userCreatePlanMapper;

    @Autowired
    PmsPkPunchRecordMapper pkPunchRecordMapper;

    @Override
    public void checkPlanIsStart(Long uid) {
        List<PmsPkPlan> pkPlans=new ArrayList<>();
        QueryWrapper<PmsPkPlan> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",uid).eq("plan_status_id",1);
        pkPlans.addAll(pkPlanMapper.selectList(queryWrapper));
        for (PmsPkPlan pkPlan:pkPlans
             ) {
            if(LocalDateTime.now().isAfter(pkPlan.getStartTime())){
                pkPlan.setPlanStatusId(2);
            }
                pkPlanMapper.updateById(pkPlan);
        }
    }

    @Override
    public void checkPlanIsEnd(Long uid) {
        List<PmsPkPlan> pkPlans=new ArrayList<>();
        QueryWrapper<PmsPkPlan> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",uid).eq("plan_status_id",2);
        pkPlans.addAll(pkPlanMapper.selectList(queryWrapper));
        for (PmsPkPlan pkPlan:pkPlans
        ) {
            if(LocalDateTime.now().isAfter(pkPlan.getEndTime())){
                pkPlan.setPlanStatusId(3);
            }
            pkPlanMapper.updateById(pkPlan);
        }
    }

    @Override
    public void judgePlanResult(Long uid) {
        List<PmsUserAttendPlan> userAttendPlans=new ArrayList<>();
        QueryWrapper<PmsUserAttendPlan> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",uid).eq("is_success",2);
        userAttendPlans.addAll(userAttendPlanMapper.selectList(queryWrapper));
        for (PmsUserAttendPlan userAttendPlan:userAttendPlans
        ) {
            int midCount=userAttendPlan.getPunchQuantity()/2;
            if(userAttendPlan.getPunchVictoryCount()>midCount){
                userAttendPlan.setIsSuccess(1);
            }
            else{
                userAttendPlan.setIsSuccess(0);
            }
            userAttendPlanMapper.updateById(userAttendPlan);
        }

        List<PmsUserCreatePlan> userCreatePlans=new ArrayList<>();
        QueryWrapper<PmsUserCreatePlan> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("user_id",uid).eq("is_success",2);
        userCreatePlans.addAll(userCreatePlanMapper.selectList(queryWrapper1));
        for (PmsUserCreatePlan userCreatePlan:userCreatePlans
        ) {
            int midCount=userCreatePlan.getPunchQuantity()/2;
            if(userCreatePlan.getPunchVictoryCount()>midCount){
                userCreatePlan.setIsSuccess(1);
            }
            else{
                userCreatePlan.setIsSuccess(0);
            }
            userCreatePlanMapper.updateById(userCreatePlan);
        }
    }

    @Override
    public void judgePunchResult(Long uid) {
        List<PmsPkPunchRecord> pkPunchRecords=new ArrayList<>();
        QueryWrapper<PmsPkPunchRecord> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("user_id",uid).eq("status_id",2);
        pkPunchRecords.addAll(pkPunchRecordMapper.selectList(queryWrapper1));
        for (PmsPkPunchRecord pkPunchRecord:pkPunchRecords
        ) {
            if(LocalDateTime.now().isAfter(pkPunchRecord.getExpiredTime())){
                if(pkPunchRecord.getAgreeCount()>=pkPunchRecord.getDisagreeCount()){
                    pkPunchRecord.setStatusId(3);
                    
                }
                else{
                    pkPunchRecord.setStatusId(4);
                }
            }
            pkPunchRecordMapper.updateById(pkPunchRecord);
        }

    }
}