package cn.fusionfuture.bugu.user.service.impl;

import cn.fusionfuture.bugu.pojo.constants.UserRelationship;
import cn.fusionfuture.bugu.pojo.entity.UmsUser;
import cn.fusionfuture.bugu.pojo.entity.UmsUserFollow;
import cn.fusionfuture.bugu.user.mapper.UmsUserFollowMapper;
import cn.fusionfuture.bugu.user.mapper.UmsUserMapper;
import cn.fusionfuture.bugu.user.service.IUmsUserFollowService;
import cn.fusionfuture.bugu.user.vo.UserFollowVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

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
        // 首先查询关注信息
        UmsUserFollow obj = userFollowMapper.queryUmsUserFollow(uid, fuid);
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
        else if (obj.getFollowedEachOther()) {
            // 已经互相关注了 返回false
            return false;
        }
        else if (obj.getUserId().equals(fuid)) {
            // 要关注的用户已经是他的粉丝了 那么就是变成相互关注
            obj.setFollowedEachOther(true);

            // 被关注的用户粉丝数+1 关注的用户关注数+1
            UmsUser user = userMapper.selectById(uid);
            UmsUser followedUser = userMapper.selectById(fuid);

            userMapper.updateById(user.setFollowQuantity(user.getFollowQuantity() + 1));
            userMapper.updateById(followedUser.setFansQuantity(followedUser.getFansQuantity() + 1));
            userFollowMapper.updateById(obj.setUpdateTime(LocalDateTime.now()));
            return true;
        }
        // 最后的情况就是已经关注过该用户了
        return false;
    }

    @Override
    public Boolean unFollowUser(Long uid, @RequestParam Long fuid) {
        // 首先查询关注信息
        UmsUserFollow obj = userFollowMapper.queryUmsUserFollow(uid, fuid);
        if (obj == null) {
            // 查询不到任何关注信息 直接返回false
            return false;
        }
        else if (obj.getFollowedEachOther()) {
            // 如果是互相关注 改变关注状态
            obj.setUserId(fuid).setFollowedUserId(uid).setFollowedEachOther(false);
            userFollowMapper.updateById(obj);


        }
        else if (obj.getUserId().equals(uid)) {
            // 之前关注过 则删除关注记录
            userFollowMapper.deleteById(obj);
        }
        else {
            // 否则就是之前没有关注过该用户 返回false
            return false;
        }

        // 被关注的用户粉丝数-1 关注的用户关注数-1
        UmsUser user = userMapper.selectById(uid);
        UmsUser followedUser = userMapper.selectById(fuid);
        // TODO: 如果用户关注数和粉丝数小于1 那么应该抛出异常

        userMapper.updateById(user.setFollowQuantity(user.getFollowQuantity() - 1));
        userMapper.updateById(followedUser.setFansQuantity(followedUser.getFansQuantity() - 1));

        return true;
    }

    @Override
    public Integer queryRelationship(Long id1, Long id2) {
        UmsUserFollow obj = userFollowMapper.queryUmsUserFollow(id1, id2);

        if (obj == null) {
            // 为空表示没两个人互相没有关注
            return UserRelationship.NON.getIndex();
        }
        else if (obj.getFollowedEachOther()) {
            // 互相关注
            return UserRelationship.FRIENDS.getIndex();
        }
        else if (obj.getUserId().equals(id1)) {
            return UserRelationship.FOLLOWED.getIndex();
        }
        else {
            return UserRelationship.FANS.getIndex();
        }
    }
}
