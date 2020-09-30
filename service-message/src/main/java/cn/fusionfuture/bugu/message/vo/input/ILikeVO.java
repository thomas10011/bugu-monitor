package cn.fusionfuture.bugu.message.vo.input;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author DELL
 * @version 1.0
 * @class ILikeVO
 * @description TODO
 * @date 2020/9/30 16:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ILikeVO", description="输入点赞对象")
public class ILikeVO extends IPunchVO implements Serializable {
    private static final long serialVersionUID = 2870102002797997068L;
}
