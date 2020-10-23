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
 * @class PkPlanPatternVO
 * @description TODO
 * @date 2020/9/12 10:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PkPlanPatternVO对象", description="pk模式VO")
public class PkPlanPatternVO implements Serializable {

    private static final long serialVersionUID = 5176299205110529712L;

    @ApiModelProperty(value = "pk模式id",example = "1")
    private Integer id;

    @ApiModelProperty(value = "pk模式描述",example = "双人pk")
    private String description;

}
