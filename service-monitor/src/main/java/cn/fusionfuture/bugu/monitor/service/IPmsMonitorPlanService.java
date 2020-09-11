package cn.fusionfuture.bugu.monitor.service;

import cn.fusionfuture.bugu.monitor.vo.BasicMonitorPlanVO;
import cn.fusionfuture.bugu.monitor.vo.NewMonitorPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPlan;
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
public interface IPmsMonitorPlanService extends IService<PmsMonitorPlan> {

    /**
     * 创建监督计划接口
     * @author thomas
     * @since 2020/9/11 11:24 下午
     * @param newMonitorPlanVO 传入的VO对象
     * @return java.lang.Long
     **/
    Long createMonitorPlan(NewMonitorPlanVO newMonitorPlanVO);

    /**
     * 查询用户的所有监督计划
     * @author thomas
     * @since 2020/9/12 1:36 上午
     * @param []
     * @return List<BasicMonitorPlanVO>
     **/
    List<BasicMonitorPlanVO> queryBasicMonitorPlanVO(Long uid);

}
