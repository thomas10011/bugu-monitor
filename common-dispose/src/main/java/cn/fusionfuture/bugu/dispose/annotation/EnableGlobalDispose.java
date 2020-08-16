package cn.fusionfuture.bugu.dispose.annotation;

import cn.fusionfuture.bugu.dispose.GlobalDefaultConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author thomas
 * @description 该注解用于启用全局统一异常处理
 * @create 2020/8/16 3:19 下午
 * @update 2020/8/16 3:19 下午
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(GlobalDefaultConfiguration.class)
public @interface EnableGlobalDispose {
}
