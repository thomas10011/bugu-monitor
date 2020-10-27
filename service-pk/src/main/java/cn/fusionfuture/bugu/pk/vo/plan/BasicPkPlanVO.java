package cn.fusionfuture.bugu.pk.vo.plan;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zwswl
 * @version 1.0
 * @class BasicPkPlanVO
 * @description TODO
 * @date 2020/9/12 10:01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BasicPkPlanVO", description="pk计划部分属性")
public class BasicPkPlanVO implements Serializable {

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

    @ApiModelProperty(value = "报名人数",example = "10")
    private Integer enrolledQuantity;

    @ApiModelProperty(value = "打卡成功次数",example = "2")
    private Integer punchVictoryCount;

    @ApiModelProperty(value = "pk计划单人金额",example = "90")
    private BigDecimal totalBonus;

    @ApiModelProperty(value = "计划开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "计划结束时间")
    private LocalDateTime endTime;

}