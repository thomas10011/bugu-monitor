package cn.fusionfuture.bugu.pojo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
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
@ApiModel(value="PmsPkPlan对象", description="")
public class PmsPkPlan implements Serializable {

    private static final long serialVersionUID = 3522694942579531042L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建者用户id")
    private Long userId;

    @ApiModelProperty(value = "计划标题")
    private String name;

    @ApiModelProperty(value = "计划描述")
    private String description;

    @ApiModelProperty(value = "pk计划封面图片url")
    private String imageUrl;

    @ApiModelProperty(value = "点赞次数")
    private Integer likeCount;

    @ApiModelProperty(value = "是否匿名发布")
    private Boolean isAnonymouslyPublish;

    @ApiModelProperty(value = "pk模式id")
    private Integer pkPatternId;

    @ApiModelProperty(value = "pk人数")
    private Integer pkQuantity;

    @ApiModelProperty(value = "已报名人数")
    private Integer enrolledQuantity;

    @ApiModelProperty(value = "计划开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "计划持续天数")
    private Integer lastTime;

    @ApiModelProperty(value = "计划结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "打卡总次数")
    private Integer punchQuantity;

    @ApiModelProperty(value = "打卡周期")
    private Integer punchCycle;

    @ApiModelProperty(value = "奖池奖金形式")
    private String bonusType;

    @ApiModelProperty(value = "pk者奖金总数")
    private BigDecimal pkBonus;

    @ApiModelProperty(value = "投票者奖金总数")
    private BigDecimal voteBonus;

    @ApiModelProperty(value = "奖金总数")
    private BigDecimal totalBonus;

    @ApiModelProperty(value = "是否上传至布谷金巢")
    private Boolean isUploadBugu;

    @ApiModelProperty(value = "计划状态id")
    private Integer planStatusId;

    @ApiModelProperty(value = "计划是否删除")
    @TableLogic
    private Boolean isDeleted;


}
