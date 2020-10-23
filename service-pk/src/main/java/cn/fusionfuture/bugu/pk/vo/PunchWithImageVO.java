package cn.fusionfuture.bugu.pk.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author zws
 * @version 1.0
 * @class PunchWithImageVO
 * @description TODO
 * @date 2020/9/26 15:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PunchWithImageVO", description="打卡记录相关VO")
public class PunchWithImageVO {

    @ApiModelProperty(value = "pk计划模式",example = "多人打卡")
    private String planPattern;

    @ApiModelProperty(value = "计划标题",example = "打败抑郁症")
    private String name;

    @ApiModelProperty(value = "打卡内容",example = "小小测试一下")
    private String content;

    @ApiModelProperty(value = "打卡被点赞次数",example = "1")
    private Integer likeCount;

    @ApiModelProperty(value = "打卡被认可次数",example = "1")
    private Integer agreeCount;

    @ApiModelProperty(value = "打卡被否认次数",example = "1")
    private Integer disagreeCount;

    @ApiModelProperty(value = "打卡图片")
    private List<String> imageUrls;
}