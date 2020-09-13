package cn.fusionfuture.bugu.user.mapper;

import cn.fusionfuture.bugu.pojo.entity.UmsUserFollow;
import cn.fusionfuture.bugu.user.vo.UserFollowVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
public interface UmsUserFollowMapper extends BaseMapper<UmsUserFollow> {

    /**
     * 根据用户id查询用户关注的用户的信息
     * @author thomas
     * @since 2020/9/12 10:12 下午
     * @param uid 用户id
     * @return java.util.List<cn.fusionfuture.bugu.user.vo.UserFollowVO>
     **/
    List<UserFollowVO> queryUmsUserFollowByPage(Long uid);

    /**
     * 查询用户粉丝列表
     * @author thomas
     * @since 2020/9/12 10:24 下午
     * @param uid 用户id
     * @return java.util.List<cn.fusionfuture.bugu.user.vo.UserFollowVO>
     **/
    List<UserFollowVO> queryUmsUserFansByPage(Long uid);
}
