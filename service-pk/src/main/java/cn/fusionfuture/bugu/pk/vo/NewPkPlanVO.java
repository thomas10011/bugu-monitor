package cn.fusionfuture.bugu.pk.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zwswl
 * @version 1.0
 * @class PkPlanVO
 * @description TODO
 * @date 2020/9/10 10:30
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PkPlanVO对象", description="创建pk计划传入的对象")
public class NewPkPlanVO {

    @ApiModelProperty(value = "计划的id")
    private Long id;

    @ApiModelProperty(value = "计划标题")
    private String name;

    @ApiModelProperty(value = "创建者用户id")
    private Long userId;

    @ApiModelProperty(value = "计划描述")
    private String description;

    @ApiModelProperty(value = "监督计划封面图片url")
    private String imageUrl;

    @ApiModelProperty(value = "是否匿名发布")
    private Boolean isAnonymouslyPublish;

    @ApiModelProperty(value = "监督模式id")
    private Integer pkPatternId;

    @ApiModelProperty(value = "监督人数")
    private Integer pkQuantity;

    @ApiModelProperty(value = "计划开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "计划持续时间")
    private LocalDateTime lastTime;

    @ApiModelProperty(value = "计划结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "打卡总次数")
    private Integer punchQuantity;

    @ApiModelProperty(value = "打卡周期")
    private Integer punchCycle;

    @ApiModelProperty(value = "奖池奖金形式")
    private Boolean bonusType;

    @ApiModelProperty(value = "监督者奖金总数")
    private BigDecimal monitorBonus;

    @ApiModelProperty(value = "投票者奖金总数")
    private BigDecimal voteBonus;

    @ApiModelProperty(value = "奖金总数")
    private BigDecimal totalBonus;

    @ApiModelProperty(value = "是否上传至布谷金巢")
    private Boolean isUploadBugu;

    @ApiModelProperty(value = "计划状态id")
    private Integer planStatusId;
}