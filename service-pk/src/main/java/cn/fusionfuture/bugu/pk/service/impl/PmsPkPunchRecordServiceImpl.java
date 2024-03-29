package cn.fusionfuture.bugu.pk.service.impl;

import cn.fusionfuture.bugu.pk.dto.PkPlanTrendDTO;
import cn.fusionfuture.bugu.pk.dto.PunchForMessageDTO;
import cn.fusionfuture.bugu.pk.dto.SimplePunchDTO;
import cn.fusionfuture.bugu.pk.feign.SearchFeignService;
import cn.fusionfuture.bugu.pk.feign.UserFeignService;
import cn.fusionfuture.bugu.pk.mapper.*;
import cn.fusionfuture.bugu.pk.vo.*;
import cn.fusionfuture.bugu.pk.vo.punch.BasicPunchVO;
import cn.fusionfuture.bugu.pk.vo.punch.DetailedPunchVO;
import cn.fusionfuture.bugu.pk.vo.punch.PlanTrendVO;
import cn.fusionfuture.bugu.pk.vo.punch.SimplePunchVO;
import cn.fusionfuture.bugu.pojo.constants.PkPlanStatus;
import cn.fusionfuture.bugu.pojo.constants.PunchStatus;
import cn.fusionfuture.bugu.pojo.entity.*;
import cn.fusionfuture.bugu.pk.service.IPmsPkPunchRecordService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
public class PmsPkPunchRecordServiceImpl extends ServiceImpl<PmsPkPunchRecordMapper, PmsPkPunchRecord> implements IPmsPkPunchRecordService {

    @Autowired
    PmsPkPunchRecordMapper pkPunchRecordMapper;

    @Autowired
    PmsPkPunchImageUrlMapper pkPunchImageUrlMapper;

    @Autowired
    PmsUserAttendPlanMapper userAttendPlanMapper;

    @Autowired
    PmsUserCreatePlanMapper userCreatePlanMapper;

    @Autowired
    PmsPkUserGrabTicketMapper pkUserGrabTicketMapper;

    @Autowired
    PmsPkPlanMapper pkPlanMapper;

    @Autowired
    PmsPkPatternMapper pkPatternMapper;

    @Autowired
    UserFeignService userFeignService;

