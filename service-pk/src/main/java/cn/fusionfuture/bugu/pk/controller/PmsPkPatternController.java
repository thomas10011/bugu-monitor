package cn.fusionfuture.bugu.pk.controller;


import cn.fusionfuture.bugu.pk.service.IPmsPkPatternService;
import cn.fusionfuture.bugu.pk.vo.PkPlanPatternVO;
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

public class PmsPkPatternController {

    @Autowired
    IPmsPkPatternService pkPatternService;

    @GetMapping(value = "/pk-plan/pattern")
    public List<PkPlanPatternVO> queryPkPlanPattern() {
        return pkPatternService.queryPkPlanPattern();
    }
}
