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
 * @class IEnrollVO
 * @description TODO
 * @date 2020/9/30 15:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="IEnrollVO", description="输入报名对象")
public class IEnrollVO extends IPlanVO implements Serializable {
    private static final long serialVersionUID = 793528372854707665L;

    @ApiModelProperty(value = "与报名类型表相关联")
    private Integer enrollTypeId;
}
