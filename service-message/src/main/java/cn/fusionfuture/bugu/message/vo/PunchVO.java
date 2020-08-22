package cn.fusionfuture.bugu.message.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author LiLan
 * @version 1.0
 * @class PunchVO
 * @description TODO
 * @date 2020/8/22 11:16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PunchVO对象", description="")
public class PunchVO extends ParentPlanVO implements Serializable {
}
