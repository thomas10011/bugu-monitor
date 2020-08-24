package cn.fusionfuture.bugu.message.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EnrollVO对象", description="")
public class EnrollVO extends ParentPlanVO implements Serializable  {
    private static final long serialVersionUID = -609962923751712596L;

    @ApiModelProperty(value = "最大报名人数")
    private Integer maxEnrollQuantity;

    @ApiModelProperty(value = "当前报名人数")
    private Integer currentEnrollCount;

}

