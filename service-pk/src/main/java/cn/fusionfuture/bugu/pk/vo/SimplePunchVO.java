package cn.fusionfuture.bugu.pk.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author zws
 * @version 1.0
 * @class SimplePunchVO
 * @description TODO
 * @date 2020/10/15 20:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SimplePunchVO", description="打卡日历界面的简略打卡信息")
public class SimplePunchVO {

    @ApiModelProperty(value = "打卡的id",example = "1")
    private Long id;

    @ApiModelProperty(value = "打卡时间",example = "1")
    private LocalDateTime punchTime;

    @ApiModelProperty(value = "打卡状态",example = "1")
    private String punchStatus;
}