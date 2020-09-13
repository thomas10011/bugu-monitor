package cn.fusionfuture.bugu.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Aki
 * @version 1.0
 * @class WechatBindDetailsVO
 * @description 从微信服务器获取的用户ID
 * @date 2020/9/12 22:01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WechatBindDetails对象", description="")
public class WechatBindDetailsVO {

    @ApiModelProperty(value = "用户的唯一标识")
    private String openid;

    @ApiModelProperty(value = "若存在该用户，则返回该用户的id")
    private String uid;
}
