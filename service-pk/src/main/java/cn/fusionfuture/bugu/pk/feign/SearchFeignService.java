package cn.fusionfuture.bugu.pk.feign;

import cn.fusionfuture.bugu.pk.dto.PopularPlanDTO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

/*
 * @author zws
 * @description TODO
 * @create 2020/10/11 15:28
 * @update 2020/10/11 15:28
 * @param
 * @return
 **/
@FeignClient(name = "search-service")
public interface SearchFeignService {

    /*
     * 查询首页展示所需的数据
     * @author zws
     * @since 2020/10/11 15:27
     * @param popularPlanDTO 要保存的信息
     * @return cn.fusionfuture.bugu.pojo.api.CommonResult<?>
     **/
    @PostMapping(value = "popular")
    CommonResult<?> createPopularPlan(@ApiParam(value = "首页计划的dto") @RequestBody PopularPlanDTO popularPlanDTO);

}
