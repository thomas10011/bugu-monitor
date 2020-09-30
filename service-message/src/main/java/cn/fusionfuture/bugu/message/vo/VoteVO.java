package cn.fusionfuture.bugu.message.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author LiLan
 * @version 1.0
 * @class VoteVO
 * @description 投票VO
 * @date 2020/8/22 14:50
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="VoteVO对象", description="")
public class VoteVO extends ParentPunchVO implements Serializable {
    private static final long serialVersionUID = -90001152778722972L;

    @ApiModelProperty(value="投票总数")
    private Integer voteCount;

    @ApiModelProperty(value="认可/啄一下")
    private Boolean isApproved;

    @ApiModelProperty(value="当前类型的投票数目")
    private Integer currentVoteCount;
}
