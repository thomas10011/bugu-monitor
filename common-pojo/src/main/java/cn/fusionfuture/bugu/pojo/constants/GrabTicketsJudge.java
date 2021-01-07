package cn.fusionfuture.bugu.pojo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zws
 * @version 1.0
 * @class GrabTicketsJudge
 * @description 判断抢票的情况
 * @date 2020/12/9 14:31
 */
@Getter
@AllArgsConstructor
public class GrabTicketsJudge {
    //抢票成功
    public static final Integer SUCCESS =0;

    //人数已满
    public static final Integer POPULATION_IS_FULL =1;

    // 不能对自己创建的计划抢票
    public static final Integer CAN_NOT_GRAB_TICKET_FOR_YOURS_PLAN = 2;

    // 抢票已截至
    public static final Integer GRAB_TICKET_IS_END = 3;

    //不能重复操作
    public static final Integer CAN_NOT_REPEAT = 4;
}