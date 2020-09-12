package cn.fusionfuture.bugu.pk.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zwswl
 * @version 1.0
 * @class PkPlanStatusVO
 * @description TODO
 * @date 2020/9/12 10:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PkPlanRecordVO对象", description="pk计划状态VO")
public class PkPlanStatusVO implements Serializable {

    private static final long serialVersionUID = -7186178054085819876L;

    @ApiModelProperty(value = "监督状态id")
    private Integer id;

    @ApiModelProperty(value = "监督状态描述")
    private String description;

}