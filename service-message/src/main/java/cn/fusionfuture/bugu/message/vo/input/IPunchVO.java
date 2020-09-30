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
 * @class IPunchVO
 * @description TODO
 * @date 2020/9/30 15:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="IPunchVO对象", description="输入打卡对象的公共继承类")
public class IPunchVO extends ICommonVO implements Serializable  {

    @ApiModelProperty(value = "外键，与打卡表相关联")
    private Long punchId;

    @ApiModelProperty(value = "与计划类型表相关联")
    private Integer planTypeId;
}
