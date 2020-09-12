package cn.fusionfuture.bugu.monitor.service.impl;

import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPunchImageUrlMapper;
import cn.fusionfuture.bugu.pojo.constants.PunchStatus;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPunchRecord;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPunchRecordMapper;
import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPunchRecordService;
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
public class PmsMonitorPunchRecordServiceImpl extends ServiceImpl<PmsMonitorPunchRecordMapper, PmsMonitorPunchRecord> implements IPmsMonitorPunchRecordService {

    @Autowired
    PmsMonitorPunchRecordMapper monitorPunchRecordMapper;

    @Autowired
    PmsMonitorPunchImageUrlMapper monitorPunchImageUrlMapper;

    @Override
    public Long punch(Long planId, Long userId, String content, List<String> imageUrls) {
        PmsMonitorPunchRecord monitorPunchRecord = PmsMonitorPunchRecord.builder()
                .userId(userId)
                .monitorPlanId(planId)
                .likeCount(0)
                .commentQuantity(0)
                .agreeCount(0)
                .disagreeCount(0)
                .content(content)
                .punchTime(LocalDateTime.now())
                .statusId(PunchStatus.PUNCHED)
                .build();

        monitorPunchRecordMapper.insert(monitorPunchRecord);
        return monitorPunchRecord.getId();
    }
}
