package cn.fusionfuture.bugu.monitor.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author thomas
 * @version 1.0
 * @class MonitorPlanPatternVO
 * @description TODO
 * @date 2020/9/11 10:57 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MonitorPlanRecordVO对象", description="监督计划类型VO")
public class MonitorPlanPatternVO implements Serializable {

    private static final long serialVersionUID = 5176299205110529712L;

    @ApiModelProperty(value = "监督模式id")
    private Integer id;

    @ApiModelProperty(value = "监督模式描述")
    private String description;

}
