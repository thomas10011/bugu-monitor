package cn.fusionfuture.bugu.message.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author LiLan
 * @version 1.0
 * @class LikeVO
 * @description 给打卡点赞
 * @date 2020/8/22 14:11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="LikeVO对象", description="")
public class LikeVO extends ParentPunchVO implements Serializable {


    private static final long serialVersionUID = -6797049384600858512L;

    @ApiModelProperty(value="点赞总数")
    private Integer likeCount;
}
