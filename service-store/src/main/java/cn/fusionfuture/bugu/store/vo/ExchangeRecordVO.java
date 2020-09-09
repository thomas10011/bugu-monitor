package cn.fusionfuture.bugu.store.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author thomas
 * @version 1.0
 * @class ExchangeRecordVO
 * @description TODO
 * @date 2020/9/9 9:25 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ExchangeRecordVO对象", description="查询兑换记录VO")
public class ExchangeRecordVO {

    @ApiModelProperty(value = "记录id")
    private Long productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "商品名称")
    private Integer exchangeQuantity;

    @ApiModelProperty(value = "兑换时间")
    private LocalDateTime createTime;

}
