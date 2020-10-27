package cn.fusionfuture.bugu.transaction.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author thomas
 * @version 1.0
 * @class PlanTransactionVO
 * @description TODO
 * @date 2020/10/26 10:34 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BasicMonitorPlanVO", description="查询用户的收支的对象")
public class PlanTransactionVO {

    @ApiModelProperty(value = "计划的id", example = "1318520212345098242")
    private Long id;

    @ApiModelProperty(value = "plan type 计划的类型", example = "多人监督")
    private String ptp;

    @ApiModelProperty(value = "transaction type 收入或支出的类型", example = "监督收入-投票")
    private String ttp;

    @ApiModelProperty(value = "title 计划的标题", example = "#活着，然后打败抑郁症#")
    private String tt;

    @ApiModelProperty(value = "transaction_amount 交易金额", example = "6.66")
    private BigDecimal tm;

    @ApiModelProperty(value = "time，交易时间", example = "1970-1-1T00:00:00:000")
    private String t;


}
