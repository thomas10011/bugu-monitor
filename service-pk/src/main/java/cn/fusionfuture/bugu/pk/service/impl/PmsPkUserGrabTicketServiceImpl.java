package cn.fusionfuture.bugu.pk.service.impl;

import cn.fusionfuture.bugu.pk.feign.UserFeignService;
import cn.fusionfuture.bugu.pk.mapper.PmsPkPlanMapper;
import cn.fusionfuture.bugu.pk.mapper.PmsUserAttendPlanMapper;
import cn.fusionfuture.bugu.pk.mapper.PmsUserCreatePlanMapper;
import cn.fusionfuture.bugu.pk.vo.plan.BasicPkPlanVO;
import cn.fusionfuture.bugu.pojo.constants.GrabTicketsJudge;
import cn.fusionfuture.bugu.pojo.constants.PkPlanStatus;
import cn.fusionfuture.bugu.pojo.entity.*;
import cn.fusionfuture.bugu.pk.mapper.PmsPkUserGrabTicketMapper;
import cn.fusionfuture.bugu.pk.service.IPmsPkUserGrabTicketService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class PmsPkUserGrabTicketServiceImpl extends ServiceImpl<PmsPkUserGrabTicketMapper, PmsPkUserGrabTicket> implements IPmsPkUserGrabTicketService {

    @Autowired
    PmsPkUserGrabTicketMapper pkUserGrabTicketMapper;

    @Autowired
    PmsPkPlanMapper pkPlanMapper;

    @Autowired
    PmsUserAttendPlanMapper userAttendPlanMapper;

    @Autowired
    PmsUserCreatePlanMapper userCreatePlanMapper;

    @Autowired
    UserFeignService userFeignService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer userGrabTicket(Long userId,Long planId){

        //判断是否为自己创建的计划，用户不能报名自己创建的计划
        PmsPkPlan pkPlan=pkPlanMapper.selectById(planId);
        if(pkPlan.getUserId().equals(userId)){
            return GrabTicketsJudge.CAN_NOT_GRAB_TICKET_FOR_YOURS_PLAN;
        }
        else {
            Integer planStatusId = pkPlan.getPlanStatusId();
            //检查PK计划是否处在报名中或进行中
            if (planStatusId.equals(PkPlanStatus.REGISTERING.getIndex()) || planStatusId.equals(PkPlanStatus.GRABBING.getIndex())) {
                //检查用户是否已经抢票
                QueryWrapper<PmsPkUserGrabTicket> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("user_id", userId).eq("pk_plan_id", planId);
                if (pkUserGrabTicketMapper.selectOne(queryWrapper) != null) {
                    return GrabTicketsJudge.CAN_NOT_REPEAT;
                } else {
                    //插入一条用户抢票获得监督机会的记录
                    PmsPkUserGrabTicket userPkPlanRecord = new PmsPkUserGrabTicket();
                    userPkPlanRecord.setUserId(userId).setPkPlanId(planId);
                    pkUserGrabTicketMapper.insert(userPkPlanRecord);
                    userFeignService.updatePlanCount(userId,1);
                    return GrabTicketsJudge.SUCCESS;
                }
            } else {
                return GrabTicketsJudge.GRAB_TICKET_IS_END;
            }
        }
    }

    @Override
    public PageInfo<BasicPkPlanVO> queryUserVotePlanByUserId(Integer pn, Integer ps, Long uid){

        PageHelper.startPage(pn,ps);
        List<BasicPkPlanVO> basicPkPlanVOList=pkUserGrabTicketMapper.queryUserVotePlanByUserId(uid);
//        for (BasicPkPlanVO basicPkPlanVO:basicPkPlanVOList
//             ) {
//            QueryWrapper<PmsUserAttendPlan> queryWrapper1=new QueryWrapper();
//            queryWrapper1.eq("user_id",uid).eq("pk_plan_id", basicPkPlanVO.getId());
//            if(userAttendPlanMapper.selectOne(queryWrapper1)!=null){
//                basicPkPlanVO.setPunchVictoryCount(userAttendPlanMapper.selectOne(queryWrapper1).getPunchVictoryCount());
//            }
//            QueryWrapper<PmsUserCreatePlan> queryWrapper2=new QueryWrapper();
//            queryWrapper2.eq("user_id", uid).eq("pk_plan_id", basicPkPlanVO.getId());
//            if(userCreatePlanMapper.selectOne(queryWrapper2)!=null){
//                basicPkPlanVO.setPunchVictoryCount(userCreatePlanMapper.selectOne(queryWrapper2).getPunchVictoryCount());
//            }
//        }
        return new PageInfo<>(basicPkPlanVOList);
    }
}
