package cn.fusionfuture.bugu.monitor.feign;

import cn.fusionfuture.bugu.monitor.dto.PopularPlanDTO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author thomas
 * @version 1.0
 * @class SearchFeignService
 * @description TODO
 * @date 2020/9/26 4:30 下午
 */
@FeignClient(name = "search-service")
public interface SearchFeignService {


    /**
     * 创建计划的首页数据
     * @author thomas
     * @since 2020/9/26 4:49 下午
     * @param popularPlanDTO 要保存的信息
     **/
    @PostMapping(value = "popular")
    CommonResult<?> createPopularPlan(@ApiParam(value = "首页计划的dto") @RequestBody PopularPlanDTO popularPlanDTO);

}
