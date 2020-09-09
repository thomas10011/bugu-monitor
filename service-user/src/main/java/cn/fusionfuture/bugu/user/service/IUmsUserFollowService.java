package cn.fusionfuture.bugu.user.service;

import cn.fusionfuture.bugu.pojo.entity.UmsUserFollow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
public interface IUmsUserFollowService extends IService<UmsUserFollow> {
    PageInfo<UmsUserFollow> queryUmsUserFollowByPage(Integer pn, Integer ps);
}
