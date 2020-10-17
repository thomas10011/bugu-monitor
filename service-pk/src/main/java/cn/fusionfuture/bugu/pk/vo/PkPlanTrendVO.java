package cn.fusionfuture.bugu.pk.vo;

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
 * @class PkPlanTrendVO
 * @description Pk计划首页动态界面信息
 * @date 2020/10/17 21:23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PkPlanTrendVO", description="pk计划首页动态界面信息")
public class PkPlanTrendVO {

    @ApiModelProperty(name = "用户名",example = "小小")
    private String userName;

    @ApiModelProperty(value = "用户头像url",example = "1")
    private String userImage;

    @ApiModelProperty(value = "计划模式",example = "1")
    private String planPattern;

    @ApiModelProperty(name = "name,计划的标题",example = "每日单词")
    private String name;

    @ApiModelProperty(name = "打卡id",example = "123")
    private Long punchId;

    @ApiModelProperty(value = "打卡内容",example = "1")
    private String content;

    @ApiModelProperty(value = "打卡被点赞次数",example = "1")
    private Integer likeCount;

    @ApiModelProperty(value = "打卡被认可次数",example = "1")
    private Integer agreeCount;

    @ApiModelProperty(value = "打卡被否认次数",example = "1")
    private Integer disagreeCount;

    @ApiModelProperty(value = "受评论次数",example = "1")
    private Integer commentQuantity;

    @ApiModelProperty(value = "打卡图片",example = "1")
    private List<String> imageUrls;

    @ApiModelProperty(value = "打卡时间",example = "1")
    private LocalDateTime punchTime;

    @ApiModelProperty(value = "当前打卡周期",example = "1")
    private Integer currentPunchCycle;

}