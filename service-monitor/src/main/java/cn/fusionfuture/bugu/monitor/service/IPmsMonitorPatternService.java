package cn.fusionfuture.bugu.monitor.service;

import cn.fusionfuture.bugu.monitor.vo.MonitorPlanPatternVO;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPattern;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
public interface IPmsMonitorPatternService extends IService<PmsMonitorPattern> {

    /**
     * 查询监督计划类型
     * @author thomas
     * @since 2020/9/11 11:03 下午
     * @return cn.fusionfuture.bugu.monitor.vo.MonitorPlanPatternVO
     **/
    List<MonitorPlanPatternVO> queryMonitorPlanPatter();

}
