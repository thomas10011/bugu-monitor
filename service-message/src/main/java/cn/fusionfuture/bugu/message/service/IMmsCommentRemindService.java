package cn.fusionfuture.bugu.message.service;

import cn.fusionfuture.bugu.message.vo.CommentVO;
import cn.fusionfuture.bugu.message.vo.PunchCommentVO;
import cn.fusionfuture.bugu.pojo.entity.MmsCommentRemind;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
public interface IMmsCommentRemindService extends IService<MmsCommentRemind> {

    void addComment(MmsCommentRemind mmsCommentRemind);

    PageInfo<CommentVO> getCommentRemind(Integer pn, Integer ps, Long id);

    List<PunchCommentVO> getPunchComment(Long id);

}
