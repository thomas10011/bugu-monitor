package cn.fusionfuture.bugu.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SmsProductExchangeRecord对象", description="")
public class SmsProductExchangeRecord implements Serializable {

    private static final long serialVersionUID = -1610028325441688984L;

    @ApiModelProperty(value = "雪花算法，自增")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "表示兑换的时间，时区为东八区")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "表示更新的时间，时区为东八区")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "商品的id")
    private Long productId;

    @ApiModelProperty(value = "商品的名称")
    private String productName;

    @ApiModelProperty(value = "商品图片在oss中访问的url")
    private String imageUrl;

    @ApiModelProperty(value = "用户的id")
    private Long userId;

    @ApiModelProperty(value = "商品兑换的数量")
    private Integer exchangeQuantity;

    @ApiModelProperty(value = "商品单价")
    private BigDecimal productPrice;


}
