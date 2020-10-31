package cn.fusionfuture.bugu.pojo.api;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
    SUCCESS("00000","操作成功！"),

    /**
     * http错误
     */
    NOT_FOUND("H0404", "请求的页面不存在！"),
    METHOD_NOT_ALLOWED("H0405", "没有权限访问该路径！"),
    UNSUPPORTED_MEDIA_TYPE("H0415", "不支持的数据类型！"),
    INTERNAL_SERVER_ERROR("H0500", "内部服务器错误！"),

    OSS_SIGN_FAIL("O0000", "OSS签名出错！"),


    USER_DEFAULT("A0001", "用户端错误"),

    USER_NOT_EXISTED("A0201", "用户不存在"),

    USER_HAS_VOTED("A0202","用户已投票"),

    USER_HAS_NOT_ENROLLED("A0203","用户不能参与投票");








    private final String code;

    private final String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
