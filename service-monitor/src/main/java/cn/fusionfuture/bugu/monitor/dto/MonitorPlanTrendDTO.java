package cn.fusionfuture.bugu.monitor.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zws
 * @version 1.0
 * @class MonitorPlanTrendDTO
 * @description 获取监督计划动态界面信息
 * @date 2020/10/16 16:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MonitorPlanTrendDTO", description="监督计划首页动态界面信息")
public class MonitorPlanTrendDTO implements Serializable {

    private static final long serialVersionUID = 7833407387585455855L;

    @ApiModelProperty(name = "用户id",example = "1309123491764805634")
    private Long uid;

    @ApiModelProperty(value = "计划模式",example = "自我监督")
    private String planPattern;

    @ApiModelProperty(name = "name,计划的标题",example = "每日单词")
    private String name;


    @ApiModelProperty(name = "计划id",example = "1318520214740045826")
    private Long planId;

    @ApiModelProperty(name = "打卡id",example = "1318520214740045826")
    private Long punchId;

    @ApiModelProperty(value = "打卡内容",example = "今天忘记了！")
    private String content;

    @ApiModelProperty(value = "打卡被点赞次数",example = "0")
    private Integer likeCount;

    @ApiModelProperty(value = "打卡被认可次数",example = "0")
    private Integer agreeCount;

    @ApiModelProperty(value = "打卡被否认次数",example = "0")
    private Integer disagreeCount;

    @ApiModelProperty(value = "受评论次数",example = "0")
    private Integer commentQuantity;

    @ApiModelProperty(value = "打卡图片")
    private List<String> imageUrls;

    @ApiModelProperty(value = "打卡时间")
    private LocalDateTime punchTime;

    @ApiModelProperty(value = "当前打卡周期",example = "1")
    private Integer currentPunchCycle;



}