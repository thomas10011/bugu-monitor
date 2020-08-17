package cn.fusionfuture.bugu.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UmsPkPlanAchievement对象", description="")
public class UmsPkPlanAchievement implements Serializable {

    private static final long serialVersionUID = -9182894095248501497L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private Long userId;

    @ApiModelProperty(value = "计划总数")
    private Integer planCount;

    @ApiModelProperty(value = "成功总数（成功 success）：用户在他所有的pk计划中，打卡成功占比超过50%的计划总数。")
    private Integer successCount;

    @ApiModelProperty(value = "战胜人数")
    private Integer defeatCount;

    @ApiModelProperty(value = "胜利总数（胜利 victory）：用户在pk计划中，对于每一次打卡任务中，他完成了打卡任务，此时有人出现没有完成的情况，他就胜利了一次，如果都完成了，就没有胜利。")
    private Integer victoryCount;

    @ApiModelProperty(value = "创建的时间，时区为东八区")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新的时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
