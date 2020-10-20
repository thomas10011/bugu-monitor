package cn.fusionfuture.bugu.search.feign;

import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.search.vo.PlanTrendVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/*
 * @author zws
 * @description feign调用pk-service
 * @create 2020/10/20 17:15
 * @update 2020/10/20 17:15
 * @param
 * @return
 **/
@FeignClient(value = "pk-service")
public interface PkFeignService {

    @ApiOperation(value = "根据用户id查询打卡相关信息")
    @GetMapping(value = "/plan/trend/{uid}")
    CommonResult<List<PlanTrendVO>> querypkPlanTrendVO(@Validated
                                                  @ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid);

}
