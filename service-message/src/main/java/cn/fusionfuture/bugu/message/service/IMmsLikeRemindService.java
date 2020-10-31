package cn.fusionfuture.bugu.message.service;

import cn.fusionfuture.bugu.message.vo.LikeVO;
import cn.fusionfuture.bugu.pojo.entity.MmsLikeRemind;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author LiLan
 * @version 1.0
 * @class MmsLikeRemindServiceImpl
 * @description 定义点赞接口
 * @date 2020/8/22 14:29
 */
public interface IMmsLikeRemindService extends IService<MmsLikeRemind> {

    void addLikeRemind(MmsLikeRemind mmsLikeRemind);
    PageInfo<LikeVO> getLikeRemind(Integer pn, Integer ps, Long id);
    Boolean isLike(Long uid, Long pid);
    Boolean cancelLike(Long uid, Long pid);

}
