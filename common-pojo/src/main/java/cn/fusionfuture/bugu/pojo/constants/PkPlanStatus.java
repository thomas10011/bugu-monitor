package cn.fusionfuture.bugu.pojo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author thomas
 * @version 1.0
 * @class PkPlanStatus
 * @description pk计划的状态
 * @date 2020/9/24 8:25 上午
 */
@Getter
@AllArgsConstructor
public enum PkPlanStatus {

    /**
     * 默认值
     */
    ALL(0, "全部"),
    REGISTERING(1, "加入pk"),
    GRABBING(2, "抢单中"),
    COMPLETE(3, "已完成");

    private final Integer index;

    private final String value;

    public static String getValue(Integer index) {
        for (PkPlanStatus stat : PkPlanStatus.values()) {
            if (stat.index.equals(index)) {
                return stat.value;
            }
        }
        return null;
    }


}
