package cn.fusionfuture.bugu.user.service.impl;

import cn.fusionfuture.bugu.pojo.entity.UmsUser;
import cn.fusionfuture.bugu.pojo.entity.UmsUserFollow;
import cn.fusionfuture.bugu.user.mapper.UmsUserFollowMapper;
import cn.fusionfuture.bugu.user.mapper.UmsUserMapper;
import cn.fusionfuture.bugu.user.service.IUmsUserFollowService;
import cn.fusionfuture.bugu.user.vo.UserFollowVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@Service
public class UmsUserFollowServiceImpl extends ServiceImpl<UmsUserFollowMapper, UmsUserFollow> implements IUmsUserFollowService {

    @Autowired
    UmsUserFollowMapper userFollowMapper;

    @Autowired
    UmsUserMapper userMapper;

    @Override
    public PageInfo<UserFollowVO> queryUmsUserFollowByPage(Integer pn, Integer ps, Long uid) {
        Page<UserFollowVO> page = PageHelper.startPage(pn, ps);
        return new PageInfo<>(userFollowMapper.queryUmsUserFollowByPage(uid));
    }

    @Override
    public PageInfo<UserFollowVO> queryUmsUserFansByPage(Integer pn, Integer ps, Long uid) {
        Page<UserFollowVO> page = PageHelper.startPage(pn, ps);
        return new PageInfo<>(userFollowMapper.queryUmsUserFansByPage(uid));
    }

    @Override
    public Boolean followUser(Long uid, @RequestParam Long fuid) {
        UmsUserFollow obj = userFollowMapper.selectOne(
                new QueryWrapper<UmsUserFollow>()
                    .eq("user_id", uid)
                    .eq("followed_user_id", fuid)
        );
        if (obj == null) {
            userFollowMapper.insert(
                    new UmsUserFollow()
                    .setFollowedUserId(fuid)
                    .setUserId(uid)
            );
            // 被关注的用户粉丝数+1 关注的用户关注数+1
            UmsUser user = userMapper.selectById(uid);
            UmsUser followedUser = userMapper.selectById(fuid);

            userMapper.updateById(user.setFollowQuantity(user.getFollowQuantity() + 1));
            userMapper.updateById(followedUser.setFansQuantity(followedUser.getFansQuantity() + 1));

            return true;
        }
        return false;
    }

    @Override
    public Boolean unFollowUser(Long uid, @RequestParam Long fuid) {
        userFollowMapper.delete(new QueryWrapper<UmsUserFollow>()
                .eq("user_id", uid)
                .eq("followed_user_id", fuid));

        // 被关注的用户粉丝数-1 关注的用户关注数-1
        UmsUser user = userMapper.selectById(uid);
        UmsUser followedUser = userMapper.selectById(fuid);

        // TODO: 如果用户关注数和粉丝数小于1 那么应该抛出异常

        userMapper.updateById(user.setFollowQuantity(user.getFollowQuantity() - 1));
        userMapper.updateById(followedUser.setFansQuantity(followedUser.getFansQuantity() - 1));

        return true;
    }
}
