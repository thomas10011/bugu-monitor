package cn.fusionfuture.bugu.monitor.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author zws
 * @version 1.0
 * @class BasicPunchVO
 * @description TODO
 * @date 2020/9/26 16:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BasicPunchVO", description="打卡相关信息")
public class BasicPunchVO {


    @ApiModelProperty(name = "打卡id",example = "1318520214740045826")
    private Long id;

    @ApiModelProperty(value = "计划模式",example = "单人监督")
    private String planPattern;

    @ApiModelProperty(value = "计划名称",example = "每日背单词")
    private String name;

    @ApiModelProperty(value = "打卡内容",example = "今天忘记了！")
    private String content;

    @ApiModelProperty(value = "打卡被点赞次数",example = "1")
    private Integer likeCount;

    @ApiModelProperty(value = "打卡被认可次数",example = "1")
    private Integer agreeCount;

    @ApiModelProperty(value = "打卡被否认次数",example = "1")
    private Integer disagreeCount;

    @ApiModelProperty(value = "打卡受评论次数",example = "1")
    private Integer commentQuantity;

    @ApiModelProperty(value = "打卡图片")
    private List<String> imageUrls;
}