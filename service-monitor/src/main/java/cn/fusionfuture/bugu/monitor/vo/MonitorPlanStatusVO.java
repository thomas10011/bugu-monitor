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
 * @class MonitorPlanStatusVO
 * @description TODO
 * @date 2020/9/11 10:58 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MonitorPlanRecordVO对象", description="监督计划状态VO")
public class MonitorPlanStatusVO implements Serializable {

    private static final long serialVersionUID = -7186178054085819876L;

    @ApiModelProperty(value = "监督状态id")
    private Integer id;

    @ApiModelProperty(value = "监督状态描述")
    private String description;

}
