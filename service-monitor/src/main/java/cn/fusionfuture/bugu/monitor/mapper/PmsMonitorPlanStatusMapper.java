package cn.fusionfuture.bugu.monitor.mapper;

import cn.fusionfuture.bugu.monitor.vo.MonitorPlanStatusVO;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPlanStatus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author thomas
 * @since 2020-09-11
 */
public interface PmsMonitorPlanStatusMapper extends BaseMapper<PmsMonitorPlanStatus> {
    /**
     * 查询监督计划所有的状态列表
     * @author thomas
     * @since 2020/9/11 11:27 下午
     * @return java.util.List<cn.fusionfuture.bugu.monitor.vo.MonitorPlanStatusVO>
     **/
    List<MonitorPlanStatusVO> queryMonitorPlanStatus();

}
