package cn.fusionfuture.bugu.user.service.impl;

import cn.fusionfuture.bugu.pojo.entity.UmsUserFollow;
import cn.fusionfuture.bugu.user.mapper.UmsUserFollowMapper;
import cn.fusionfuture.bugu.user.service.IUmsUserFollowService;
import cn.fusionfuture.bugu.user.vo.UserFollowVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
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
    UmsUserFollowMapper umsUserFollowMapper;

    @Override
    public PageInfo<UserFollowVO> queryUmsUserFollowByPage(Integer pn, Integer ps, Long uid) {
        Page<UserFollowVO> page = PageHelper.startPage(pn, ps);
        return new PageInfo<>(umsUserFollowMapper.queryUmsUserFollowByPage(uid));
    }

    @Override
    public PageInfo<UserFollowVO> queryUmsUserFansByPage(Integer pn, Integer ps, Long uid) {
        Page<UserFollowVO> page = PageHelper.startPage(pn, ps);
        return new PageInfo<>(umsUserFollowMapper.queryUmsUserFansByPage(uid));
    }

    @Override
    public Boolean followUser(Long uid, @RequestParam Long fuid) {
        UmsUserFollow obj = umsUserFollowMapper.selectOne(
                new QueryWrapper<UmsUserFollow>()
                    .eq("user_id", uid)
                    .eq("followed_user_id", fuid)
        );
        if (obj == null) {
            umsUserFollowMapper.insert(
                    new UmsUserFollow()
                    .setFollowedUserId(fuid)
                    .setUserId(uid)
            );
            return true;
        }
        return false;
    }

    @Override
    public Boolean unFollowUser(Long uid, @RequestParam Long fuid) {
        umsUserFollowMapper.delete(new QueryWrapper<UmsUserFollow>()
                .eq("user_id", uid)
                .eq("followed_user_id", fuid));

        return true;
    }
}
