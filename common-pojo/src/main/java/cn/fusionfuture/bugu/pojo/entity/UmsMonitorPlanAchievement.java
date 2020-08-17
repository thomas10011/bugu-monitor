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
@ApiModel(value="UmsMonitorPlanAchievement对象", description="")
public class UmsMonitorPlanAchievement implements Serializable {

    private static final long serialVersionUID = -2026775790097465252L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private Long userId;

    @ApiModelProperty(value = "计划总数")
    private Integer planCount;

    @ApiModelProperty(value = "成功总数")
    private Integer successCount;

    @ApiModelProperty(value = "参与人数")
    private Integer participateCount;

    @ApiModelProperty(value = "创建的时间，时区为东八区")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新的时间，时区为东八区")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