    @Autowired
    SearchFeignService searchFeignService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String punch(Long userId, Long planId, String content, List<String> imageUrls) {
        //获取当前时间
        LocalDateTime currentTime = LocalDateTime.now();
        QueryWrapper<PmsPkPunchRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pk_plan_id", planId).eq("user_id", userId);
        List<PmsPkPunchRecord> punchRecords = pkPunchRecordMapper.selectList(queryWrapper);
        for (PmsPkPunchRecord pkPunchRecord : punchRecords
        ) {
            //更新打卡内容
            if (currentTime.isAfter(pkPunchRecord.getStartTime()) && currentTime.isBefore(pkPunchRecord.getExpiredTime())) {
                if (pkPunchRecord.getContent() != null) {
                    return "当前周期已打卡";
                }
                pkPunchRecord.setContent(content).setStatusId(PunchStatus.PUNCHED).setPunchTime(currentTime);
                pkPunchRecordMapper.updateById(pkPunchRecord);

                // 保存图片路径
                for (String imageUrl : imageUrls) {
                    pkPunchImageUrlMapper.insert(
                            new PmsPkPunchImageUrl()
                                    .setPunchId(pkPunchRecord.getId())
                                    .setImageUrl(imageUrl));
                }

                // 对应的计划打卡次数加一(用户参加计划）
                if (userAttendPlanMapper.queryByUserIdAndPlanId(userId, planId) != null) {
                    PmsUserAttendPlan userAttendPlan = new PmsUserAttendPlan();
                    userAttendPlan.setPunchCount(userAttendPlanMapper.queryByUserIdAndPlanId(userId, planId).getPunchCount() + 1).setId(userAttendPlanMapper.queryByUserIdAndPlanId(userId, planId).getId());
                    userAttendPlanMapper.updateById(userAttendPlan);
                }
                //对应的计划打卡次数加一（用户创建计划）
                if (userCreatePlanMapper.queryByUserIdAndPlanId(userId, planId) != null) {
                    PmsUserCreatePlan userCreatePlan = new PmsUserCreatePlan();
                    userCreatePlan.setPunchCount(userCreatePlanMapper.queryByUserIdAndPlanId(userId, planId).getPunchCount() + 1).setId(userCreatePlanMapper.queryByUserIdAndPlanId(userId, planId).getId());
                    userCreatePlanMapper.updateById(userCreatePlan);
                }
                return "打卡成功";
            }

        }
        return "当前时间无法打卡";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void like(Long punchId) throws IOException {
        //点赞操作，将打卡的点赞数+1
        PmsPkPunchRecord pkPunchRecord=pkPunchRecordMapper.selectById(punchId);
        pkPunchRecord.setLikeCount(pkPunchRecord.getLikeCount()+1);
        pkPunchRecordMapper.updateById(pkPunchRecord);
        PmsPkPlan pkPlan=pkPlanMapper.selectById(pkPunchRecord.getPkPlanId());
        pkPlanMapper.updateById(pkPlan.setLikeCount(pkPlan.getLikeCount()+1));
        searchFeignService.ratePopularPlan(pkPlan.getId());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelLike(Long punchId) throws IOException {
        //取消点赞，将打卡的点赞数-1
        PmsPkPunchRecord pkPunchRecord=pkPunchRecordMapper.selectById(punchId);
        pkPunchRecord.setLikeCount(pkPunchRecord.getLikeCount()-1);
        pkPunchRecordMapper.updateById(pkPunchRecord);
        PmsPkPlan pkPlan=pkPlanMapper.selectById(pkPunchRecord.getPkPlanId());
        pkPlanMapper.updateById(pkPlan.setLikeCount(pkPlan.getLikeCount()-1));
        searchFeignService.cancelRatePopularPlan(pkPlan.getId());
    }

    @Override
    public PunchWithImageVO queryPunchWithImageVO(Long punchId){
        //根据打卡id查询打卡的一些基本信息
        PunchWithImageVO punchWithImageVO= new PunchWithImageVO();
        BasicPunchVO basicPunchVO= pkPunchRecordMapper.queryBasicPunchVO(punchId);
        BeanUtils.copyProperties(basicPunchVO,punchWithImageVO);
        //获取图片
        punchWithImageVO.setImageUrls(pkPunchImageUrlMapper.queryImageByPunchId(punchId));
        return punchWithImageVO;
    }

    @Override
    public DetailedPunchVO queryDetailedPunchVO(Long punchId){
        PmsPkPunchRecord pkPunchRecord=pkPunchRecordMapper.selectById(punchId);
        PmsPkPlan pkPlan=pkPlanMapper.selectById(pkPunchRecord.getPkPlanId());
        HashMap<String,String> puncher = userFeignService.getDetailsForMessage(pkPunchRecord.getUserId()).getData();

        DetailedPunchVO detailedPunchVO=new DetailedPunchVO();
        detailedPunchVO.setUserId(pkPunchRecord.getUserId())
                .setUserName(puncher.get("userName"))
                .setUserImage(puncher.get("avatarUrl"))
                .setPlanPattern(pkPatternMapper.selectById(pkPlan.getPkPatternId()).getDescription())
                .setName(pkPlan.getName())
                .setPunchId(punchId)
                .setContent(pkPunchRecord.getContent())
                .setLikeCount(pkPunchRecord.getLikeCount())
                .setAgreeCount(pkPunchRecord.getAgreeCount())
                .setDisagreeCount(pkPunchRecord.getDisagreeCount())
                .setCommentQuantity(pkPunchRecord.getCommentQuantity())
                .setImageUrls(pkPunchImageUrlMapper.queryImageByPunchId(pkPunchRecord.getId()))
                .setPunchTime(pkPunchRecord.getPunchTime())
                .setCurrentPunchCycle(pkPunchRecord.getCurrentPunchCycle());
        return detailedPunchVO;
    }

    @Override
    public List<SimplePunchVO> querySimplePunchVO(Long userId,Long planId) {
        List<SimplePunchDTO> simplePunchDTOS=pkPunchRecordMapper.querySimplePunchDTO(userId,planId);
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
    public List<PlanTrendVO> queryPkPlanTrendVO(Long userId){
        //将返回的值
        List<PkPlanTrendDTO> pkPlanTrendDTOS=new ArrayList<>();
        //目标计划的id
        List<Long> planIds=new ArrayList<>();
        //查询获取用户相关计划id
        QueryWrapper<PmsPkUserGrabTicket> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("user_id",userId);
        //用户有权参与投票的计划
        List<PmsPkUserGrabTicket> pkUserGrabTickets=pkUserGrabTicketMapper.selectList(queryWrapper1);
        //获取目标计划id
        for (PmsPkUserGrabTicket pkUserGrabTicket:
                pkUserGrabTickets
        ) {
            planIds.add(pkUserGrabTicket.getPkPlanId());
        }
        //需要返回的打卡记录
        List<PmsPkPunchRecord> pkPunchRecords=new ArrayList<>();

        for (Long planId:planIds
        ) {
            PmsPkPlan pkPlan=pkPlanMapper.selectById(planId);
            //如果计划正在进行中
            Integer planStatusId=pkPlan.getPlanStatusId();
            if(planStatusId.equals(PkPlanStatus.GRABBING.getIndex())) {
                //查询获取用户相关计划id
                QueryWrapper<PmsPkPunchRecord> queryWrapper2=new QueryWrapper<>();
                queryWrapper2.eq("pk_plan_id",planId);
                List<PmsPkPunchRecord> pkPunchRecordDemos=pkPunchRecordMapper.selectList(queryWrapper2);
                LocalDateTime currentTime=LocalDateTime.now();
                for (PmsPkPunchRecord pkPunchRecord:pkPunchRecordDemos
                ) {
                    //如果当前时间处于一个打卡周期内则将该打卡记录返回
                    if(currentTime.isAfter(pkPunchRecord.getStartTime())&&currentTime.isBefore(pkPunchRecord.getExpiredTime())){
                        pkPunchRecords.add(pkPunchRecord);
                    }
                }
            }
        }

        for (PmsPkPunchRecord pkPunchRecord:pkPunchRecords
        ) {
            PkPlanTrendDTO pkPlanTrendDTO=new PkPlanTrendDTO();
            //当前时间
            LocalDateTime currentTime=LocalDateTime.now();
            Long planId=pkPunchRecord.getPkPlanId();
            PmsPkPlan pkPlan=pkPlanMapper.selectById(planId);
            pkPlanTrendDTO.setUid(pkPunchRecord.getUserId())
                    .setName(pkPlan.getName())
                    .setPlanPattern(pkPatternMapper.selectById(pkPlan.getPkPatternId()).getDescription())
                    .setPlanId(planId)
                    .setPunchId(pkPunchRecord.getId())
                    .setContent(pkPunchRecord.getContent())
                    .setLikeCount(pkPunchRecord.getLikeCount())
                    .setAgreeCount(pkPunchRecord.getAgreeCount())
                    .setDisagreeCount(pkPunchRecord.getDisagreeCount())
                    .setCommentQuantity(pkPunchRecord.getCommentQuantity())
                    .setImageUrls(pkPunchImageUrlMapper.queryImageByPunchId(pkPunchRecord.getId()))
                    .setPunchTime(pkPunchRecord.getPunchTime())
                    .setCurrentPunchCycle(pkPunchRecord.getCurrentPunchCycle());
            pkPlanTrendDTOS.add(pkPlanTrendDTO);
        }

        List<PlanTrendVO> planTrendVOS =new ArrayList<>();

        for (PkPlanTrendDTO pkPlanTrendDemoDTO:pkPlanTrendDTOS
        ) {
            //打卡者
            HashMap<String,String> puncher = userFeignService.getDetailsForMessage(pkPlanTrendDemoDTO.getUid()).getData();

            PlanTrendVO planTrendVO =new PlanTrendVO();
            planTrendVO.setUserId(pkPlanTrendDemoDTO.getUid())
                    .setUserName(puncher.get("userName"))
                    .setUserImage(puncher.get("avatarUrl"))
                    .setPlanPattern(pkPlanTrendDemoDTO.getPlanPattern())
                    .setName(pkPlanTrendDemoDTO.getName())
                    .setPlanId(pkPlanTrendDemoDTO.getPlanId())
                    .setPunchId(pkPlanTrendDemoDTO.getPunchId())
                    .setContent(pkPlanTrendDemoDTO.getContent())
                    .setLikeCount(pkPlanTrendDemoDTO.getLikeCount())
                    .setAgreeCount(pkPlanTrendDemoDTO.getAgreeCount())
                    .setDisagreeCount(pkPlanTrendDemoDTO.getDisagreeCount())
                    .setCommentQuantity(pkPlanTrendDemoDTO.getCommentQuantity())
                    .setImageUrls(pkPlanTrendDemoDTO.getImageUrls())
                    .setPunchTime(pkPlanTrendDemoDTO.getPunchTime())
                    .setCurrentPunchCycle(pkPlanTrendDemoDTO.getCurrentPunchCycle());
            planTrendVOS.add(planTrendVO);
        }
        return planTrendVOS;
    }

    @Override
    public Integer getCurrentPunchCycle(LocalDateTime currentTime,Long planId){
        QueryWrapper<PmsPkPunchRecord> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("pk_plan_id",planId);
        List<PmsPkPunchRecord> pkPunchRecords=pkPunchRecordMapper.selectList(queryWrapper);
        for (PmsPkPunchRecord pkPunchRecord:pkPunchRecords
        ) {
            if(currentTime.isAfter(pkPunchRecord.getStartTime())&&currentTime.isBefore(pkPunchRecord.getExpiredTime())){
                Integer currentPunchCycle=pkPunchRecords.indexOf(pkPunchRecord)+1;
                return currentPunchCycle;
            }
        }
        return 0;
    }
    @Override
    public PunchForMessageDTO getPunchForMessageDTO(Long punchId) {
        PmsPkPunchRecord pkPunchRecord=pkPunchRecordMapper.selectById(punchId);
        PmsPkPlan pkPlan=pkPlanMapper.selectById(pkPunchRecord.getPkPlanId());

        PunchForMessageDTO plan=new PunchForMessageDTO();
        plan.setPlanPattern(pkPatternMapper.selectById(pkPlan.getPkPatternId()).getDescription())
                .setName(pkPlan.getName())
                .setContent(pkPunchRecord.getContent())
                .setLikeCount(pkPunchRecord.getLikeCount())
                .setAgreeCount(pkPunchRecord.getAgreeCount())
                .setDisagreeCount(pkPunchRecord.getDisagreeCount())
                .setImageUrls(pkPunchImageUrlMapper.queryImageByPunchId(pkPunchRecord.getId()));
        return plan;
    }
}
