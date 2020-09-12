package cn.fusionfuture.bugu.monitor.vo;

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
 * @class MonitorPlanRecordVO
 * @description TODO
 * @date 2020/9/10 10:17
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MonitorPlanRecordVO对象", description="查询监督计划record")
public class MonitorPlanRecordVO {

    @ApiModelProperty(value = "监督模式id")
    private Integer monitorPatternId;

    @ApiModelProperty(value = "计划开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "计划持续时间")
    private LocalDateTime lastTime;

    @ApiModelProperty(value = "计划结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "计划名称")
    private String monitorName;

    @ApiModelProperty(value = "每人奖池数额")
    private BigDecimal monitorBonus;

    @ApiModelProperty(value = "计划详情")
    private String description;

    @ApiModelProperty(value = "打卡Id")
    private Integer punchId;

    @ApiModelProperty(value = "打卡次数")
    private Integer punchCount;

    @ApiModelProperty(value = "打卡状态Id")
    private Integer punchStatusId;
}