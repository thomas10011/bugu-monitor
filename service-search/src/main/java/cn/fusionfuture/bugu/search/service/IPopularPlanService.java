package cn.fusionfuture.bugu.search.service;

import cn.hutool.json.JSONObject;

import java.io.IOException;
import java.util.List;

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
     * @param keyWord 查询的关键词
     * @param pageNum 分页的页码
     * @param pageSize 页面的大小
     * @param monitorPlanType 选择的监督计划的类型
     * @param monitorPlanStatus 选择的监督计划的状态
     * @param pkPlanType 环责的pk计划的类型
     * @param pkPlanStatus 选择的pk计划的状态
     * @return void
     **/
    List<JSONObject> queryPopularPlan(String keyWord, Integer pageNum, Integer pageSize, String monitorPlanType, String monitorPlanStatus, String pkPlanType, String pkPlanStatus) throws IOException;
}
