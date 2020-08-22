package cn.fusionfuture.bugu.message.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author LiLan
 * @version 1.0
 * @class ParentPunchVO
 * @description TODO
 * @date 2020/8/22 10:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ParentPunchVO对象", description="")
public class ParentPunchVO implements Serializable {

    private static final long serialVersionUID = 6345826325975237230L;

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

    @ApiModelProperty(value = "打卡的id")
    private Long punchId;

    @ApiModelProperty(value = "计划的模式，如单人监督")
    private String planPattern;

    @ApiModelProperty(value = "计划的名称")
    private String planName;

    @ApiModelProperty(value = "打卡的文字内容")
    private String punchContent;

    @ApiModelProperty(value = "打卡的第一张图片内容")
    private String punchImageUrl;

    @ApiModelProperty(value = "计划的类型id，表明是pk计划还是监督计划，决定去哪一个服务查找数据")
    private Integer planTypeId;

    @ApiModelProperty(value = "消息发送时间")
    private LocalDateTime SendTime;

    @ApiModelProperty(value = "表明是否被查看")
    private Boolean isChecked;

    @ApiModelProperty(value = "表明是否被隐藏")
    private Boolean isHidden;
}
