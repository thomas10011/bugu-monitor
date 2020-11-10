package cn.fusionfuture.bugu.pk.service.impl;

import cn.fusionfuture.bugu.pk.feign.SearchFeignService;
import cn.fusionfuture.bugu.pk.feign.UserFeignService;
import cn.fusionfuture.bugu.pk.mapper.PmsPkPlanMapper;
import cn.fusionfuture.bugu.pk.mapper.PmsPkPunchRecordMapper;
import cn.fusionfuture.bugu.pk.vo.plan.BasicPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.plan.MyAchievementPlanVO;
import cn.fusionfuture.bugu.pojo.constants.PkPlanStatus;
import cn.fusionfuture.bugu.pojo.constants.PunchStatus;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPlan;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPunchRecord;
import cn.fusionfuture.bugu.pojo.entity.PmsPkUserGrabTicket;
import cn.fusionfuture.bugu.pojo.entity.PmsUserAttendPlan;
import cn.fusionfuture.bugu.pk.mapper.PmsUserAttendPlanMapper;
import cn.fusionfuture.bugu.pk.service.IPmsUserAttendPlanService;
import cn.hutool.core.lang.Console;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
@Service
public class PmsUserAttendPlanServiceImpl extends ServiceImpl<PmsUserAttendPlanMapper, PmsUserAttendPlan> implements IPmsUserAttendPlanService {

    @Autowired
    PmsUserAttendPlanMapper userAttendPlanMapper;

    @Autowired
    PmsPkPlanMapper pkPlanMapper;

    @Autowired
    PmsPkPunchRecordMapper pkPunchRecordMapper;

    @Autowired
    SearchFeignService searchFeignService;

    @Override
    public PageInfo<MyAchievementPlanVO> queryPkUserAttendPlanByUserId(Integer pn, Integer ps, Long uid){
        PageHelper.startPage(pn,ps);
        return new PageInfo<>(userAttendPlanMapper.queryPkUserAttendPlanByUserId(uid));
    }

    @Override
    public String userAttendPlan(Long userId,Long planId) throws IOException {

        //判断用户是否为计划创建者，创建者不能报名该计划
        PmsPkPlan pkPlan=pkPlanMapper.selectById(planId);
        if(pkPlan.getUserId().equals(userId)){
            return "不能报名自己创建的计划";
        }
        //用户不是计划创建者
        else {
            Integer planStatusId = pkPlan.getPlanStatusId();
            //检查PK计划是否处在报名中或进行中
            //计划在报名中或进行中
            if (planStatusId.equals(PkPlanStatus.REGISTERING.getIndex()) || planStatusId.equals(PkPlanStatus.GRABBING.getIndex())) {
                //检查用户是否已经报名
                QueryWrapper<PmsUserAttendPlan> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("user_id", userId).eq("pk_plan_id", planId);
                //用户已经报名
                if (userAttendPlanMapper.selectOne(queryWrapper) != null) {
                    return "不能重复对计划进行报名";
                }
                //用户未报名
                else {
                    //插入一条用户参与计划的记录
                    PmsUserAttendPlan userAttendPlanRecord=new PmsUserAttendPlan();
                    userAttendPlanRecord.setUserId(userId).setPkPlanId(planId).setPunchCount(0).setPunchVictoryCount(0);
                    //如果已报名人数加1不超过pk人数，将计划的已报名人数加1，并保存至用户参加pk计划记录
                    if(pkPlan.getEnrolledQuantity()+1 <= pkPlan.getPkQuantity()){
                        pkPlan.setEnrolledQuantity(pkPlan.getEnrolledQuantity()+1);
                        pkPlanMapper.updateById(pkPlan);
                        userAttendPlanMapper.insert(userAttendPlanRecord);

                        //创建该用户的打卡记录
                        Integer punchQuantity=pkPlan.getPunchQuantity();
                        for(int i=0;i<punchQuantity;i++){
                            PmsPkPunchRecord pkPunchRecord=new PmsPkPunchRecord();
                            pkPunchRecord.setAgreeCount(0).setDisagreeCount(0)
                                    .setLikeCount(0).setCommentQuantity(0).setPkPlanId(pkPlan.getId()).setCurrentPunchCycle(i+1)
                                    .setUserId(userId).setStatusId(PunchStatus.NotPunched)
                                    .setStartTime(pkPlan.getStartTime().plusDays(i*pkPlan.getPunchCycle()));
                            if(pkPlan.getStartTime().plusDays((i+1)*pkPlan.getPunchCycle()).isAfter(pkPlan.getEndTime())){
                                pkPunchRecord.setExpiredTime(pkPlan.getEndTime());
                            }
                            else{
                                pkPunchRecord.setExpiredTime(pkPlan.getStartTime().plusDays((i+1)*pkPlan.getPunchCycle()));
                            }
                            pkPunchRecordMapper.insert(pkPunchRecord);
                        }
                        return "报名成功";
                    }
                    //报名人数已满
                    else{
                        return "计划报名人数已满";
                    }
                }
            }
            //计划报名已截至
            else{
                return "计划已停止报名";
            }
        }
    }
}
