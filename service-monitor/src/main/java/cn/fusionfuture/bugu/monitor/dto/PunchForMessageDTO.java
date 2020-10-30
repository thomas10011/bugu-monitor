package cn.fusionfuture.bugu.monitor.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author zws
 * @version 1.0
 * @class PunchForMessageDTO
 * @description TODO
 * @date 2020/10/30 14:48
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PunchForMessageDTO", description="message_service需要的打卡信息")
public class PunchForMessageDTO {
    @ApiModelProperty(value = "计划模式",example = "单人监督")
    private String planPattern;

    @ApiModelProperty(name = "name,计划的标题",example = "每日单词")
    private String name;

    @ApiModelProperty(value = "打卡内容",example = "今天忘记了！")
    private String content;

    @ApiModelProperty(value = "打卡被点赞次数",example = "1")
    private Integer likeCount;

    @ApiModelProperty(value = "打卡被认可次数",example = "1")
    private Integer agreeCount;

    @ApiModelProperty(value = "打卡被否认次数",example = "1")
    private Integer disagreeCount;

    @ApiModelProperty(value = "打卡图片")
    private List<String> imageUrls;
}