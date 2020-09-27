package cn.fusionfuture.bugu.monitor.service;

import cn.fusionfuture.bugu.monitor.vo.BasicMonitorPlanVO;
import cn.fusionfuture.bugu.monitor.vo.NewMonitorPlanVO;
import cn.fusionfuture.bugu.monitor.vo.SimpleMonitorPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPlan;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

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
     * 分页查询查询用户的所有监督计划
     * @author thomas
     * @since 2020/9/12 1:36 上午
     * @param pn 当前页码
     * @param ps 页面大小
     * @param uid 用户id
     * @return List<BasicMonitorPlanVO>
     **/
    PageInfo<BasicMonitorPlanVO> queryBasicMonitorPlanVO(Integer pn, Integer ps, Long uid);

    /*
     * TODO 根据计划id查询计划简单信息
     * @author zws
     * @since 2020/9/26 15:54
     * @param [planId]
     * @return cn.fusionfuture.bugu.monitor.vo.SimpleMonitorPlanVO
     **/
    SimpleMonitorPlanVO querySimpleMonitorPlanVO(Long planId);
}
