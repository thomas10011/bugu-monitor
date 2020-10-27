package cn.fusionfuture.bugu.transaction.controller;


import cn.fusionfuture.bugu.transaction.service.ITmsPlanTransactionService;
import cn.fusionfuture.bugu.transaction.vo.PlanTransactionVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>                                                                                                                                                                                                                                                                                                                                                                                                                                                     
 * @author thomas
 * @since 2020-08-17
 */
@RestController
@Api(tags = "计划相关交易记录查询")
public class TmsPlanTransactionController {

    @Autowired
    ITmsPlanTransactionService planTransactionService;

    @ApiOperation(value = "分页查询羽毛支出")
    @GetMapping(value = "/feather/expense/{uid}")
    public PageInfo<PlanTransactionVO> queryFeatherExpenseByPage(@ApiParam(value = "用户id") @NonNull @PathVariable(value = "uid") Long uid,
                                                                 @ApiParam(value = "页码编号") @RequestParam(name = "pn", defaultValue = "1") Integer pn,
                                                                 @ApiParam(value = "页面大小") @RequestParam(name = "ps", defaultValue = "5") Integer ps) {
        return planTransactionService.queryFeatherExpenseByPage(uid, pn, ps);
    }

    @ApiOperation(value = "分页查询羽毛收入")
    @GetMapping(value = "/feather/income/{uid}")
    public PageInfo<PlanTransactionVO>  queryFeatherIncomeByPage(@ApiParam(value = "用户id") @NonNull @PathVariable(value = "uid") Long uid,
                                            @ApiParam(value = "页码编号") @RequestParam(name = "pn", defaultValue = "1") Integer pn,
                                            @ApiParam(value = "页面大小") @RequestParam(name = "ps", defaultValue = "5") Integer ps) {
        return planTransactionService.queryFeatherIncomeByPage(uid, pn, ps);
    }

}
