package cn.fusionfuture.bugu.message.service.impl;

import cn.fusionfuture.bugu.message.mapper.MmsCommentRemindMapper;
import cn.fusionfuture.bugu.message.service.IMmsCommentRemindService;
import cn.fusionfuture.bugu.pojo.entity.MmsCommentRemind;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.fusionfuture.bugu.message.mapper.MmsCommentRemindMapper;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@Service
public class MmsCommentRemindServiceImpl extends ServiceImpl<MmsCommentRemindMapper, MmsCommentRemind> implements IMmsCommentRemindService {
    @Autowired
    private MmsCommentRemindMapper mmsCommentRemindMapper;
}
