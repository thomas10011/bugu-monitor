package cn.fusionfuture.bugu.pk.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

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

    @ApiModelProperty(value = "记录的id",example = "1315187541535731714")
    private Long id;

    @ApiModelProperty(value = "已打卡次数",example = "1")
    private Integer punchCount;

}