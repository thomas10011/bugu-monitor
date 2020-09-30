package cn.fusionfuture.bugu.message.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author LiLan
 * @version 1.0
 * @class ParentCommonVO
 * @description TODO
 * @date 2020/8/22 16:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ParentCommonVO对象", description="")
public class ParentCommonVO implements Serializable {


    @ApiModelProperty(value = "提醒本身id")
    private Long id;

    @ApiModelProperty(value = "发送消息者的id")
    private Long sendUserId;

    @ApiModelProperty(value = "发送消息者的名称")
    private String sendUserName;

    @ApiModelProperty(value = "发送消息者的头像")
    private String sendAvatarUrl;

    @ApiModelProperty(value = "当前用户id")
    private Long receiveUserId;

    @ApiModelProperty(value = "消息发送时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "表明是否被查看")
    private Boolean isChecked;

    @ApiModelProperty(value = "表明是否被隐藏")
    private Boolean isHidden;
}