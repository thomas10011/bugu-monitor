package cn.fusionfuture.bugu.store.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author thomas
 * @version 1.0
 * @class ProductVO
 * @description TODO
 * @date 2020/9/8 12:03 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ProductVO对象", description="创建商品传入的对象")
public class ProductVO implements Serializable {

    private static final long serialVersionUID = -4123802064543082124L;

    @ApiModelProperty(value = "商品id")
    private Long id;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "图片url")
    private String imageUrl;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "兑换数量")
    private Integer exchangeCount;
}
