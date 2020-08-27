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
 * @class MessageVO
 * @description TODO
 * @date 2020/8/27 10:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PrivateChatVO对象", description="")
public class MessageVO extends ParentCommonVO implements Serializable {

    @ApiModelProperty(value = "接收消息者的名称")
    private String receiveUserName;

    @ApiModelProperty(value = "接收消息者的头像")
    private String receiveAvatarUrl;

    @ApiModelProperty(value = "发送消息的文字内容")
    private String messageContent;

    @ApiModelProperty(value = "发送消息的图片url")
    private String imageUrl;

}
