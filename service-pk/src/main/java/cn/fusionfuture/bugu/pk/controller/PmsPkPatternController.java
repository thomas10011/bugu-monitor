package cn.fusionfuture.bugu.pk.controller;


import cn.fusionfuture.bugu.pk.service.IPmsPkPatternService;
import cn.fusionfuture.bugu.pk.vo.PkPlanPatternVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
@RestController
@Api(tags = "查询pk计划的模式")
public class PmsPkPatternController {

    @Autowired
    IPmsPkPatternService pkPatternService;

    @ApiOperation(value = "输出pk计划的所有计划模式")
    @GetMapping(value = "/pk-plan/pattern")
    public List<PkPlanPatternVO> queryPkPlanPattern() {
        return pkPatternService.queryPkPlanPattern();
    }
}
