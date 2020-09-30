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
 * @class IPrivateChatVO
 * @description TODO
 * @date 2020/9/30 16:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="IPrivateChatVO", description="输入私信对象")
public class IPrivateChatVO extends ICommonVO implements Serializable {
    private static final long serialVersionUID = -6393118701895294072L;

    @ApiModelProperty(value = "消息内容")
    private String messageContent;

    @ApiModelProperty(value = "图片url，一次只能发送一张图片")
    private String imageUrl;
}
