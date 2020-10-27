package cn.fusionfuture.bugu.pk.vo.plan;

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
 * @class MyAchievementPlanVO
 * @description TODO
 * @date 2020/10/28 4:51
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MyAchievementPlanVO", description="我的成就界面计划VO")
public class MyAchievementPlanVO {

    private static final long serialVersionUID = 8502311760426262072L;

    @ApiModelProperty(value = "计划的id", example = "1318520212345098242")
    private Long id;

    @ApiModelProperty(value = "计划标题",example = "每天背至少40个新单词")
    private String name;

    @ApiModelProperty(value = "计划描述",example = "每天背至少40个新单词")
    private String description;

    @ApiModelProperty(value = "计划状态描述",example = "报名中")
    private String planStatus;

    @ApiModelProperty(value = "计划类型描述",example = "多人pk")
    private String planPattern;

    @ApiModelProperty(value = "打卡总次数",example = "10")
    private Integer punchQuantity;

    @ApiModelProperty(value = "打卡成功次数",example = "1")
    private Integer punchVictoryCount;

    @ApiModelProperty(value = "已报名人数",example = "3")
    private Integer enrolledQuantity;

    @ApiModelProperty(value = "pk计划单人金额",example = "90")
    private BigDecimal totalBonus;

    @ApiModelProperty(value = "计划开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "计划结束时间")
    private LocalDateTime endTime;
}