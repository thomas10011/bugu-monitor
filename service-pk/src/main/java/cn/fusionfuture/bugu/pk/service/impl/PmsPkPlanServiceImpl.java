package cn.fusionfuture.bugu.pk.service.impl;

import ch.qos.logback.classic.jmx.MBeanUtil;
import cn.fusionfuture.bugu.pk.mapper.*;
import cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.DetailedPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.NewPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.SimplePkPlanVO;
import cn.fusionfuture.bugu.pojo.constants.PunchStatus;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPunchRecord;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPlan;
import cn.fusionfuture.bugu.pk.service.IPmsPkPlanService;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPunchRecord;
import cn.fusionfuture.bugu.pojo.entity.PmsUserCreatePlan;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PmsPkPlanServiceImpl extends ServiceImpl<PmsPkPlanMapper, PmsPkPlan> implements IPmsPkPlanService {

    @Autowired
    PmsPkPlanMapper pkPlanMapper;

    @Autowired
    PmsUserCreatePlanMapper userCreatePlanMapper;

    @Autowired
    PmsPkPunchRecordMapper pkPunchRecordMapper;

    @Autowired
    PmsPkPunchStatusMapper pkPunchStatusMapper;

    @Autowired
    PmsUserAttendPlanMapper userAttendPlanMapper;

    @Override
    public Long createPkPlan(NewPkPlanVO newPkPlanVO) {
        PmsPkPlan pkPlan = new PmsPkPlan();
        PmsUserCreatePlan pmsUserCreatePlan=new PmsUserCreatePlan();
        BeanUtils.copyProperties(newPkPlanVO, pkPlan);
        //设置计划的已报名人数
        pkPlan.setEnrolledQuantity(1);
        //设置计划的状态为报名中（报名中，进行中，已完成）
        pkPlan.setPlanStatusId(1);
        pkPlan.setTotalBonus(newPkPlanVO.getTotalBonus());
        pkPlanMapper.insert(pkPlan);
        Integer punchQuantity=pkPlan.getPunchQuantity();
        for(int i=0;i<punchQuantity;i++){
            PmsPkPunchRecord pkPunchRecord=new PmsPkPunchRecord();
            pkPunchRecord.setAgreeCount(0).setDisagreeCount(0)
                    .setLikeCount(0).setCommentQuantity(0).setPkPlanId(pkPlan.getId())
                    .setUserId(pkPlan.getUserId()).setStatusId(PunchStatus.NotPunched)
                    .setStartTime(pkPlan.getStartTime().plusDays(i*pkPlan.getPunchCycle()));
            if(pkPlan.getStartTime().plusDays((i+1)*pkPlan.getPunchCycle()).isAfter(pkPlan.getEndTime())){
                pkPunchRecord.setExpiredTime(pkPlan.getEndTime());
            }
            else{
                pkPunchRecord.setExpiredTime(pkPlan.getStartTime().plusDays((i+1)*pkPlan.getPunchCycle()));
            }
            pkPunchRecordMapper.insert(pkPunchRecord);
        }

        pmsUserCreatePlan.setUserId(pkPlan.getUserId()).setPunchCount(0).setPkPlanId(pkPlan.getId());
        userCreatePlanMapper.insert(pmsUserCreatePlan);
        return pkPlan.getId();
    }

    @Override
    public PageInfo<BasicPkPlanVO> queryBasicPkPlanVO(Integer pn, Integer ps, Long uid) {
        PageHelper.startPage(pn,ps);
        return new PageInfo<>(pkPlanMapper.queryBasicPkPlanVO(uid));
    }

    @Override
    public SimplePkPlanVO querySimplePkPlanVO(Long planId) {
        return pkPlanMapper.querySimplePkPlanVO(planId);
    }

    @Override
    //@Scheduled(cron = "0 0 0 * * ?")
    public String checkIsPunched(Long userId,Long planId){

        //获取当前时间
        LocalDateTime currentTime=LocalDateTime.now();
        QueryWrapper<PmsPkPunchRecord> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("pk_plan_id",planId ).eq("user_id",userId);
        List<PmsPkPunchRecord> punchRecords=pkPunchRecordMapper.selectList(queryWrapper);
        for (PmsPkPunchRecord pkPunchRecord:punchRecords
        ) {
            if(currentTime.isAfter(pkPunchRecord.getStartTime())&&currentTime.isBefore(pkPunchRecord.getExpiredTime())){
                return pkPunchStatusMapper.selectById(pkPunchRecord.getStatusId()).getDescription();
            }
        }
        return "访问异常";
    }

    @Override
    public DetailedPkPlanVO queryDetailedPkPlanVO(Long uid, Long pid) {

        if(userAttendPlanMapper.queryByUserIdAndPlanId(uid,pid) != null){
            return userAttendPlanMapper.queryDetailedPkPlanVO(uid,pid);
        }
        else if(userCreatePlanMapper.queryByUserIdAndPlanId(uid,pid) != null){
            return userCreatePlanMapper.queryDetailedPkPlanVO(uid,pid);
        }
        return null;
    }
}
