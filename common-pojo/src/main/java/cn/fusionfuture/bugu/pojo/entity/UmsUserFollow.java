package cn.fusionfuture.bugu.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
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
@ApiModel(value="UmsUserFollow对象", description="")
public class UmsUserFollow implements Serializable {

    private static final long serialVersionUID = 879904891291964608L;

    @ApiModelProperty(value = "自增")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "被关注的用户id")
    private Long followedUserId;

    @ApiModelProperty(value = "关注信息是否有效")
    @TableLogic
    private Boolean isDelete;

    @ApiModelProperty(value = "创建的时间，时区为东八区")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新的时间，时区为东八区")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
