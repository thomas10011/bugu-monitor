package cn.fusionfuture.bugu.monitor.service.impl;

import cn.fusionfuture.bugu.monitor.vo.MonitorPlanPatternVO;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPattern;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPatternMapper;
import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPatternService;
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
 * @since 2020-08-24
 */
@Service
public class PmsMonitorPatternServiceImpl extends ServiceImpl<PmsMonitorPatternMapper, PmsMonitorPattern> implements IPmsMonitorPatternService {

    @Autowired
    PmsMonitorPatternMapper monitorPatternMapper;

    @Override
    public List<MonitorPlanPatternVO> queryMonitorPlanPatter() {
        return monitorPatternMapper.queryMonitorPlanPatter();
    }
}
