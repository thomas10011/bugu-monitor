package cn.fusionfuture.bugu.monitor.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author thomas
 * @version 1.0
 * @class PopularPlanVO
 * @description TODO
 * @date 2020/9/24 7:47 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PopularPlanDTO", description="首页热门计划所需要的详细信息")
public class PopularPlanDTO implements Serializable {

    private static final long serialVersionUID = 7833407387585455855L;

    @ApiModelProperty(name = "计划的id")
    private Long id;

    @ApiModelProperty(name = "type,计划的类型", notes = "计划的类型。包括：自我监督、单人监督、多人监督、pk计划")
    private String tp;

    @ApiModelProperty(name = "title,计划的标题")
    private String tt;

    @ApiModelProperty(name = "status,计划的状态", notes = "计划的状态，对于监督加护，包含三种状态：报名中、进行中、已完成。对于pk计划，包含三种状态：加入pk、抢单中、已完成。")
    private String st;

    @ApiModelProperty(name = "cover,计划封面图片存储的url", notes = "计划封面图片的相对路径")
    private String cv;

    @ApiModelProperty(name = "avatar,用户头像的url", notes = "用户头像的相对路径")
    private String at;

    @ApiModelProperty(name = "avatars,用户头像列表", notes = "对于pk计划，存储的是参与到pk计划中的用户的头像。对于监督计划，存储的是点赞用户的头像。")
    private List<String> ats;

    @ApiModelProperty(name = "headcount,参与计划的人数")
    private Integer hc;

    @ApiModelProperty(name = "award,奖金总金额")
    private String aw;

    @ApiModelProperty(name = "rates,点赞数")
    private Integer rt;

}
