package cn.fusionfuture.bugu.pojo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author thomas
 * @version 1.0
 * @class MonitorPlanType
 * @description 监督计划的类型
 * @date 2020/9/24 8:18 上午
 */
@Getter
@AllArgsConstructor
public enum  MonitorPlanType {
    /**
     * 默认成功值
     */
    ALL(0, "全部"),
    SELF(1, "自我监督"),
    SINGLE(2, "单人监督"),
    MULTIPLE(3, "多人监督");


    private final Integer index;

    private final String value;

    public static String getValue(Integer index) {
        for (MonitorPlanType type : MonitorPlanType.values()) {
            if (type.index.equals(index)) {
                return type.value;
            }
        }
        return null;
    }

}
