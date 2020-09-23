package cn.fusionfuture.bugu.pk.service.impl;

import cn.fusionfuture.bugu.pk.mapper.*;
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
    public Long punch(Long planId, Long userId, String content, List<String> imageUrls) {
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

        // 对应的计划打卡次数加一
//        PmsUserAttendPlan userAttendPlan = userAttendPlanMapper.selectById(userAttendPlanMapper.queryByUserIdAndPlanId(planId, userId).getId());
//        userAttendPlan.setPunchCount(userAttendPlan.getPunchCount() + 1);
//        userAttendPlanMapper.updateById(userAttendPlan);

        // 返回打卡记录的id
        return pkPunchRecord.getId();
    }
}
