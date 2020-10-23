package cn.fusionfuture.bugu.pk.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zws
 * @version 1.0
 * @class PkPlanTrendDTO
 * @description Pk计划首页动态界面信息
 * @date 2020/10/17 21:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PkPlanTrendDTO", description="Pk计划首页动态界面信息")
public class PkPlanTrendDTO implements Serializable {

    private static final long serialVersionUID = 7833407387585455855L;

    @ApiModelProperty(name = "用户id",example = "1309123491764805634")
    private Long uid;

    @ApiModelProperty(value = "计划模式",example = "多人pk")
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