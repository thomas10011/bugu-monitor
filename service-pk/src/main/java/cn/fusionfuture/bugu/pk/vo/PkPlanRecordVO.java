package cn.fusionfuture.bugu.pk.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zwswl
 * @version 1.0
 * @class pkPlanRecordVO
 * @description TODO
 * @date 2020/9/10 10:33
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PkPlanRecordVO对象", description="查询pk计划record")
public class PkPlanRecordVO {

    @ApiModelProperty(value = "pk模式id",example = "1")
    private Integer pkPatternId;

    @ApiModelProperty(value = "计划开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "计划持续时间")
    private LocalDateTime lastTime;

    @ApiModelProperty(value = "计划结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "计划标题",example = "打败抑郁症")
    private String pkName;

    @ApiModelProperty(value = "每人奖池数额",example = "10")
    private BigDecimal pkBonus;

    @ApiModelProperty(value = "计划详情",example = "抑郁症什么的不是问题！")
    private String decription;

    @ApiModelProperty(value = "打卡Id",example = "1318520214740045826")
    private Integer punchId;

    @ApiModelProperty(value = "已打卡次数",example = "3")
    private Integer punchCount;

    @ApiModelProperty(value = "打卡状态Id",example = "1")
    private Integer punchStatusId;
}