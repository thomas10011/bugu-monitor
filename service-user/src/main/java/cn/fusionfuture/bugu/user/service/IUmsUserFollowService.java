package cn.fusionfuture.bugu.user.service;

import cn.fusionfuture.bugu.pojo.entity.UmsUserFollow;
import cn.fusionfuture.bugu.user.vo.UserFollowVO;
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

    /**
     * 根据id查询用户关注列表
     * @author thomas
     * @since 2020/9/12 9:58 下午
     * @param pn 查询的页码
     * @param ps 查询的页面大小
     * @param uid 用户id
     * @return com.github.pagehelper.PageInfo<cn.fusionfuture.bugu.pojo.entity.UmsUserFollow>
     **/
    PageInfo<UserFollowVO> queryUmsUserFollowByPage(Integer pn, Integer ps, Long uid);

    /**
     * 查询用户粉丝列表
     * @author thomas
     * @since 2020/9/12 10:25 下午
     * @param pn 查询的页码
     * @param ps 查询的页面大小
     * @param uid 用户id
     * @return com.github.pagehelper.PageInfo<cn.fusionfuture.bugu.user.vo.UserFollowVO>
     **/
    PageInfo<UserFollowVO> queryUmsUserFansByPage(Integer pn, Integer ps, Long uid);

    /**
     * 关注用户
     * @author thomas
     * @since 2020/9/12 11:27 下午
     * @param uid 用户id
     * @param fuid 被关注用户id
     * @return java.lang.Boolean
     **/
    Boolean followUser(Long uid, Long fuid);

    /**
     * 取关用户
     * @author thomas
     * @since 2020/9/12 11:28 下午
     * @param uid 用户id
     * @param fuid 被关注用户id
     * @return java.lang.Boolean
     **/
    Boolean unFollowUser(Long uid, Long fuid);

    Integer queryRelationship(Long id1, Long id2);


}
