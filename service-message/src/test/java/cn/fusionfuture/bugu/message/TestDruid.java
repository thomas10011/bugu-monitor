package cn.fusionfuture.bugu.message;

import cn.fusionfuture.bugu.message.mapper.MmsCommentRemindMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.sql.DataSource;
import java.util.UUID;

/**
 * @author thomas
 * @version 1.0
 * @class TestDruid
 * @description 测试连接池
 * @date 2020/8/17 3:13 下午
 */
@SpringBootTest
@Slf4j
public class TestDruid {
    @Autowired
    DataSource dataSource;

    @Autowired
    MmsCommentRemindMapper mmsCommentRemindMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() throws Exception {
        //class com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceWrapper
        System.out.println(dataSource.getClass());
        //com.mysql.cj.jdbc.ConnectionImpl@d9f5fce
        System.out.println(dataSource.getConnection());
        System.out.println(mmsCommentRemindMapper);
    }

    @Test
    public void testRedis() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

        ops.set("hello", "world_" + UUID.randomUUID().toString());
        String hello  = ops.get("hello");
        log.info("之前保存的数据是：" + hello);
    }
}
