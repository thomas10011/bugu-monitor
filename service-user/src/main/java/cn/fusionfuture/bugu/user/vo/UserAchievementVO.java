package cn.fusionfuture.bugu.user.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author thomas
 * @version 1.0
 * @class UserAchievementVO
 * @description TODO
 * @date 2020/10/19 8:30 上午
 */
@Data
@ApiModel(value = "用户成就", description = "用于展示用户的成就")
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAchievementVO {

    @ApiModelProperty(value = "我的监督计划总数, monitor plan count")
    private Integer mpc;

    @ApiModelProperty(value = "我的监督计划成功总数, monitor plan success count")
    private Integer mpsc;

    @ApiModelProperty(value = "我的监督计划参与人数, monitor plan participate count")
    private Integer mppc;

    @ApiModelProperty(value = "我的pk计划总数, pk plan count")
    private Integer ppc;

    @ApiModelProperty(value = "我的pk计划成功次数 pk plan success count")
    private Integer ppsc;

    @ApiModelProperty(value = "我的pk计划胜利次数 pk plan victory count")
    private Integer ppvc;

    @ApiModelProperty(value = "我在pk计划中战胜的人数 pk plan defeat count")
    private Integer ppdc;

    @ApiModelProperty(value = "我监督的计划总数 i monitor plan count")
    private Integer ipc;

    @ApiModelProperty(value = "我监督成功的计划总数 i monitor plan success count")
    private Integer ipsc;

    @ApiModelProperty(value = "我投票总数 i vote count")
    private Integer ivc;

    @ApiModelProperty(value = "我助力人数 i assisted count")
    private Integer iac;

}
