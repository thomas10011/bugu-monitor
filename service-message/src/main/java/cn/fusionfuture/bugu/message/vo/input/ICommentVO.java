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
 * @date 2020/9/30 15:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ICommentVO对象", description="输入对象评论提醒类")
public class ICommentVO extends IPunchVO implements Serializable {


    private static final long serialVersionUID = 345084650824286172L;

    @ApiModelProperty(value = "评论内容")
    private String commentContent;

    @ApiModelProperty(value = "为评论回复而设计，如果是第一个评论则该值为NULL")
    private Long parentId;
}
