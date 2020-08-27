package cn.fusionfuture.bugu.message.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author LiLan
 * @version 1.0
 * @class ParentPunchVO
 * @description TODO
 * @date 2020/8/22 10:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ParentPunchVO对象", description="")
public class ParentPunchVO extends ParentCommonVO implements Serializable {

    private static final long serialVersionUID = 6345826325975237230L;

    @ApiModelProperty(value = "打卡的id")
    private Long punchId;

    @ApiModelProperty(value = "计划的模式，如单人监督")
    private String planPattern;

    @ApiModelProperty(value = "计划的名称")
    private String planName;

    @ApiModelProperty(value = "打卡的文字内容")
    private String punchContent;

    @ApiModelProperty(value = "打卡的第一张图片内容")
    private String punchImageUrl;

    @ApiModelProperty(value = "计划的类型id，表明是pk计划还是监督计划，决定去哪一个服务查找数据")
    private Integer planTypeId;

}
