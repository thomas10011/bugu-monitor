package cn.fusionfuture.bugu.monitor.service.impl;

import cn.fusionfuture.bugu.monitor.dto.MonitorPlanTrendDTO;
import cn.fusionfuture.bugu.monitor.dto.SimplePunchDTO;
import cn.fusionfuture.bugu.monitor.feign.UserFeignService;
import cn.fusionfuture.bugu.monitor.mapper.*;
import cn.fusionfuture.bugu.monitor.vo.punch.BasicPunchVO;
import cn.fusionfuture.bugu.monitor.vo.punch.DetailedPunchVO;
import cn.fusionfuture.bugu.monitor.vo.punch.PlanTrendVO;
import cn.fusionfuture.bugu.monitor.vo.punch.SimplePunchVO;
import cn.fusionfuture.bugu.pojo.constants.PunchStatus;
import cn.fusionfuture.bugu.pojo.entity.*;
import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPunchRecordService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
@Service
public class PmsMonitorPunchRecordServiceImpl extends ServiceImpl<PmsMonitorPunchRecordMapper, PmsMonitorPunchRecord> implements IPmsMonitorPunchRecordService {

    @Autowired
    PmsMonitorPunchRecordMapper monitorPunchRecordMapper;

    @Autowired
    PmsMonitorPunchImageUrlMapper monitorPunchImageUrlMapper;

    @Autowired
    PmsMonitorPlanMapper monitorPlanMapper;

    @Autowired
    PmsMonitorPatternMapper monitorPatternMapper;

    @Autowired
    PmsMonitorUserGrabTicketMapper monitorUserGrabTicketMapper;

    @Autowired
    UserFeignService userFeignService;

    @Override
    public String punch(Long planId, String content, List<String> imageUrls) {
        //获取当前时间
        LocalDateTime currentTime=LocalDateTime.now();
        QueryWrapper<PmsMonitorPunchRecord> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("monitor_plan_id",planId);
        List<PmsMonitorPunchRecord> punchRecords=monitorPunchRecordMapper.selectList(queryWrapper);
        for (PmsMonitorPunchRecord monitorPunchRecord:punchRecords
        ) {
            //更新打卡内容
            if(currentTime.isAfter(monitorPunchRecord.getStartTime())&&currentTime.isBefore(monitorPunchRecord.getExpiredTime())){
                if(monitorPunchRecord.getContent()!=null){
                    return "当前周期已打卡";
                }
                monitorPunchRecord.setContent(content).setStatusId(PunchStatus.PUNCHED).setPunchTime(currentTime);
                monitorPunchRecordMapper.updateById(monitorPunchRecord);
                // 保存图片路径
                for (String imageUrl : imageUrls) {
                    monitorPunchImageUrlMapper.insert(
                            new PmsMonitorPunchImageUrl()
                                    .setPunchId(monitorPunchRecord.getId())
                                    .setImageUrl(imageUrl));
                }

                // 对应的计划打卡次数加一，对应打卡记录的打卡状态设置为已打卡
                PmsMonitorPlan plan = monitorPlanMapper.selectById(planId);
                monitorPlanMapper.updateById(
                        new PmsMonitorPlan()
                                .setId(plan.getId())
                                .setPunchCount(plan.getPunchCount() + 1));
                return "打卡成功";
            }
        }
        return "当前时间无法打卡";
    }

    @Override
    public void like(Long punchId){
        //点赞操作，将打卡的点赞数+1
        PmsMonitorPunchRecord monitorPunchRecord=monitorPunchRecordMapper.selectById(punchId);
        monitorPunchRecordMapper.updateById(monitorPunchRecord.setLikeCount(monitorPunchRecord.getLikeCount()+1));
    }

    @Override
    public void cancelLike(Long punchId){
        //取消点赞操作，将计划的点赞数-1
        PmsMonitorPunchRecord monitorPunchRecord=monitorPunchRecordMapper.selectById(punchId);
        monitorPunchRecordMapper.updateById(monitorPunchRecord.setLikeCount(monitorPunchRecord.getLikeCount()+1));
    }
    @Override
    public BasicPunchVO queryBasicPunchVO(Long punchId){


        BasicPunchVO basicPunchVO = new BasicPunchVO();
        PmsMonitorPunchRecord monitorPunchRecord = monitorPunchRecordMapper.selectById(punchId);
        //获取对应的计划id
        Long planId=monitorPunchRecord.getMonitorPlanId();
        PmsMonitorPlan monitorPlan=monitorPlanMapper.selectById(planId);
        basicPunchVO.setName(monitorPlan.getName())
                .setContent(monitorPunchRecord.getContent())
                .setCurrentPunchCycle(monitorPunchRecord.getCurrentPunchCycle())
                .setId(punchId)
                .setPunchTime(monitorPunchRecord.getPunchTime())
                .setImageUrls(monitorPunchImageUrlMapper.queryImageByPunchId(punchId));
        return basicPunchVO;
    }

