package cn.fusionfuture.bugu.user.vo;

import cn.fusionfuture.bugu.pojo.api.CommonResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author Aki
 * @version 1.0
 * @class UserDertailsVO
 * @description 用户信息
 * @date 2020/9/8 16:46
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserDetailsVO对象", description="")
public class UserDetailsVO {

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "关注数")
    private Integer followQuantity;

    @ApiModelProperty(value = "粉丝数")
    private Integer fansQuantity;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "羽毛数")
    private BigDecimal featherBalance;

    @ApiModelProperty(value = "现金余额")
    private BigDecimal cashBalance;

    @ApiModelProperty(value = "今日羽毛数变化")
    private BigDecimal featherChange;
}
