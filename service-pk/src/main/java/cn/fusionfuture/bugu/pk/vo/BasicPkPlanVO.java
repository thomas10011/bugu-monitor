package cn.fusionfuture.bugu.pk.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
@ApiModel(value="BasicPkPlanVO", description="BasicPkPlanVO")
public class BasicPkPlanVO implements Serializable {

    private static final long serialVersionUID = 8502311760426262072L;

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

    @ApiModelProperty(value = "打卡总次数")
    private Integer punchQuantity;

    @ApiModelProperty(value = "计划开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

}