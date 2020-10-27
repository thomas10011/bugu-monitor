package cn.fusionfuture.bugu.monitor.service.impl;

import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPunchRecordMapper;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPunchStatusMapper;
import cn.fusionfuture.bugu.monitor.mapper.PmsUserMonitorPlanMapper;
import cn.fusionfuture.bugu.monitor.vo.plan.BasicMonitorPlanVO;
import cn.fusionfuture.bugu.monitor.vo.plan.DetailedMonitorPlanVO;
import cn.fusionfuture.bugu.monitor.vo.plan.NewMonitorPlanVO;
import cn.fusionfuture.bugu.monitor.vo.plan.SimpleMonitorPlanVO;
import cn.fusionfuture.bugu.pojo.constants.PunchStatus;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPlan;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPlanMapper;
import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPlanService;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPunchRecord;
import cn.fusionfuture.bugu.pojo.entity.PmsUserMonitorPlan;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Autowired
    PmsUserMonitorPlanMapper userMonitorPlanMapper;

    @Override
    public Long createMonitorPlan(NewMonitorPlanVO newMonitorPlanVO) {
        PmsMonitorPlan monitorPlan = new PmsMonitorPlan();
        PmsUserMonitorPlan userMonitorPlan=new PmsUserMonitorPlan();


        BeanUtils.copyProperties(newMonitorPlanVO, monitorPlan);
        // 插入前把已打卡次数置为零
        monitorPlanMapper.insert(monitorPlan.setPunchCount(0));
        Integer punchQuantity=monitorPlan.getPunchQuantity();
        for(int i=0;i<punchQuantity;i++){
            PmsMonitorPunchRecord monitorPunchRecord=new PmsMonitorPunchRecord();
            monitorPunchRecord.setAgreeCount(0).setDisagreeCount(0).setCurrentPunchCycle(i+1)
                    .setLikeCount(0).setCommentQuantity(0).setMonitorPlanId(monitorPlan.getId())
                    .setUserId(monitorPlan.getUserId()).setStatusId(PunchStatus.NotPunched)
                    .setStartTime(monitorPlan.getStartTime().plusDays(i*monitorPlan.getPunchCycle()));
            if(monitorPlan.getStartTime().plusDays((i+1)*monitorPlan.getPunchCycle()).isAfter(monitorPlan.getEndTime())){
                monitorPunchRecord.setExpiredTime(monitorPlan.getEndTime());
            }
            else{
                monitorPunchRecord.setExpiredTime(monitorPlan.getStartTime().plusDays((i+1)*monitorPlan.getPunchCycle()));
            }
            monitorPunchRecordMapper.insert(monitorPunchRecord);
        }
        userMonitorPlan.setMonitorPlanId(monitorPlan.getId()).setUserId(monitorPlan.getUserId())
                .setPunchVictoryCount(0);
        userMonitorPlanMapper.insert(userMonitorPlan);
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
    public String checkIsPunched(Long planId){

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