    @Override
    public DetailedPunchVO queryDetailedPunchVO(Long punchId){
        PmsMonitorPunchRecord monitorPunchRecord=monitorPunchRecordMapper.selectById(punchId);
        PmsMonitorPlan monitorPlan=monitorPlanMapper.selectById(monitorPunchRecord.getMonitorPlanId());
        HashMap<String,String> puncher = userFeignService.getDetailsForMessage(monitorPunchRecord.getUserId()).getData();

        DetailedPunchVO detailedPunchVO=new DetailedPunchVO();
        detailedPunchVO.setUserName(puncher.get("userName"))
                .setUserImage(puncher.get("avatarUrl"))
                .setPlanPattern(monitorPatternMapper.selectById(monitorPlan.getMonitorPatternId()).getDescription())
                .setName(monitorPlan.getName())
                .setPunchId(punchId)
                .setContent(monitorPunchRecord.getContent())
                .setLikeCount(monitorPunchRecord.getLikeCount())
                .setAgreeCount(monitorPunchRecord.getAgreeCount())
                .setDisagreeCount(monitorPunchRecord.getDisagreeCount())
                .setCommentQuantity(monitorPunchRecord.getCommentQuantity())
                .setImageUrls(monitorPunchImageUrlMapper.queryImageByPunchId(monitorPunchRecord.getId()))
                .setPunchTime(monitorPunchRecord.getPunchTime())
                .setCurrentPunchCycle(monitorPunchRecord.getCurrentPunchCycle());
        return detailedPunchVO;
    }


    @Override
    public List<SimplePunchVO> querySimplePunchVO(Long planId) {
        List<SimplePunchDTO> simplePunchDTOS=monitorPunchRecordMapper.querySimplePunchDTO(planId);
        List<SimplePunchVO> simplePunchVOS=new ArrayList<SimplePunchVO>() ;
        for (SimplePunchDTO simplePunchDTO:
             simplePunchDTOS) {
            SimplePunchVO simplePunchVO=new SimplePunchVO();
            simplePunchVO.setId(simplePunchDTO.getId()).setPunchStatus(simplePunchDTO.getStatus());
            if(simplePunchDTO.getStatus().equals("未打卡")){
                simplePunchVO.setPunchTime(simplePunchDTO.getExpiredTime());
            }
            else{
                simplePunchVO.setPunchTime(simplePunchDTO.getPunchTime());
            }
            simplePunchVOS.add(simplePunchVO);
        }
        return simplePunchVOS;
    }

