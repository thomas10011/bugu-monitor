package cn.fusionfuture.bugu.user.service.impl;

import cn.fusionfuture.bugu.pojo.entity.UmsUserFollow;
import cn.fusionfuture.bugu.user.mapper.UmsUserFollowMapper;
import cn.fusionfuture.bugu.user.service.IUmsUserFollowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public PageInfo<UmsUserFollow> queryUmsUserFollowByPage(Integer pn, Integer ps) {
        Page<UmsUserFollow> page = PageHelper.startPage(pn, ps);
        PageInfo<UmsUserFollow> pageInfo = new PageInfo(umsUserFollowMapper.selectList(null));
        return pageInfo;
    }
}
