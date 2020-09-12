package cn.fusionfuture.bugu.monitor.service.impl;

import cn.fusionfuture.bugu.monitor.vo.BasicMonitorPlanVO;
import cn.fusionfuture.bugu.monitor.vo.NewMonitorPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPlan;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPlanMapper;
import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

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
public class PmsMonitorPlanServiceImpl extends ServiceImpl<PmsMonitorPlanMapper, PmsMonitorPlan> implements IPmsMonitorPlanService {

    @Autowired
    PmsMonitorPlanMapper monitorPlanMapper;

    @Override
    public Long createMonitorPlan(NewMonitorPlanVO newMonitorPlanVO) {
        PmsMonitorPlan monitorPlan = new PmsMonitorPlan();
        BeanUtils.copyProperties(newMonitorPlanVO, monitorPlan);
        // 插入前把已打卡次数置为零
        monitorPlanMapper.insert(monitorPlan.setPunchCount(0));
        return monitorPlan.getId();
    }

    @Override
    public PageInfo<BasicMonitorPlanVO> queryBasicMonitorPlanVO(Integer pn, Integer ps, Long uid) {
        PageHelper.startPage(pn, ps);
        return new PageInfo<>(monitorPlanMapper.queryBasicMonitorPlanVO(uid));
    }

}
