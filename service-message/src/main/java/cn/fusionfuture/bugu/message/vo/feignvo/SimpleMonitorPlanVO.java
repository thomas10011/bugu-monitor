package cn.fusionfuture.bugu.message.vo.feignvo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author DELL
 * @version 1.0
 * @class SimpleMonitorPlanVO
 * @description TODO
 * @date 2020/10/7 9:09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SimpleMonitorPlanVO对象", description="计划简略信息")
public class SimpleMonitorPlanVO {

    @ApiModelProperty(value = "计划标题")
    private String name;

    @ApiModelProperty(value = "监督模式")
    private String planPattern;

    @ApiModelProperty(value = "监督人数")
    private Integer monitorQuantity;

}
