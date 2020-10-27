package cn.fusionfuture.bugu.monitor.mapper;

import cn.fusionfuture.bugu.monitor.vo.plan.BasicMonitorPlanVO;
import cn.fusionfuture.bugu.monitor.vo.plan.DetailedMonitorPlanVO;
import cn.fusionfuture.bugu.monitor.vo.plan.SimpleMonitorPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPlan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
@Repository
public interface PmsMonitorPlanMapper extends BaseMapper<PmsMonitorPlan> {

    /**
     * 查询BasicMonitorPlanVO
     * @author thomas
     * @since 2020/9/12 1:34 上午
     * @return java.util.List<cn.fusionfuture.bugu.monitor.vo.plan.BasicMonitorPlanVO>
     **/
    List<BasicMonitorPlanVO> queryBasicMonitorPlanVO(Long uid);

    /*
     * 依据计划id查询监督计划
     * @author zws
     * @since 2020/10/14 18:32
     * @param [pid]
     * @return cn.fusionfuture.bugu.monitor.vo.plan.BasicMonitorPlanVO
     **/
    DetailedMonitorPlanVO queryDetailedMonitorPlanVO(Long pid);

    /*
     * 根据计划id获取计划简略信息
     * @author zws
     * @since 2020/9/26 15:57
     * @param [planId]
     * @return cn.fusionfuture.bugu.monitor.vo.plan.SimpleMonitorPlanVO
     **/
    SimpleMonitorPlanVO querySimpleMonitorPlanVO(Long planId);



}
