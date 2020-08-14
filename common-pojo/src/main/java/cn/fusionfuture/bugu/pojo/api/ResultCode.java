package cn.fusionfuture.bugu.pojo.api;

import lombok.Getter;

/**
 * @author Huiri Tan
 * @description 错误码定义
 * @create 2020/8/14 7:45 下午
 * @update 2020/8/14 7:45 下午
 **/
@Getter
public enum ResultCode {
    /**
     * 默认成功值
     */
    SUCCESS("00000","操作成功！");





    private final String code;
    private final String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
