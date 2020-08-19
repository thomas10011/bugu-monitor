package cn.fusionfuture.bugu.pojo.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @class CommonResult
 * @description 统一返回结果
 * @author thomas
 * @date 2020/8/14 7:51 下午
 * @version  1.0
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = -2362944906883437387L;

    private String code;
    private String message;
    private String service;
    private T data;

    /**
     * @author thomas
     * @description 成功返回时调用
     * @create 2020/8/14 11:02 下午
     * @update 2020/8/14 11:02 下午
     * @return cn.fusionfuture.bugu.pojo.api.CommonResult
     **/
    public static <T> CommonResult<T> success() {
        return new CommonResult<T>()
                .setCode("00000")
                .setMessage("处理成功！");
    }

    /**
     * @author thomas
     * @description 失败返回时调用
     * @create 2020/8/14 11:02 下午
     * @update 2020/8/14 11:02 下午
     * @param resultCode 结果错误码
     * @return cn.fusionfuture.bugu.pojo.api.CommonResult
     **/
    public static <T> CommonResult<T> fail(ResultCode resultCode) {
        return new CommonResult<T>()
                .setCode(resultCode.getCode())
                .setMessage(resultCode.getMessage());
    }

    /**
     * @author Huiri Tan
     * @description 向data中添加数据
     * @create 2020/8/14 8:01 下午
     * @update 2020/8/14 8:01 下午
     * @param data 要添加的数据
     * @return cn.fusionfuture.bugu.pojo.api.CommonResult
     **/
    public CommonResult<T> append(T data) {
        this.data = data;
        return this;
    }


    public CommonResult<T> setService(String service) {
        this.service = service;
        return this;
    }
    public CommonResult<T> setService(ServiceCode serviceCode) {
        this.service = serviceCode.getName();
        return this;
    }
}
