package cn.fusionfuture.bugu.message.service;

import cn.fusionfuture.bugu.message.vo.EnrollVO;
import cn.fusionfuture.bugu.pojo.entity.MmsCommentRemind;
import cn.fusionfuture.bugu.pojo.entity.MmsEnrollRemind;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
public interface IMmsEnrollRemindService extends IService<MmsEnrollRemind> {
    void addEnrollRemind(MmsEnrollRemind mmsEnrollRemind);
    List<EnrollVO> getEnrollRemind(Long id);

}
