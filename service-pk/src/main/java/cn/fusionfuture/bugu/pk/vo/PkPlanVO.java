package cn.fusionfuture.bugu.pk.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class PkPlanVO {

    @ApiModelProperty(value = "计划id")
    private Long planId;

    @ApiModelProperty(value = "计划名称")
    private String name;

    @ApiModelProperty(value = "计划详情")
    private String decription;

    @ApiModelProperty(value = "计划封面url")
    private String cover;

    @ApiModelProperty(value = "是否公开发布")
    private Boolean isAnonymouslyPublish;

    @ApiModelProperty(value = "参与人数")
    private Integer pkQuantity;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "持续时间")
    private LocalDateTime lastTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "打卡次数")
    private Integer punchCount;

    @ApiModelProperty(value = "打卡周期")
    private Integer punchCycle;

    @ApiModelProperty(value = "是否上传至布谷金巢")
    private Boolean isUploadBugu;
}