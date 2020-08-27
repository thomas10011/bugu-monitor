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
 * @class CommentVO
 * @description 评论提示
 * @date 2020/8/22 15:30
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CommentVO对象", description="")
public class CommentVO extends ParentPunchVO implements Serializable {

    private static final long serialVersionUID = -5703979969373658421L;

    @ApiModelProperty(value = "评论内容")
    private String commentContent;

    @ApiModelProperty(value = "父评论id")
    private Long parentId;

    @ApiModelProperty(value = "父评论内容")
    private String parentConnent;

    @ApiModelProperty(value = "发出父评论的用户ID")
    private Long parentUserId;

    @ApiModelProperty(value = "发出父评论的用户名称")
    private String parentUserName;

}
