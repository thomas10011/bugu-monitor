package cn.fusionfuture.bugu.message.vo.feignvo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author DELL
 * @version 1.0
 * @class BasicPunchVO
 * @description TODO
 * @date 2020/10/7 9:09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BasicPunchVO", description="打卡相关信息")
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

    @ApiModelProperty(value = "打卡图片")
    private List<String> imageUrls;
}
