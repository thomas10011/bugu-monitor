package cn.fusionfuture.bugu.message.service;

import cn.fusionfuture.bugu.dispose.annotation.EnableGlobalDispose;
import cn.fusionfuture.bugu.message.mapper.MmsCommentRemindMapper;
import cn.fusionfuture.bugu.message.mapper.MmsEnrollTypeMapper;
import cn.fusionfuture.bugu.pojo.entity.MmsCommentRemind;
import cn.fusionfuture.bugu.pojo.entity.MmsEnrollType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author thomas
 * @version 1.0
 * @class TestCRUD
 * @description 测试增删改查
 * @date 2020/8/17 3:51 下午
 */
@SpringBootTest
@EnableGlobalDispose
@Slf4j
public class TestCRUD {

    @Autowired
    MmsCommentRemindMapper mmsCommentRemindMapper;

    @Autowired
    MmsEnrollTypeMapper mmsEnrollTypeMapper;

    @Autowired
    IMmsCommentRemindService iMmsCommentRemindService;

    @Test
    public void testInsert() {
        MmsCommentRemind mmsCommentRemind = new MmsCommentRemind();
        mmsCommentRemind
                .setCommentContent("test insert")
                .setPlanTypeId(1)
                .setPunchId(1L)
                .setSendUserId(1L)
                .setReceiveUserId(1L)
                .setIsChecked(true);
        mmsCommentRemindMapper.insert(mmsCommentRemind);

        MmsCommentRemind m = iMmsCommentRemindService.getById(mmsCommentRemind.getId());
        List<MmsCommentRemind> ls = mmsCommentRemindMapper.selectList(iMmsCommentRemindService.query().select("id"));
        MmsEnrollType mmsEnrollType = new MmsEnrollType();
        mmsEnrollType.setDescription("test insert");
        mmsEnrollTypeMapper.insert(mmsEnrollType);

        log.info(m.toString());

    }
}
