package cn.fusionfuture.bugu.monitor.vo.plan;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author zwswl
 * @version 1.0
 * @class MonitorPlanVO
 * @description TODO
 * @date 2020/9/9 11:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MonitorPlanVO对象", description="创建一个监督计划")
public class NewMonitorPlanVO {

    @ApiModelProperty(value = "计划标题",example = "每日背单词")
    private String name;

    @ApiModelProperty(value = "创建者用户id",example = "1309123491764805634")
    private Long userId;

    @ApiModelProperty(value = "计划描述",example = "http://www.bifengo.com/img/db6dad1b4d4c7e5b0fbbee7d4f2d6b6b83130323.html")
    private String description;

    @ApiModelProperty(value = "监督计划封面图片url",example = "每天背至少30个新单词")
    private String imageUrl;

    @ApiModelProperty(value = "是否匿名发布")
    private Boolean isAnonymouslyPublish;

    @ApiModelProperty(value = "监督模式id",example = "3")
    private Integer monitorPatternId;

    @ApiModelProperty(value = "监督人数",example = "2")
    private Integer monitorQuantity;

    @ApiModelProperty(value = "计划开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "计划持续时间")
    private LocalDateTime lastTime;

    @ApiModelProperty(value = "计划结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "打卡总次数",example = "10")
    private Integer punchQuantity;

    @ApiModelProperty(value = "打卡周期",example = "3")
    private Integer punchCycle;

    @ApiModelProperty(value = "奖池奖金形式")
    private Boolean bonusType;

    @ApiModelProperty(value = "监督者奖金总数",example = "90")
    private BigDecimal monitorBonus;

    @ApiModelProperty(value = "投票者奖金总数",example = "10")
    private BigDecimal voteBonus;

    @ApiModelProperty(value = "奖金总数",example = "100")
    private BigDecimal totalBonus;

    @ApiModelProperty(value = "是否上传至布谷金巢")
    private Boolean isUploadBugu;

    @ApiModelProperty(value = "计划状态id",example = "2")
    private Integer planStatusId;
}