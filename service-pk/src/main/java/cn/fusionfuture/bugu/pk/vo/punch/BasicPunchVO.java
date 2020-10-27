package cn.fusionfuture.bugu.pk.vo.punch;

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
 * @description TODO
 * @date 2020/9/25 19:51
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BasicPunchVO", description="打卡记录相关VO")
public class BasicPunchVO {

    @ApiModelProperty(name = "打卡id",example = "1318520213938933761")
    private Long id;

    @ApiModelProperty(value = "计划名称",example = "每日背单词")
    private String name;

    @ApiModelProperty(value = "打卡内容",example = "今天忘记了！")
    private String content;

    @ApiModelProperty(value = "打卡时间")
    private LocalDateTime punchTime;

    @ApiModelProperty(value = "当前打卡所处的打卡周期")
    private Integer currentPunchCycle;

}