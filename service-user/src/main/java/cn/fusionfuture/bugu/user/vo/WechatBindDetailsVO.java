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
 * @description 从后台服务器获取token
 * @date 2020/9/12 22:01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WechatBindDetails对象", description="")
public class WechatBindDetailsVO{

    @ApiModelProperty(value = "uid")
    private String uid;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "refresh_token")
    private String refreshToken;
}
