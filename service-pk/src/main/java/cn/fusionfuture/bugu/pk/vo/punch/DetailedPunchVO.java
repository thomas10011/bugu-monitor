package cn.fusionfuture.bugu.pk.vo.punch;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zws
 * @version 1.0
 * @class DetailedPunchVO
 * @description TODO
 * @date 2020/10/27 15:33
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DetailedPunchVO", description="打卡详情页面的打卡信息")
public class DetailedPunchVO {

    @ApiModelProperty(name = "用户名",example = "小小")
    private String userName;

    @ApiModelProperty(value = "用户头像url",example = "http://www.bifengo.com/img/db6dad1b4d4c7e5b0fbbee7d4f2d6b6b83130323.html")
    private String userImage;

    @ApiModelProperty(value = "计划模式",example = "单人监督")
    private String planPattern;

    @ApiModelProperty(name = "name,计划的标题",example = "每日单词")
    private String name;

    @ApiModelProperty(name = "打卡id",example = "1318520214740045826")
    private Long punchId;

    @ApiModelProperty(value = "打卡内容",example = "今天忘记了！")
    private String content;

    @ApiModelProperty(value = "打卡被点赞次数",example = "1")
    private Integer likeCount;

    @ApiModelProperty(value = "打卡被认可次数",example = "1")
    private Integer agreeCount;

    @ApiModelProperty(value = "打卡被否认次数",example = "1")
    private Integer disagreeCount;

    @ApiModelProperty(value = "受评论次数",example = "1")
    private Integer commentQuantity;

    @ApiModelProperty(value = "打卡图片")
    private List<String> imageUrls;

    @ApiModelProperty(value = "打卡时间")
    private LocalDateTime punchTime;

    @ApiModelProperty(value = "当前打卡周期",example = "2")
    private Integer currentPunchCycle;
}