package cn.fusionfuture.bugu.pk.service.impl;

import cn.fusionfuture.bugu.pk.dto.SimplePunchDTO;
import cn.fusionfuture.bugu.pk.mapper.*;
import cn.fusionfuture.bugu.pk.vo.BasicPunchVO;
import cn.fusionfuture.bugu.pk.vo.PunchWithImageVO;
import cn.fusionfuture.bugu.pk.vo.SimplePunchVO;
import cn.fusionfuture.bugu.pk.vo.UserAttendPlanRecordVO;
import cn.fusionfuture.bugu.pojo.constants.PunchStatus;
import cn.fusionfuture.bugu.pojo.entity.*;
import cn.fusionfuture.bugu.pojo.constants.*;
import cn.fusionfuture.bugu.pk.service.IPmsPkPunchRecordService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
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
    public void like(Long punchId){
        //点赞操作，将计划的点赞数+1
        PmsPkPunchRecord pkPunchRecord=pkPunchRecordMapper.selectById(punchId);
        pkPunchRecord.setLikeCount(pkPunchRecord.getLikeCount()+1);
        pkPunchRecordMapper.updateById(pkPunchRecord);
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
}
