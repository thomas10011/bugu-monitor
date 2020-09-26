package cn.fusionfuture.bugu.pk.vo;

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

    @ApiModelProperty(value = "计划模式")
    private String planPattern;

    @ApiModelProperty(value = "计划名称")
    private String name;

    @ApiModelProperty(value = "打卡内容")
    private String content;

    @ApiModelProperty(value = "打卡被点赞次数")
    private Integer likeCount;

    @ApiModelProperty(value = "打卡被认可次数")
    private Integer agreeCount;

    @ApiModelProperty(value = "打卡被否认次数")
    private Integer disagreeCount;

//    @ApiModelProperty(value = "打卡图片")
//    private List<String> imageUrls;
}