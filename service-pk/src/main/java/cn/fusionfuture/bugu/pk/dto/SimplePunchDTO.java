package cn.fusionfuture.bugu.pk.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author zws
 * @version 1.0
 * @class SimplePunchDTO
 * @description TODO
 * @date 2020/10/15 20:49
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SimplePunchDTO", description="打卡日历界面的简略打卡信息DTO")
public class SimplePunchDTO {

    @ApiModelProperty(value = "打卡的id",example = "1318520214740045826")
    private Long id;

    @ApiModelProperty(value = "打卡时间")
    private LocalDateTime punchTime;

    @ApiModelProperty(value = "打卡截止时间")
    private LocalDateTime expiredTime;

    @ApiModelProperty(value = "打卡状态",example = "已打卡")
    private String status;
}