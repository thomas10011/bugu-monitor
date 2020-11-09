package cn.fusionfuture.bugu.monitor.service;

import cn.fusionfuture.bugu.monitor.dto.PlanForMessageDTO;
import cn.fusionfuture.bugu.monitor.vo.plan.BasicMonitorPlanVO;
import cn.fusionfuture.bugu.monitor.vo.plan.DetailedMonitorPlanVO;
import cn.fusionfuture.bugu.monitor.vo.plan.NewMonitorPlanVO;
import cn.fusionfuture.bugu.monitor.vo.plan.SimpleMonitorPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPlan;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

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
     * 根据计划id查询计划的详细信息（打卡信息界面）
     * @author zws
     * @since 2020/10/14 18:48
     * @param [pid]
     * @return cn.fusionfuture.bugu.monitor.vo.plan.DetailedMonitorPlanVO
     **/
    DetailedMonitorPlanVO queryDetailedMonitorPlanVO(Long pid);

    /*
     * 根据计划id查询计划简单信息
     * @author zws
     * @since 2020/9/26 15:54
     * @param [planId]
     * @return cn.fusionfuture.bugu.monitor.vo.plan.SimpleMonitorPlanVO
     **/
    SimpleMonitorPlanVO querySimpleMonitorPlanVO(Long planId);

    /*
     * 根据计划id定时刷新计划在当前所属周期的打卡情况
     * @author zws
     * @since 2020/10/8 21:52
     * @param [planId]
     * @return void
     **/
    String checkIsPunched(Long planId);

    /*
     * @author zws
     * @description message_service获取计划相关信息
     * @create 2020/10/30 15:14
     * @update 2020/10/30 15:14
     * @param [planId]
     * @return cn.fusionfuture.bugu.monitor.dto.PlanForMessageDTO
     **/
    PlanForMessageDTO getPlanForMessageDTO(Long planId);
}
