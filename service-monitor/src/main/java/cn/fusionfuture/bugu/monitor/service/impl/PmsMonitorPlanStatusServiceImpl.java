package cn.fusionfuture.bugu.monitor.service.impl;

import cn.fusionfuture.bugu.monitor.vo.MonitorPlanStatusVO;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPlanStatus;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPlanStatusMapper;
import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPlanStatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author thomas
 * @since 2020-09-11
 */
@Service
public class PmsMonitorPlanStatusServiceImpl extends ServiceImpl<PmsMonitorPlanStatusMapper, PmsMonitorPlanStatus> implements IPmsMonitorPlanStatusService {

    @Autowired
    PmsMonitorPlanStatusMapper monitorPlanStatusMapper;

    @Override
    public List<MonitorPlanStatusVO> queryMonitorPlanStatus() {
        return monitorPlanStatusMapper.queryMonitorPlanStatus();
    }
}
