package cn.fusionfuture.bugu.pk.vo.plan;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author zws
 * @version 1.0
 * @class SimplePkPlanVO
 * @description TODO
 * @date 2020/9/25 19:29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SimplePkPlanVO对象", description="计划简略信息")
public class SimplePkPlanVO {

    @ApiModelProperty(value = "计划标题",example = "打败抑郁症")
    private String name;

    @ApiModelProperty(value = "pk模式",example = "多人pk")
    private String planPattern;

    @ApiModelProperty(value = "pk人数",example = "5")
    private Integer pkQuantity;

    @ApiModelProperty(value = "已报名人数",example = "3")
    private Integer enrolledQuantity;

}