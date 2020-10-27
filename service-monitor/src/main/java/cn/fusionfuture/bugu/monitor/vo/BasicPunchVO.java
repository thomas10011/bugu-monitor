package cn.fusionfuture.bugu.monitor.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zws
 * @version 1.0
 * @class BasicPunchVO
 * @description 打卡日历下面的打卡信息
 * @date 2020/9/26 16:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BasicPunchVO", description="打卡相关信息")
public class BasicPunchVO {

    @ApiModelProperty(name = "打卡id",example = "1318520214740045826")
    private Long id;

    @ApiModelProperty(value = "计划名称",example = "每日背单词")
    private String name;

    @ApiModelProperty(value = "打卡内容",example = "今天忘记了！")
    private String content;

    @ApiModelProperty(value = "打卡时间")
    private LocalDateTime punchTime;

    @ApiModelProperty(value = "当前打卡所处的打卡周期")
    private Integer currentPunchCycle;

    @ApiModelProperty(value = "打卡图片")
    private List<String> imageUrls;
}