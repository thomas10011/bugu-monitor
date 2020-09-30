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
 * @class IVoteVO
 * @description TODO
 * @date 2020/9/30 16:09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="IVoteVO", description="输入投票对象")
public class IVoteVO extends IPunchVO implements Serializable {

    private static final long serialVersionUID = -7015579079887784481L;

    @ApiModelProperty(value = "表明投票类型是认可还是啄（不认可）")
    private Boolean isApproved;
}
