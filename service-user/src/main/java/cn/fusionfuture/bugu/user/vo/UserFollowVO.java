package cn.fusionfuture.bugu.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author thomas
 * @version 1.0
 * @class UserFollowVO
 * @description TODO
 * @date 2020/9/12 10:01 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserFollowVO对象", description="用户关注列表VO")
public class UserFollowVO implements Serializable {

    private static final long serialVersionUID = -1513812115373053095L;

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "头像url")
    private String avatarUrl;

    @ApiModelProperty(value = "粉丝数量")
    private Integer fansQuantity;

}
