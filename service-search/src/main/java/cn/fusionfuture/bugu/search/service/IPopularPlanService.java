package cn.fusionfuture.bugu.search.service;

import cn.fusionfuture.bugu.search.dto.PopularPlanDTO;
import cn.fusionfuture.bugu.search.vo.PopularPlanVO;
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
     * @param planType 选择的计划的类型
     * @param planStatus 选择的监督计划的状态
     * @return void
     * @exception IOException IOException
     **/
    PopularPlanVO queryPopularPlan(String keyWord, Integer pageNum, Integer pageSize, List<String> planType, List<String> planStatus) throws IOException;

    /**
     * 给计划点赞
     * @author thomas
     * @since 2020/9/30 7:27 下午
     * @param pid 计划的id
     **/
    void ratePopularPlan(Long pid) throws IOException;
    /**
     * 创建计划的首页数据
     * @author thomas
     * @since 2020/9/26 4:49 下午
     * @param popularPlanDTO 要保存的信息
     **/
    void createPopularPlan(PopularPlanDTO popularPlanDTO) throws IOException;

}
