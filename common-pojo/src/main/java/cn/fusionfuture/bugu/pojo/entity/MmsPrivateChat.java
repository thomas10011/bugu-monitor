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
@ApiModel(value="MmsPrivateChat对象", description="")
public class MmsPrivateChat implements Serializable {

    private static final long serialVersionUID = -768323669012916733L;

    @ApiModelProperty(value = "自增")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "表示模型创建的时间，时区为东八区")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "表示模型更新的时间，时区为东八区")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "外键，发送私信用户id,与用户表相关联")
    private Long sendUserId;

    @ApiModelProperty(value = "外键，接收私信用户id,与用户表相关联")
    private Long receiveUserId;

    @ApiModelProperty(value = "表明是否被查看")
    private Boolean isChecked;

    @ApiModelProperty(value = "表明是否被隐藏")
    private Boolean isHidden;

    @ApiModelProperty(value = "消息内容")
    private String messageContent;

    @ApiModelProperty(value = "图片url，一次只能发送一张图片")
    private String imageUrl;


}
