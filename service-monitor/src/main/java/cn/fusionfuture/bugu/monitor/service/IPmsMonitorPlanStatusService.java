package cn.fusionfuture.bugu.monitor.service;

import cn.fusionfuture.bugu.monitor.vo.MonitorPlanStatusVO;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPlanStatus;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author thomas
 * @since 2020-09-11
 */
public interface IPmsMonitorPlanStatusService extends IService<PmsMonitorPlanStatus> {

    /**
     * 查询监督计划所有的状态列表
     * @author thomas
     * @since 2020/9/11 11:26 下午
     * @return java.util.List<cn.fusionfuture.bugu.monitor.vo.MonitorPlanStatusVO>
     **/
    List<MonitorPlanStatusVO> queryMonitorPlanStatus();

}
