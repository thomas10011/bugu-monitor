package cn.fusionfuture.bugu.pojo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="TmsPlanTransaction对象", description="")
public class TmsPlanTransaction implements Serializable {

    private static final long serialVersionUID = 1892653115510250286L;

    @ApiModelProperty(value = "主键，自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "表示订单创建的时间，时区为东八区")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "表示订单更新的时间，时区为东八区")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "订单描述")
    private String description;

    @ApiModelProperty(value = "订单完成时间，可以为空")
    private LocalDateTime completeTime;

    @ApiModelProperty(value = "交易金额")
    private BigDecimal transactionAmount;

    @ApiModelProperty(value = "交易前余额")
    private BigDecimal beforeBalance;

    @ApiModelProperty(value = "交易后余额")
    private BigDecimal afterBalance;

    @ApiModelProperty(value = "金额类型0表示现金1表示羽毛")
    private Boolean currencyType;

    @ApiModelProperty(value = "订单所对应计划的id")
    private Long planId;

    @ApiModelProperty(value = "支付方式的类型")
    private Integer paymentTypeId;

    @ApiModelProperty(value = "表示计划的类型")
    private Integer planTypeId;

    @ApiModelProperty(value = "表示交易的类型，如制定计划、参与计划等")
    private Integer transactionTypeId;

    @ApiModelProperty(value = "用户id")
    private Long userId;


}
