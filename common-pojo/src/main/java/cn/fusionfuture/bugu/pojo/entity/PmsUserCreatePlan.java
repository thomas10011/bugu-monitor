package cn.fusionfuture.bugu.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
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
 * @since 2020-08-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PmsUserPkPlan对象", description="")
public class PmsUserCreatePlan extends Wrapper<PmsUserCreatePlan> implements Serializable {

    private static final long serialVersionUID = -2171642560092898188L;

    @ApiModelProperty(value = "表示一条用户创建计划的记录")
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

    @ApiModelProperty(value = "pk计划id")
    private Long pkPlanId;

    @ApiModelProperty(value = "需要打卡的总次数")
    private Integer punchQuantity;

    @ApiModelProperty(value = "打卡次数")
    private Integer punchCount;

    @ApiModelProperty(value = "打卡成功次数")
    private Integer punchVictoryCount;

    @ApiModelProperty(value = "计划是否成功")
    private Integer isSuccess;


    @Override
    public PmsUserCreatePlan getEntity() {
        return null;
    }

    @Override
    public MergeSegments getExpression() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public String getSqlSegment() {
        return null;
    }

}