package cn.fusionfuture.bugu.monitor.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author thomas
 * @version 1.0
 * @class MonitorPlanVO
 * @description TODO
 * @date 2020/9/11 10:50 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BasicMonitorPlanVO", description="BasicMonitorPlanVO")
public class BasicMonitorPlanVO {

    @ApiModelProperty(value = "计划的id")
    private Long id;

    @ApiModelProperty(value = "计划标题")
    private String name;

    @ApiModelProperty(value = "计划描述")
    private String description;

    @ApiModelProperty(value = "计划状态描述")
    private String planStatus;

    @ApiModelProperty(value = "计划类型描述")
    private String planPattern;

    @ApiModelProperty(value = "已打卡次数")
    private Integer punchCount;

    @ApiModelProperty(value = "打卡总次数")
    private Integer punchQuantity;

    @ApiModelProperty(value = "计划开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

}