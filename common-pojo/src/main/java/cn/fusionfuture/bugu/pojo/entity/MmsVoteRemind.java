package cn.fusionfuture.bugu.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="MmsVoteRemind对象", description="")
public class MmsVoteRemind implements Serializable {

    private static final long serialVersionUID = 682669101434352887L;

    @ApiModelProperty(value = "自增")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "表示模型创建的时间，时区为东八区")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "表示模型更新的时间，时区为东八区")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "外键，与用户表相关联")
    private Long sendUserId;

    @ApiModelProperty(value = "外键，与用户表相关联")
    private Long receiveUserId;

    @ApiModelProperty(value = "外键，与打卡表相关联")
    private Long punchId;

    @ApiModelProperty(value = "与计划类型表相关联")
    private Integer planTypeId;

    @ApiModelProperty(value = "表明投票类型是认可还是啄（不认可）")
    private Boolean isApproved;

    @ApiModelProperty(value = "表明是否被查看")
    private Boolean isChecked;

    @ApiModelProperty(value = "表明是否被隐藏")
    private Boolean isHidden;


}
