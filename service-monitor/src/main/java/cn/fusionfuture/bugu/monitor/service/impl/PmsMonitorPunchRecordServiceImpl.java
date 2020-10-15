package cn.fusionfuture.bugu.monitor.service.impl;

import cn.fusionfuture.bugu.monitor.dto.SimplePunchDTO;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPatternMapper;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPlanMapper;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPunchImageUrlMapper;
import cn.fusionfuture.bugu.monitor.vo.BasicPunchVO;
import cn.fusionfuture.bugu.monitor.vo.SimplePunchVO;
import cn.fusionfuture.bugu.pojo.constants.PunchStatus;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPlan;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPunchImageUrl;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPunchRecord;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPunchRecordMapper;
import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPunchRecordService;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPunchRecord;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
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
public class PmsMonitorPunchRecordServiceImpl extends ServiceImpl<PmsMonitorPunchRecordMapper, PmsMonitorPunchRecord> implements IPmsMonitorPunchRecordService {

    @Autowired
    PmsMonitorPunchRecordMapper monitorPunchRecordMapper;

    @Autowired
    PmsMonitorPunchImageUrlMapper monitorPunchImageUrlMapper;

    @Autowired
    PmsMonitorPlanMapper monitorPlanMapper;

    @Autowired
    PmsMonitorPatternMapper monitorPatternMapper;

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
                plan.setPunchCount(plan.getPunchCount() + 1);
                monitorPlanMapper.updateById(plan);
                return "打卡成功";
            }
        }
        return "当前时间无法打卡";
    }

    @Override
    public void like(Long punchId){
        //点赞操作，将计划的点赞数+1
        PmsMonitorPunchRecord monitorPunchRecord=monitorPunchRecordMapper.selectById(punchId);
        monitorPunchRecord.setLikeCount(monitorPunchRecord.getLikeCount()+1);
        monitorPunchRecordMapper.updateById(monitorPunchRecord);
    }

    @Override
    public BasicPunchVO queryBasicPunchVO(Long punchId){


        BasicPunchVO basicPunchVO = new BasicPunchVO();
        PmsMonitorPunchRecord monitorPunchRecord = monitorPunchRecordMapper.selectById(punchId);
        //获取对应的计划id
        Long planId=monitorPunchRecord.getMonitorPlanId();
        PmsMonitorPlan monitorPlan=monitorPlanMapper.selectById(planId);
        basicPunchVO.setName(monitorPlan.getName())
                .setPlanPattern(monitorPatternMapper.selectById(monitorPlan.getMonitorPatternId()).getDescription())
                .setAgreeCount(monitorPunchRecord.getAgreeCount())
                .setDisagreeCount(monitorPunchRecord.getDisagreeCount())
                .setContent(monitorPunchRecord.getContent())
                .setLikeCount(monitorPunchRecord.getLikeCount())
                .setImageUrls(monitorPunchImageUrlMapper.queryImageByPunchId(punchId));
        return basicPunchVO;
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
}
