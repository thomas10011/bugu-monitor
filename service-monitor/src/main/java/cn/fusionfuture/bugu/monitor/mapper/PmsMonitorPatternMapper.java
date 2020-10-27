package cn.fusionfuture.bugu.monitor.mapper;

import cn.fusionfuture.bugu.monitor.vo.MonitorPlanPatternVO;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPattern;
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
public interface PmsMonitorPatternMapper extends BaseMapper<PmsMonitorPattern> {

    /**
     * 查询监督计划类型列表
     * @author thomas
     * @since 2020/9/11 11:05 下午
     * @return java.util.List<cn.fusionfuture.bugu.monitor.vo.MonitorPlanPatternVO>
     **/
    List<MonitorPlanPatternVO> queryMonitorPlanPatter();



}
