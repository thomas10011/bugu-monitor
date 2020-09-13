package cn.fusionfuture.bugu.pojo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
@ApiModel(value="UmsUser对象", description="")
public class UmsUser implements Serializable {

    private static final long serialVersionUID = 8300421039048454007L;

    @ApiModelProperty(value = "自增")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "用户名（前端约束在20个字符内）")
    private String userName;

    @ApiModelProperty(value = "用户密码的哈希值")
    private String password;

    @ApiModelProperty(value = "用户绑定的电话号码")
    private String phone;

    @ApiModelProperty(value = "用户绑定的邮箱")
    private String email;

    @ApiModelProperty(value = "用户性别")
    private Integer gender;

    @ApiModelProperty(value = "用户生日")
    private LocalDate birthday;

    @ApiModelProperty(value = "用户头像")
    private String avatarUrl;

    @ApiModelProperty(value = "用户所持有的羽毛数")
    private BigDecimal featherBalance;

    @ApiModelProperty(value = "用户钱包中的余额")
    private BigDecimal cashBalance;

    @ApiModelProperty(value = "用户主动关注其他用户的总数")
    private Integer followQuantity;

    @ApiModelProperty(value = "用户被其他用户关注的总数")
    private Integer fansQuantity;

    @ApiModelProperty(value = "用户是否已经注销账户")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "表示用户的权限，0为系统管理员，1为运维管理员，2为普通用户")
    private Integer privilege;

    @ApiModelProperty(value = "创建的时间，时区为东八区")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新的时间，时区为东八区")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
