package cn.fusionfuture.bugu.pk.vo;

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
 * @class DetailedPkPlanVO
 * @description TODO
 * @date 2020/10/14 21:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DetailedPkPlanVO", description="用于显示打卡中的计划详情")
public class DetailedPkPlanVO {

    @ApiModelProperty(value = "计划标题")
    private String name;

    @ApiModelProperty(value = "计划图片")
    private String imageUrl;

    @ApiModelProperty(value = "计划描述")
    private String description;

    @ApiModelProperty(value = "计划状态描述")
    private String planStatus;

    @ApiModelProperty(value = "计划类型描述")
    private String planPattern;

    @ApiModelProperty(value = "pk人数")
    private Integer pkQuantity;

    @ApiModelProperty(value = "已报名人数")
    private Integer enrolledQuantity ;

    @ApiModelProperty(value = "已打卡次数")
    private Integer punchCount;

    @ApiModelProperty(value = "打卡总次数")
    private Integer punchQuantity;

    @ApiModelProperty(value = "打卡周期")
    private Integer punchCycle;

    @ApiModelProperty(value = "pk计划总金额")
    private BigDecimal totalBonus;

    @ApiModelProperty(value = "pk者金额")
    private BigDecimal pkBonus;

    @ApiModelProperty(value = "计划开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "计划结束时间")
    private LocalDateTime endTime;
}