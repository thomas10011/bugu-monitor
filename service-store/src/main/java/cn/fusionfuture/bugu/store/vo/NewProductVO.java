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
 * @description 创建商品的vo
 * @date 2020/9/7 3:40 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="NewProductVO对象", description="创建商品传入的对象")
public class NewProductVO implements Serializable {

    private static final long serialVersionUID = 459540323011748413L;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "货币类型 0表示现金1表示羽毛")
    private Boolean currencyType;

    @ApiModelProperty(value = "商品图路径")
    private String imageUrl;

    @ApiModelProperty(value = "商品库存数")
    private Integer stockQuantity;

    @ApiModelProperty(value = "商品描述")
    private String description;


}
