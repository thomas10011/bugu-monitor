package cn.fusionfuture.bugu.pojo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
@ApiModel(value="SmsProduct对象", description="")
public class SmsProduct implements Serializable {

    private static final long serialVersionUID = 2296667269876034495L;

    @ApiModelProperty(value = "雪花算法，自增")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "表示商品创建的时间，时区为东八区")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "表示商品更新的时间，时区为东八区")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "表示商品的名称")
    private String name;

    @ApiModelProperty(value = "表示商品价格，单位为羽毛")
    private BigDecimal price;

    @ApiModelProperty(value = "0表示现金1表示羽毛")
    private Integer currencyType;

    @ApiModelProperty(value = "表示图片存储在oss中的访问url")
    private String imageUrl;

    @ApiModelProperty(value = "表示商品的库存数量")
    private Integer stockQuantity;

    @ApiModelProperty(value = "表示商品的兑换数量")
    private Integer exchangeCount;

    @ApiModelProperty(value = "表示商品的文本描述")
    private String description;

    @ApiModelProperty(value = "1表示已下架0表示未下架")
    @TableLogic
    private Boolean isDeleted;


}
