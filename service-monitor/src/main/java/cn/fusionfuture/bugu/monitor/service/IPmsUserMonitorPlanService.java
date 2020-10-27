package cn.fusionfuture.bugu.monitor.service;

import cn.fusionfuture.bugu.monitor.vo.plan.MyAchievementPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsUserMonitorPlan;
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
public interface IPmsUserMonitorPlanService extends IService<PmsUserMonitorPlan> {

    /**
     * 分页查询用户参与的监督计划
     * @author thomas
     * @since 2020/9/12 11:32 上午
     * @param pn 页码编号
     * @param ps 页面大小
     * @param uid 用户id
     * @return com.github.pagehelper.PageInfo<cn.fusionfuture.bugu.monitor.vo.plan.BasicMonitorPlanVO>
     **/
    PageInfo<MyAchievementPlanVO> queryMonitorPlanByUserId(Integer pn, Integer ps, Long uid);
}
