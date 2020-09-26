package cn.fusionfuture.bugu.pk.service.impl;

import cn.fusionfuture.bugu.pk.mapper.*;
import cn.fusionfuture.bugu.pk.vo.BasicPunchVO;
import cn.fusionfuture.bugu.pk.vo.UserAttendPlanRecordVO;
import cn.fusionfuture.bugu.pojo.constants.PunchStatus;
import cn.fusionfuture.bugu.pojo.entity.*;
import cn.fusionfuture.bugu.pk.service.IPmsPkPunchRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class PmsPkPunchRecordServiceImpl extends ServiceImpl<PmsPkPunchRecordMapper, PmsPkPunchRecord> implements IPmsPkPunchRecordService {

    @Autowired
    PmsPkPunchRecordMapper pkPunchRecordMapper;

    @Autowired
    PmsPkPunchImageUrlMapper pkPunchImageUrlMapper;

    @Autowired
    PmsUserAttendPlanMapper userAttendPlanMapper;

    @Autowired
    PmsUserCreatePlanMapper userCreatePlanMapper;

    @Override
    public Long punch(Long userId, Long planId, String content, List<String> imageUrls) {
        // 保存打卡记录
        PmsPkPunchRecord pkPunchRecord = PmsPkPunchRecord.builder()
                .userId(userId)
                .pkPlanId(planId)
                .likeCount(0)
                .commentQuantity(0)
                .agreeCount(0)
                .disagreeCount(0)
                .content(content)
                .punchTime(LocalDateTime.now())
                .statusId(PunchStatus.PUNCHED)
                .build();
        pkPunchRecordMapper.insert(pkPunchRecord);

        // 保存图片路径
        for (String imageUrl : imageUrls) {
            pkPunchImageUrlMapper.insert(
                    new PmsPkPunchImageUrl()
                            .setPunchId(pkPunchRecord.getId())
                            .setImageUrl(imageUrl));
        }

        // 对应的计划打卡次数加一(用户参加计划）
        if(userAttendPlanMapper.queryByUserIdAndPlanId(userId,planId)!=null) {
            PmsUserAttendPlan userAttendPlan = new PmsUserAttendPlan();
            userAttendPlan.setPunchCount(userAttendPlanMapper.queryByUserIdAndPlanId(userId, planId).getPunchCount() + 1).setId(userAttendPlanMapper.queryByUserIdAndPlanId(userId, planId).getId());
            userAttendPlanMapper.updateById(userAttendPlan);
        }
        //对应的计划打卡次数加一（用户创建计划）
        if(userCreatePlanMapper.queryByUserIdAndPlanId(userId,planId)!=null) {
            PmsUserCreatePlan userCreatePlan = new PmsUserCreatePlan();
            userCreatePlan.setPunchCount(userCreatePlanMapper.queryByUserIdAndPlanId(userId, planId).getPunchCount() + 1).setId(userCreatePlanMapper.queryByUserIdAndPlanId(userId, planId).getId());
            userCreatePlanMapper.updateById(userCreatePlan);
        }
        // 返回打卡记录的id
        return pkPunchRecord.getId();
    }

    @Override
    public void like(Long punchId){
        //点赞操作，将计划的点赞数+1
        PmsPkPunchRecord pkPunchRecord=pkPunchRecordMapper.selectById(punchId);
        pkPunchRecord.setLikeCount(pkPunchRecord.getLikeCount()+1);
        pkPunchRecordMapper.updateById(pkPunchRecord);
    }

//    @Override
//    public BasicPunchVO queryBasicPunchVO(Long punchId){
//        //根据打卡id查询打卡的一些基本信息
//        return pkPunchRecordMapper.queryBasicPunchVO(punchId);
//    }
}
