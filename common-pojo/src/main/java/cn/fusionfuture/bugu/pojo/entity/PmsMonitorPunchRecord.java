package cn.fusionfuture.bugu.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * <p>
 * 
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel(value="PmsMonitorPunchRecord对象", description="")
public class PmsMonitorPunchRecord implements Serializable {

    private static final long serialVersionUID = -2454479581819224060L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "监督计划id")
    private Long monitorPlanId;

    @ApiModelProperty(value = "单次打卡所得金额")
    private Integer singlePunchBonus;

    @ApiModelProperty(value = "点赞总数")
    private Integer likeCount;

    @ApiModelProperty(value = "评论总数")
    private Integer commentQuantity;

    @ApiModelProperty(value = "认可总数")
    private Integer agreeCount;

    @ApiModelProperty(value = "否认总数")
    private Integer disagreeCount;

    @ApiModelProperty(value = "打卡内容")
    private String content;

    @ApiModelProperty(value = "该条打卡所处的打卡周期")
    private Integer currentPunchCycle;

    @ApiModelProperty(value = "打卡时间")
    private LocalDateTime punchTime;

    @ApiModelProperty(value = "打卡状态")
    private Integer statusId;

    @ApiModelProperty(value = "打卡开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "打卡截止时间")
    private LocalDateTime expiredTime;

}
