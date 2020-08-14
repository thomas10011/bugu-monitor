package cn.fusionfuture.bugu.pojo.api;

import lombok.Getter;

/**
 * @author thomas
 * @description 微服务名称代码
 * @create 2020/8/14 10:38 下午
 * @update 2020/8/14 10:38 下午
 **/
@Getter
public enum ServiceCode {
    /*
     * @author thomas
     **/
    MESSAGE("10001", "message-service"),
    MONITOR("10002", "monitor-service"),
    PK("10003", "pk-service"),
    STORE("10004", "store-service"),
    TRANSACTION("10005", "transaction-service"),
    USER("10006", "user-service");

    private final String code;
    private final String name;

    ServiceCode(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
