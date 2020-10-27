package cn.fusionfuture.bugu.monitor.vo.plan;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author thomas
 * @version 1.0
 * @class MonitorPlanVO
 * @description TODO
 * @date 2020/9/11 10:50 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BasicMonitorPlanVO", description="按照用户id查询相关监督计划")
public class BasicMonitorPlanVO {

    @ApiModelProperty(value = "计划的id", example = "1318520212345098242")
    private Long id;

    @ApiModelProperty(value = "计划标题",example = "每日背单词")
    private String name;

    @ApiModelProperty(value = "计划描述",example = "每天背至少40个新单词")
    private String description;

    @ApiModelProperty(value = "计划状态描述",example = "报名中")
    private String planStatus;

    @ApiModelProperty(value = "计划类型描述",example = "自我监督")
    private String planPattern;

    @ApiModelProperty(value = "监督计划单人金额",example = "100")
    private BigDecimal totalBonus;

    @ApiModelProperty(value = "已打卡次数",example = "1")
    private Integer punchCount;

    @ApiModelProperty(value = "打卡总次数",example = "10")
    private Integer punchQuantity;

    @ApiModelProperty(value = "计划开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

}
