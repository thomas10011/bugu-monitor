package cn.fusionfuture.bugu.monitor.service.impl;

import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPlan;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPlanMapper;
import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

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
    public Long createMonitorPlan(PmsMonitorPlan monitorPlan) {
        monitorPlanMapper.insert(monitorPlan);
        return monitorPlan.getId();
    }
}
