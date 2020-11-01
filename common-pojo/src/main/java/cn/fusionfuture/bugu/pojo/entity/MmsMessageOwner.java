package cn.fusionfuture.bugu.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author DELL
 * @version 1.0
 * @class MmsMessageOwner
 * @description TODO
 * @date 2020/10/31 17:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MmsMessageOwner对象", description="用于表明消息实际所在用户中的状态")
public class MmsMessageOwner implements Serializable {
    private static final long serialVersionUID = -6454910691385077421L;

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
    private Long ownerId;

    @ApiModelProperty(value = "外键，与用户表相关联")
    private Long otherId;

    @ApiModelProperty(value = "外键，与私信表中的消息相关联")
    private Long messageId;

    @ApiModelProperty(value = "表明是否被查看")
    @TableField(fill = FieldFill.INSERT)
    private Boolean isChecked;

    @ApiModelProperty(value = "表明是否被隐藏")
    @TableField(fill = FieldFill.INSERT)
    private Boolean isHidden;
}
