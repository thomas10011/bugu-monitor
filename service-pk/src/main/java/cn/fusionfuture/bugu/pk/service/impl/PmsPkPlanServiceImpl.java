package cn.fusionfuture.bugu.pk.service.impl;

import cn.fusionfuture.bugu.pk.dto.PlanForMessageDTO;
import cn.fusionfuture.bugu.pk.dto.PopularPlanDTO;
import cn.fusionfuture.bugu.pk.feign.SearchFeignService;
import cn.fusionfuture.bugu.pk.mapper.*;
import cn.fusionfuture.bugu.pk.vo.plan.*;
import cn.fusionfuture.bugu.pojo.constants.PkPlanStatus;
import cn.fusionfuture.bugu.pojo.constants.PkPlanType;
import cn.fusionfuture.bugu.pojo.constants.PunchStatus;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPlan;
import cn.fusionfuture.bugu.pk.service.IPmsPkPlanService;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPunchRecord;
import cn.fusionfuture.bugu.pojo.entity.PmsUserAttendPlan;
import cn.fusionfuture.bugu.pojo.entity.PmsUserCreatePlan;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    SearchFeignService searchFeignService;

    @Override
    public Long createPkPlan(NewPkPlanVO newPkPlanVO) {

        PmsPkPlan pkPlan = new PmsPkPlan();
        PmsUserCreatePlan pmsUserCreatePlan=new PmsUserCreatePlan();
        BeanUtils.copyProperties(newPkPlanVO, pkPlan);
        //设置计划的已报名人数,设置计划的状态为报名中（报名中，进行中，已完成）
        pkPlan.setEnrolledQuantity(1).setPlanStatusId(PkPlanStatus.REGISTERING.getIndex())
                .setTotalBonus(newPkPlanVO.getTotalBonus()).setLikeCount(0);
        pkPlanMapper.insert(pkPlan);
        Integer punchQuantity=pkPlan.getPunchQuantity();
        for(int i=0;i<punchQuantity;i++){
            PmsPkPunchRecord pkPunchRecord=new PmsPkPunchRecord();
            pkPunchRecord.setAgreeCount(0).setDisagreeCount(0)
                    .setLikeCount(0).setCommentQuantity(0).setPkPlanId(pkPlan.getId()).setCurrentPunchCycle(i+1)
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
        pmsUserCreatePlan.setUserId(pkPlan.getUserId()).setPunchCount(0).setPunchVictoryCount(0).setPkPlanId(pkPlan.getId());
        userCreatePlanMapper.insert(pmsUserCreatePlan);
        PopularPlanDTO popularPlanDTO = new PopularPlanDTO();
        popularPlanDTO
                .setId(pkPlan.getId())
                .setUid(newPkPlanVO.getUserId())
                .setTt(newPkPlanVO.getName())
                .setTp(PkPlanType.getValue(newPkPlanVO.getPkPatternId()))
                .setCv(newPkPlanVO.getImageUrl())
                .setHc(pkPlan.getEnrolledQuantity())
                .setRt(0)
                .setSt(PkPlanStatus.REGISTERING.getValue())
                .setAw(newPkPlanVO.getTotalBonus());
        searchFeignService.createPopularPlan(popularPlanDTO);
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

    @Override
    public PkPlanVO queryPkPlanVO(Long pid) {
        if(pkPlanMapper.queryPkPlanVO(pid) != null){
            PkPlanVO pkPlan= pkPlanMapper.queryPkPlanVO(pid);
            List<Long> userIdList= new ArrayList<>();

            userIdList.add(pkPlanMapper.selectById(pid).getUserId());
            QueryWrapper<PmsUserAttendPlan> userAttendPlanQueryWrapper=new QueryWrapper<>();
            userAttendPlanQueryWrapper.eq("pk_plan_id",pid);
            List<PmsUserAttendPlan> userAttendPlans=userAttendPlanMapper.selectList(userAttendPlanQueryWrapper);
            for (PmsUserAttendPlan userAttendPlan:userAttendPlans
                 ) {
                userIdList.add(userAttendPlan.getUserId());
            }

            pkPlan.setUserIdList(userIdList);
            return pkPlan;
        }
        return null;
    }

    @Override
    public void like(Long planId) {
        //点赞操作，将计划的点赞数+1
        PmsPkPlan pkPlan=pkPlanMapper.selectById(planId);
        pkPlanMapper.updateById(pkPlan.setLikeCount(pkPlan.getLikeCount()+1));
    }

    @Override
    public void cancelLike(Long planId) {
        //取消，将计划的点赞数-1
        PmsPkPlan pkPlan=pkPlanMapper.selectById(planId);
        pkPlanMapper.updateById(pkPlan.setLikeCount(pkPlan.getLikeCount()-1));
    }

    @Override
    public PlanForMessageDTO getPlanForMessageDTO(Long planId) {
        return pkPlanMapper.queryPlanForMessageDTO(planId);
    }
}
