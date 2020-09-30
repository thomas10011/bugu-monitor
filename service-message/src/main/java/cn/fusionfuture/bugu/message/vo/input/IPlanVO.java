package cn.fusionfuture.bugu.message.vo.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author DELL
 * @version 1.0
 * @class IPlanVO
 * @description TODO
 * @date 2020/9/30 15:49
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="IPlanVO对象", description="输入计划对象的公共继承类")
public class IPlanVO extends ICommonVO implements Serializable {

    @ApiModelProperty(value = "外键，与计划表相关联")
    private Long planId;

    @ApiModelProperty(value = "与计划类型表相关联")
    private Integer planTypeId;
}
