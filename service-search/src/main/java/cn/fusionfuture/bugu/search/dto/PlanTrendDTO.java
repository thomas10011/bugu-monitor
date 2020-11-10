package cn.fusionfuture.bugu.search.dto;

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
 * @class PlanTrendDTO
 * @description 一条用户动态的具体信息
 * @date 2020/11/9 19:06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PlanTrendDTO", description="动态界面所需要的索引信息")
public class PlanTrendDTO implements Serializable {

    private static final long serialVersionUID = 7833407387585455855L;

    @ApiModelProperty(name = "userId,用户id",example = "1309123491764805634")
    private Long uid;

    @ApiModelProperty(name = "planId,计划id",example = "1318520214740045826")
    private Long pid;

    @ApiModelProperty(name = "punchId,打卡id",example = "1318520214740045826")
    private Long puid;

    @ApiModelProperty(value = "content,打卡内容",example = "今天忘记了！")
    private String ct;

    @ApiModelProperty(value = "likeCount,打卡被点赞次数",example = "1")
    private Integer lc;

    @ApiModelProperty(value = "agreeCount,打卡被认可次数",example = "1")
    private Integer ac;

    @ApiModelProperty(value = "disagreeCount,打卡被否认次数",example = "1")
    private Integer dac;

    @ApiModelProperty(value = "commentQuantity,受评论次数",example = "1")
    private Integer cq;

    @ApiModelProperty(value = "打卡图片url")
    private List<String> imageUrls;

    @ApiModelProperty(value = "punchTime,打卡时间")
    private LocalDateTime pt;

    @ApiModelProperty(value = "currentPunchCycle,当前打卡周期",example = "2")
    private Integer cpc;
}