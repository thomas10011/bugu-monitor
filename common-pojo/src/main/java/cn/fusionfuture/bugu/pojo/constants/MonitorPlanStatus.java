package cn.fusionfuture.bugu.pojo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author thomas
 * @version 1.0
 * @class MonitorPlanStatus
 * @description 监督计划的状态
 * @date 2020/9/24 8:19 上午
 */
@Getter
@AllArgsConstructor
public enum MonitorPlanStatus {
    /**
     * 默认值
     */
    ALL(0, "全部"),
    REGISTERING(1, "报名中"),
    UNDERWAY(2, "进行中"),
    COMPLETE(3, "已完成"),
    Failure(4,"未成功");

    private final Integer index;
    private final String value;

    public static String getValue(Integer index) {
        for (MonitorPlanStatus stat : MonitorPlanStatus.values()) {
            if (stat.index.equals(index)) {
                return stat.value;
            }
        }
        return null;
    }


}
