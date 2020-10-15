package cn.fusionfuture.bugu.monitor.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author zws
 * @version 1.0
 * @class SimpleMonitorPlanVO
 * @description TODO
 * @date 2020/9/26 15:49
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SimpleMonitorPlanVO对象", description="供消息微服务调用的计划简略信息")
public class SimpleMonitorPlanVO {

    @ApiModelProperty(value = "计划标题",example = "1")
    private String name;

    @ApiModelProperty(value = "监督模式",example = "1")
    private String planPattern;

    @ApiModelProperty(value = "监督人数",example = "1")
    private Integer monitorQuantity;

}