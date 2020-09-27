package cn.fusionfuture.bugu.message.service;

import cn.fusionfuture.bugu.message.vo.EnrollVO;
import cn.fusionfuture.bugu.pojo.entity.MmsCommentRemind;
import cn.fusionfuture.bugu.pojo.entity.MmsEnrollRemind;
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
public interface IMmsEnrollRemindService extends IService<MmsEnrollRemind> {
    void addEnrollRemind(MmsEnrollRemind mmsEnrollRemind);
    PageInfo<EnrollVO> getEnrollRemind(Integer pn, Integer ps, Long id);

}
