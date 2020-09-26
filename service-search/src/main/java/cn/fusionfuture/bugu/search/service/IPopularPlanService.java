package cn.fusionfuture.bugu.search.service;

import java.io.IOException;

/**
 * @author thomas
 * @version 1.0
 * @class IPopularPlanService
 * @description TODO
 * @date 2020/9/21 5:23 下午
 */
public interface IPopularPlanService {

    /**
     * 查询首页的热门计划
     * @author thomas
     * @since 2020/9/24 8:29 下午
     * @param [keyWord, pageNum, pageSize, monitorPlanType, monitorPlanStatus, pkPlanType, pkPlanStatus]
     * @return void
     **/
    void queryPopularPlan(String keyWord, Integer pageNum, Integer pageSize, String monitorPlanType, String monitorPlanStatus, String pkPlanType, String pkPlanStatus) throws IOException;
}
