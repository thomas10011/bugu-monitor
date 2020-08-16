package cn.fusionfuture.bugu.pojo.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.omg.CORBA.COMM_FAILURE;

import java.util.HashMap;
import java.util.Map;

/**
 * @class CommonResult
 * @description 统一返回结果
 * @author thomas
 * @date 2020/8/14 7:51 下午
 * @version  1.0
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult {

    private String code;
    private String message;
    private String service;
    private Map<String, Object> data = new HashMap<>();

    /**
     * @author thomas
     * @description 成功返回时调用
     * @create 2020/8/14 11:02 下午
     * @update 2020/8/14 11:02 下午
     * @param service 微服务码
     * @return cn.fusionfuture.bugu.pojo.api.CommonResult
     **/
    public static CommonResult success() {
        return new CommonResult().setCode("00000").setMessage("处理成功！");
    }

    /**
     * @author thomas
     * @description 失败返回时调用
     * @create 2020/8/14 11:02 下午
     * @update 2020/8/14 11:02 下午
     * @param serviceCode 微服务码
     * @param resultCode 结果错误码
     * @return cn.fusionfuture.bugu.pojo.api.CommonResult
     **/
    public static CommonResult fail(ResultCode resultCode) {
        return new CommonResult().setCode(resultCode.getCode()).setMessage(resultCode.getMessage());
    }

    /**
     * @author Huiri Tan
     * @description 向data中添加数据
     * @create 2020/8/14 8:01 下午
     * @update 2020/8/14 8:01 下午
     * @param key 键
     * @param value 值
     * @return cn.fusionfuture.bugu.pojo.api.CommonResult
     **/
    public CommonResult append(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    /**
     * @author thomas
     * @description 根据key从data中获取数据
     * @create 2020/8/15 1:42 下午
     * @update 2020/8/15 1:42 下午
     * @param key 数据的key
     * @return java.lang.Object
     **/
    public Object get(String key) {
        return this.data.get(key);
    }

    public CommonResult setCode(String code) {
        this.code = code;
        return this;
    }

    public CommonResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public CommonResult setService(String service) {
        this.service = service;
        return this;
    }

    public CommonResult setService(ServiceCode serviceCode) {
        this.service = serviceCode.getName();
        return this;
    }
}
