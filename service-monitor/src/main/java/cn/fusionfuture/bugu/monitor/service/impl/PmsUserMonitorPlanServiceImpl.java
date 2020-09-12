package cn.fusionfuture.bugu.monitor.service.impl;

import cn.fusionfuture.bugu.monitor.vo.BasicMonitorPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsUserMonitorPlan;
import cn.fusionfuture.bugu.monitor.mapper.PmsUserMonitorPlanMapper;
import cn.fusionfuture.bugu.monitor.service.IPmsUserMonitorPlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PmsUserMonitorPlanServiceImpl extends ServiceImpl<PmsUserMonitorPlanMapper, PmsUserMonitorPlan> implements IPmsUserMonitorPlanService {

    @Autowired
    PmsUserMonitorPlanMapper userMonitorPlanMapper;


    @Override
    public PageInfo<BasicMonitorPlanVO> queryMonitorPlanByUserId(Integer pn, Integer ps, Long uid) {
        PageHelper.startPage(pn, ps);
        return new PageInfo<>(userMonitorPlanMapper.queryMonitorPlanByUserId(uid));
    }
}
