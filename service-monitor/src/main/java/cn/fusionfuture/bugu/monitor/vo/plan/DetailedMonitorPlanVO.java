package cn.fusionfuture.bugu.monitor.vo.plan;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zws
 * @version 1.0
 * @class DetailedMonitorPlanVO
 * @description TODO
 * @date 2020/10/14 18:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DetailedMonitorPlanVO", description="用于显示打卡中的计划详情")
public class DetailedMonitorPlanVO {

    @ApiModelProperty(value = "计划标题",example = "每日背单词")
    private String name;

    @ApiModelProperty(value = "计划图片",example = "http://www.bifengo.com/img/db6dad1b4d4c7e5b0fbbee7d4f2d6b6b83130323.html")
    private String imageUrl;

    @ApiModelProperty(value = "计划描述",example = "每天背至少30个新单词")
    private String description;

    @ApiModelProperty(value = "计划状态描述",example = "报名中")
    private String planStatus;

    @ApiModelProperty(value = "计划类型描述",example = "多人监督")
    private String planPattern;

    @ApiModelProperty(value = "参与监督人数",example = "2")
    private String monitorQuantity;

    @ApiModelProperty(value = "已打卡次数",example = "1")
    private Integer punchCount;

    @ApiModelProperty(value = "打卡总次数",example = "10")
    private Integer punchQuantity;

    @ApiModelProperty(value = "打卡周期",example = "3")
    private Integer punchCycle;

    @ApiModelProperty(value = "监督计划总金额",example = "100")
    private BigDecimal totalBonus;

    @ApiModelProperty(value = "受监督者金额",example = "90")
    private BigDecimal monitorBonus;

    @ApiModelProperty(value = "计划开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "计划结束时间")
    private LocalDateTime endTime;
}