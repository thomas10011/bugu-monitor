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
 * @class ICommentVO
 * @description TODO
 * @date 2020/9/30 15:41
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ICommonVO对象", description="输入对象的公共继承类")
public class ICommonVO implements Serializable {


    private static final long serialVersionUID = 566836963150675018L;

    @ApiModelProperty(value = "外键，与用户表相关联")
    private Long sendUserId;

    @ApiModelProperty(value = "外键，与用户表相关联")
    private Long receiveUserId;

}
