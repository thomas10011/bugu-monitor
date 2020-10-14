package cn.fusionfuture.bugu.monitor.service.impl;

import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPunchRecordMapper;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPunchStatusMapper;
import cn.fusionfuture.bugu.monitor.vo.*;
import cn.fusionfuture.bugu.pojo.constants.PunchStatus;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPlan;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPlanMapper;
import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPlanService;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPunchRecord;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
@Service
@Component
public class PmsMonitorPlanServiceImpl extends ServiceImpl<PmsMonitorPlanMapper, PmsMonitorPlan> implements IPmsMonitorPlanService {

    @Autowired
    PmsMonitorPlanMapper monitorPlanMapper;

    @Autowired
    PmsMonitorPunchRecordMapper monitorPunchRecordMapper;

    @Autowired
    PmsMonitorPunchStatusMapper monitorPunchStatusMapper;

    @Override
    public Long createMonitorPlan(NewMonitorPlanVO newMonitorPlanVO) {
        PmsMonitorPlan monitorPlan = new PmsMonitorPlan();

        BeanUtils.copyProperties(newMonitorPlanVO, monitorPlan);
        // 插入前把已打卡次数置为零
        monitorPlanMapper.insert(monitorPlan.setPunchCount(0));
        Integer punchQuantity=monitorPlan.getPunchQuantity();
        for(int i=0;i<punchQuantity;i++){
            PmsMonitorPunchRecord monitorPunchRecord=new PmsMonitorPunchRecord();
            monitorPunchRecord.setAgreeCount(0).setDisagreeCount(0)
                    .setLikeCount(0).setCommentQuantity(0).setMonitorPlanId(monitorPlan.getId())
                    .setUserId(monitorPlan.getUserId()).setStatusId(PunchStatus.NotPunched)
                    .setStartTime(monitorPlan.getStartTime().plusDays(i*monitorPlan.getPunchCycle()));
            if(monitorPlan.getStartTime().plusDays(monitorPlan.getPunchQuantity()*monitorPlan.getPunchCycle()).isAfter(monitorPlan.getEndTime())){
                monitorPunchRecord.setExpiredTime(monitorPlan.getEndTime());
            }
            else{
                monitorPunchRecord.setExpiredTime(monitorPlan.getStartTime().plusDays((i+1)*monitorPlan.getPunchCycle()));
            }
            monitorPunchRecordMapper.insert(monitorPunchRecord);
        }
        return monitorPlan.getId();
    }

    @Override
    public PageInfo<BasicMonitorPlanVO> queryBasicMonitorPlanVO(Integer pn, Integer ps, Long uid) {
        PageHelper.startPage(pn, ps);
        return new PageInfo<>(monitorPlanMapper.queryBasicMonitorPlanVO(uid));
    }

    @Override
    public DetailedMonitorPlanVO queryDetailedMonitorPlanVO(Long pid) {
        return monitorPlanMapper.queryDetailedMonitorPlanVO(pid);
    }

    @Override
    public SimpleMonitorPlanVO querySimpleMonitorPlanVO(Long planId) {
        return monitorPlanMapper.querySimpleMonitorPlanVO(planId);
    }

    @Override
    //@Scheduled(cron = "0 0 0 * * ?")
    public String checkIsPunched(Long planId){
//        PmsMonitorPlan monitorPlan = monitorPlanMapper.selectById(planId);
//        //设置定时任务，在用户未打卡时，提醒用户打卡（在上一次打卡结束当天24：00点修改打卡状态为未打卡）
//        //打卡开始时间
//        LocalDate startDate = monitorPlan.getStartTime().toLocalDate();
//        //打卡结束时间
//        LocalDate endDate = monitorPlan.getEndTime().toLocalDate();
//        //打卡周期
//        Integer punchCycle = monitorPlan.getPunchCycle();
//        //当前所处的打卡周期
//        Integer currentPunchCycle=monitorPlan.getCurrentPunchCycle();
//        //获取当前时间
//        LocalDate currentDate = LocalDate.now();
//        Integer i;
//        for(i=1 ;i<=monitorPlan.getPunchQuantity();i++){
//            //打卡周期开始时间
//            LocalDate punchCycleStartDate = startDate.plusDays(punchCycle*(i-1));
//            //打卡周期结束时间
//            LocalDate punchCycleEndDate;
//            if(startDate.plusDays(monitorPlan.getPunchQuantity()*punchCycle)==endDate||i<=monitorPlan.getPunchQuantity()-1){
//                punchCycleEndDate = punchCycleStartDate.plusDays(punchCycle*i);
//            }
//            else{
//                punchCycleEndDate = endDate;
//            }
//            //当前时间处在一个打卡周期内
//            if (currentDate.isAfter(punchCycleStartDate) && currentDate.isBefore(punchCycleEndDate)) {
//                currentPunchCycle=i;
//            }
//        }
        //获取当前时间
        LocalDateTime currentTime=LocalDateTime.now();
        QueryWrapper<PmsMonitorPunchRecord> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("monitor_plan_id",planId);
        List<PmsMonitorPunchRecord> punchRecords=monitorPunchRecordMapper.selectList(queryWrapper);
        for (PmsMonitorPunchRecord monitorPunchRecord:punchRecords
             ) {
            if(currentTime.isAfter(monitorPunchRecord.getStartTime())&&currentTime.isBefore(monitorPunchRecord.getExpiredTime())){
                return monitorPunchStatusMapper.selectById(monitorPunchRecord.getStatusId()).getDescription();
            }
        }
        return "访问异常";
    }
}