    @Override
    public List<PlanTrendVO> queryMonitorPlanTrendVO(Long userId){
        //将返回的值
        List<MonitorPlanTrendDTO> monitorPlanTrendDTOS=new ArrayList<>();
        //目标计划的id
        List<Long> planIds=new ArrayList<>();
        //查询获取用户相关计划id
        QueryWrapper<PmsMonitorUserGrabTicket> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("user_id",userId);
        //用户有权参与投票的计划
        List<PmsMonitorUserGrabTicket> monitorUserGrabTickets=monitorUserGrabTicketMapper.selectList(queryWrapper1);
        //获取目标计划id
        for (PmsMonitorUserGrabTicket monitorUserGrabTicket:
                monitorUserGrabTickets
             ) {
            planIds.add(monitorUserGrabTicket.getMonitorPlanId());
        }
        //需要返回的打卡记录
        List<PmsMonitorPunchRecord> monitorPunchRecords=new ArrayList<>();

        for (Long planId:planIds
             ) {
            PmsMonitorPlan monitorPlan=monitorPlanMapper.selectById(planId);
            //如果计划正在进行中
            Integer planStatusId=monitorPlan.getPlanStatusId();
            //Integer i=MonitorPlanStatus.UNDERWAY.getIndex();
            if(planStatusId.equals(2)) {
                //查询获取用户相关计划id
                QueryWrapper<PmsMonitorPunchRecord> queryWrapper2=new QueryWrapper<>();
                queryWrapper2.eq("monitor_plan_id",planId);
                List<PmsMonitorPunchRecord> monitorPunchRecordDemos=monitorPunchRecordMapper.selectList(queryWrapper2);
                LocalDateTime currentTime=LocalDateTime.now();
                for (PmsMonitorPunchRecord monitorPunchRecord:monitorPunchRecordDemos
                     ) {
                    //如果当前时间处于一个打卡周期内则将该打卡记录返回
                    if(currentTime.isAfter(monitorPunchRecord.getStartTime())&&currentTime.isBefore(monitorPunchRecord.getExpiredTime())){
                        monitorPunchRecords.add(monitorPunchRecord);
                    }
                }
            }
        }

        for (PmsMonitorPunchRecord monitorPunchRecord:monitorPunchRecords
             ) {
            MonitorPlanTrendDTO monitorPlanTrendDTO=new MonitorPlanTrendDTO();
            //当前时间
            LocalDateTime currentTime=LocalDateTime.now();
            Long planId=monitorPunchRecord.getMonitorPlanId();
            PmsMonitorPlan monitorPlan=monitorPlanMapper.selectById(planId);
            monitorPlanTrendDTO.setUid(monitorPlan.getUserId())
                    .setName(monitorPlan.getName())
                    .setPlanPattern(monitorPatternMapper.selectById(monitorPlan.getMonitorPatternId()).getDescription())
                    .setPunchId(monitorPunchRecord.getId())
                    .setContent(monitorPunchRecord.getContent())
                    .setLikeCount(monitorPunchRecord.getLikeCount())
                    .setAgreeCount(monitorPunchRecord.getAgreeCount())
                    .setDisagreeCount(monitorPunchRecord.getDisagreeCount())
                    .setCommentQuantity(monitorPunchRecord.getCommentQuantity())
                    .setImageUrls(monitorPunchImageUrlMapper.queryImageByPunchId(monitorPunchRecord.getId()))
                    .setPunchTime(monitorPunchRecord.getPunchTime())
                    .setCurrentPunchCycle(monitorPunchRecord.getCurrentPunchCycle());
            monitorPlanTrendDTOS.add(monitorPlanTrendDTO);
        }

        List<PlanTrendVO> planTrendVOS =new ArrayList<>();

        for (MonitorPlanTrendDTO monitorPlanTrendDemoDTO:monitorPlanTrendDTOS
        ) {
            //打卡者
            HashMap<String,String> puncher = userFeignService.getDetailsForMessage(monitorPlanTrendDemoDTO.getUid()).getData();

            PlanTrendVO planTrendVO =new PlanTrendVO();
            planTrendVO.setUserName(puncher.get("userName"))
                    .setUserImage(puncher.get("avatarUrl"))
                    .setPlanPattern(monitorPlanTrendDemoDTO.getPlanPattern())
                    .setName(monitorPlanTrendDemoDTO.getName())
                    .setPunchId(monitorPlanTrendDemoDTO.getPunchId())
                    .setContent(monitorPlanTrendDemoDTO.getContent())
                    .setLikeCount(monitorPlanTrendDemoDTO.getLikeCount())
                    .setAgreeCount(monitorPlanTrendDemoDTO.getAgreeCount())
                    .setDisagreeCount(monitorPlanTrendDemoDTO.getDisagreeCount())
                    .setCommentQuantity(monitorPlanTrendDemoDTO.getCommentQuantity())
                    .setImageUrls(monitorPlanTrendDemoDTO.getImageUrls())
                    .setPunchTime(monitorPlanTrendDemoDTO.getPunchTime())
                    .setCurrentPunchCycle(monitorPlanTrendDemoDTO.getCurrentPunchCycle());
            planTrendVOS.add(planTrendVO);
        }
        return planTrendVOS;
    }

    @Override
    public Integer getCurrentPunchCycle(LocalDateTime currentTime,Long planId){
        QueryWrapper<PmsMonitorPunchRecord> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("monitor_plan_id",planId);
        List<PmsMonitorPunchRecord> monitorPunchRecords=monitorPunchRecordMapper.selectList(queryWrapper);
        for (PmsMonitorPunchRecord monitorPunchRecord:monitorPunchRecords
             ) {
            if(currentTime.isAfter(monitorPunchRecord.getStartTime())&&currentTime.isBefore(monitorPunchRecord.getExpiredTime())){
                Integer currentPunchCycle=monitorPunchRecords.indexOf(monitorPunchRecord)+1;
                return currentPunchCycle;
            }
        }
        return 0;
    }
}
