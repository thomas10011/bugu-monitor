package cn.fusionfuture.bugu.pojo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author thomas
 * @version 1.0
 * @class PkPlanType
 * @description pk计划的类型
 * @date 2020/9/24 8:25 上午
 */
@Getter
@AllArgsConstructor
public enum  PkPlanType {

    /**
     * 默认成功值
     */
    ALL(0, "全部"),
    DOUBLE(1, "双人pk"),
    MULTIPLE(2, "多人pk");

    private final Integer index;

    private final String value;

    public static String getValue(Integer index) {
        for (PkPlanType type : PkPlanType.values()) {
            if (type.getIndex().equals(index)) {
                return type.getValue();
            }
        }
        return null;
    }

}
