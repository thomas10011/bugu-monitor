package cn.fusionfuture.bugu.dispose.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author thomas
 * @description 忽略对返回结果的统一处理
 * @create 2020/8/16 3:15 下午
 * @update 2020/8/16 3:15 下午
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableIgnoreResponse {
    /**
     * 是否进行全局返回结果处理封装
     * @return true:进行处理;  false:不进行处理
     */
    boolean dispose() default true;

}
