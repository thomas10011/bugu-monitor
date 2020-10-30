package cn.fusionfuture.bugu.monitor.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author zws
 * @version 1.0
 * @class PlanForMessageDTO
 * @description TODO
 * @date 2020/10/30 14:48
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PlanForMessageDTO", description="message_service需要的计划信息")
public class PlanForMessageDTO {

    @ApiModelProperty(value = "计划标题",example = "每天背至少40个新单词")
    private String name;

    @ApiModelProperty(value = "计划类型描述",example = "自我监督")
    private String planPattern;

    @ApiModelProperty(value = "监督人数",example = "10")
    private Integer monitorQuantity;
}