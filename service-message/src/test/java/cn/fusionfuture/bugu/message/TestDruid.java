package cn.fusionfuture.bugu.message;

import cn.fusionfuture.bugu.message.mapper.MmsCommentRemindMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

/**
 * @author thomas
 * @version 1.0
 * @class TestDruid
 * @description TODO
 * @date 2020/8/17 3:13 下午
 */
@SpringBootTest
public class TestDruid {
    @Autowired
    DataSource dataSource;

    @Autowired
    MmsCommentRemindMapper mmsCommentRemindMapper;

    @Test
    public void test() throws Exception{
        //class com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceWrapper
        System.out.println(dataSource.getClass());
        //com.mysql.cj.jdbc.ConnectionImpl@d9f5fce
        System.out.println(dataSource.getConnection());
        System.out.println(mmsCommentRemindMapper);
    }
}
