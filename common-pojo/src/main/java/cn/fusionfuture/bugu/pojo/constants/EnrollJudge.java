package cn.fusionfuture.bugu.pojo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zws
 * @version 1.0
 * @class EnrollJudge
 * @description 判断报名的情况
 * @date 2020/12/9 14:31
 */
@Getter
@AllArgsConstructor
public class EnrollJudge {

    //报名成功
    public static final Integer SUCCESS =0;

    //人数已满
    public static final Integer POPULATION_IS_FULL =1;

    // 不能报名自己创建的计划
    public static final Integer CAN_NOT_ENROLL_YOURS_PLAN = 2;

    // 报名已截至
    public static final Integer ENROLL_IS_END = 3;

    //不能重复操作
    public static final Integer CAN_NOT_REPEAT = 4;
}