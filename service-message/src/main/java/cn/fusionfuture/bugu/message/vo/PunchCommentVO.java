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
 * @class PunchCommentVO
 * @description 打卡下的所有评论
 * @date 2020/8/22 15:42
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PunchCommentVO对象", description="")
public class PunchCommentVO extends ParentCommonVO implements Serializable {

    private static final long serialVersionUID = -4259060089600327453L;

    @ApiModelProperty(value = "打卡id")
    private Long punchId;

    @ApiModelProperty(value = "打卡所属计划的类型id")
    private Integer planTypeId;

    @ApiModelProperty(value = "接收评论的用户名称")
    private String receiveUserName;

    @ApiModelProperty(value = "评论内容")
    private String commentContent;

    @ApiModelProperty(value = "父评论id")
    private Long parentId;
}
