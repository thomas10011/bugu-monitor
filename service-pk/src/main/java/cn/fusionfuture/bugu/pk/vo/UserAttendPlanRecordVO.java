package cn.fusionfuture.bugu.pk.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author zws
 * @version 1.0
 * @class UserAttendPlanRecordVO
 * @description TODO
 * @date 2020/9/23 10:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserAttendPlanRecordVO", description="用户参加pk计划的记录")
public class UserAttendPlanRecordVO {

    @ApiModelProperty(value = "记录的id")
    private Long id;

    @ApiModelProperty(value = "已打卡次数")
    private Integer punchCount;
}