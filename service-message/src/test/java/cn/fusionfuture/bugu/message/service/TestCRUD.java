package cn.fusionfuture.bugu.message.service;

import cn.fusionfuture.bugu.pojo.entity.MmsCommentRemind;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author thomas
 * @version 1.0
 * @class TestCRUD
 * @description 测试增删改查
 * @date 2020/8/17 3:51 下午
 */
@SpringBootTest
public class TestCRUD {
    @Autowired
    IMmsCommentRemindService iMmsCommentRemindService;

    @Test
    public void testInsert() {
        MmsCommentRemind mmsCommentRemind = new MmsCommentRemind();

    }
}
