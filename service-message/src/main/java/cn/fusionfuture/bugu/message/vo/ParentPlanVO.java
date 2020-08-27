package cn.fusionfuture.bugu.message.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author LiLan
 * @version 1.0
 * @class ParentPlanVO
 * @description 父计划VO，定义所有与计划相关的VO都具有的属性，其他计划相关的VO都继承自ParentVO
 * @date 2020/8/22 10:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ParentPlanVO对象", description="")
public class ParentPlanVO extends ParentCommonVO implements Serializable {

    private static final long serialVersionUID = 6777681783908247189L;

    @ApiModelProperty(value = "计划的id")
    private Long planId;

    @ApiModelProperty(value = "计划的模式，如单人监督")
    private String planPattern;

    @ApiModelProperty(value = "计划的名称")
    private String planName;

    @ApiModelProperty(value = "计划的类型id，表明是pk计划还是监督计划，决定去哪一个服务查找数据")
    private Integer planTypeId;

}
