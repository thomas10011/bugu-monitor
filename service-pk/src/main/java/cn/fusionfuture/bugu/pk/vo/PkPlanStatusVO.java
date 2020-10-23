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
@ApiModel(value="PkPlanStatusVO对象", description="pk计划状态VO")
public class PkPlanStatusVO implements Serializable {

    private static final long serialVersionUID = -7186178054085819876L;

    @ApiModelProperty(value = "pk计划状态id",example = "1")
    private Integer id;

    @ApiModelProperty(value = "pk计划状态描述",example = "报名中")
    private String description;

}