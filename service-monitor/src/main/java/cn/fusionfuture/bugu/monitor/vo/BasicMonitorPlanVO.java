package cn.fusionfuture.bugu.monitor.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
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
@ApiModel(value="BasicMonitorPlanVO", description="按照用户id查询相关监督计划")
public class BasicMonitorPlanVO {

    @ApiModelProperty(value = "计划的id",example = "1")
    private Long id;

    @ApiModelProperty(value = "计划标题",example = "1")
    private String name;

    @ApiModelProperty(value = "计划描述",example = "1")
    private String description;

    @ApiModelProperty(value = "计划状态描述",example = "1")
    private String planStatus;

    @ApiModelProperty(value = "计划类型描述",example = "1")
    private String planPattern;

    @ApiModelProperty(value = "监督计划单人金额",example = "1")
    private BigDecimal totalBonus;

    @ApiModelProperty(value = "已打卡次数",example = "1")
    private Integer punchCount;

    @ApiModelProperty(value = "打卡总次数",example = "1")
    private Integer punchQuantity;

    @ApiModelProperty(value = "计划开始时间",example = "1")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间",example = "1")
    private LocalDateTime endTime;

}
