package cn.fusionfuture.bugu.pk.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zws
 * @version 1.0
 * @class PlanForMessageDTO
 * @description TODO
 * @date 2020/10/30 14:28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PlanForMessageDTO", description="message_service需要的计划信息")
public class PlanForMessageDTO {

    private static final long serialVersionUID = 8502311760426262072L;

    @ApiModelProperty(value = "计划标题",example = "每天背至少40个新单词")
    private String name;

    @ApiModelProperty(value = "计划类型描述",example = "多人pk")
    private String planPattern;

    @ApiModelProperty(value = "报名人数",example = "10")
    private Integer enrolledQuantity;

    @ApiModelProperty(value = "pk人数",example = "10")
    private Integer pkQuantity;

}