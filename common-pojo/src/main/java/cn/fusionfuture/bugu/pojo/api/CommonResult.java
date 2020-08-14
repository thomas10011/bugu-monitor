package cn.fusionfuture.bugu.pojo.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @class CommonResult
 * @description 统一返回结果
 * @author thomas
 * @date 2020/8/14 7:51 下午
 * @version  1.0
 */
@Data
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
     * @param serviceCode 微服务码
     * @return cn.fusionfuture.bugu.pojo.api.CommonResult
     **/
    public static CommonResult success(ServiceCode serviceCode) {
        CommonResult result = new CommonResult();
        result.setCode("00000");
        result.setMessage("处理成功！");
        result.setService(serviceCode.getName());
        return result;
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
    public static CommonResult fail(ServiceCode serviceCode, ResultCode resultCode){
        CommonResult result = new CommonResult();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        result.setService(serviceCode.getName());
        return result;
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
    public CommonResult append(String key,Object value){
        this.getData().put(key, value);
        return this;
    }
}
