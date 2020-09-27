package cn.fusionfuture.bugu.monitor.mapper;

import cn.fusionfuture.bugu.monitor.vo.BasicMonitorPlanVO;
import cn.fusionfuture.bugu.monitor.vo.SimpleMonitorPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPlan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
public interface PmsMonitorPlanMapper extends BaseMapper<PmsMonitorPlan> {

    /**
     * 查询BasicMonitorPlanVO
     * @author thomas
     * @since 2020/9/12 1:34 上午
     * @return java.util.List<cn.fusionfuture.bugu.monitor.vo.BasicMonitorPlanVO>
     **/
    List<BasicMonitorPlanVO> queryBasicMonitorPlanVO(Long uid);

    /*
     * TODO 根据计划id获取计划简略信息
     * @author zws
     * @since 2020/9/26 15:57
     * @param [planId]
     * @return cn.fusionfuture.bugu.monitor.vo.SimpleMonitorPlanVO
     **/
    SimpleMonitorPlanVO querySimpleMonitorPlanVO(Long planId);
}
