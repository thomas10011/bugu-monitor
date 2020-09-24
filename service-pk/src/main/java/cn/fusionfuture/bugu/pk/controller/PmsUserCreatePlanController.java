package cn.fusionfuture.bugu.pk.controller;

import cn.fusionfuture.bugu.pk.service.IPmsUserAttendPlanService;
import cn.fusionfuture.bugu.pk.service.IPmsUserCreatePlanService;
import cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zws
 * @since 2020-09-15
 */
@RestController
@Api(tags = "用户创建计划记录" )
public class PmsUserCreatePlanController {

    @Autowired
    IPmsUserCreatePlanService userCreatePlanService;

    @GetMapping(value = "/pk-plan/create-plan/{uid}")
    @ApiOperation(value = "根据用户id查询用户创建的pk计划")
    PageInfo<BasicPkPlanVO> queryPkUserCreatePlanByUserId(@ApiParam(value = "用户的id") @PathVariable Long uid,
                                                          @ApiParam(value = "查询的页码") @RequestParam(name = "pn") Integer pn,
                                                          @ApiParam(value = "查询的页面大小") @RequestParam(name = "ps") Integer ps) {
        return userCreatePlanService.queryPkUserCreatePlanByUserId(pn,ps,uid);
    }
}