package cn.fusionfuture.bugu.pk.service.impl;

import cn.fusionfuture.bugu.pk.mapper.PmsUserCreatePlanMapper;
import cn.fusionfuture.bugu.pk.service.IPmsUserCreatePlanService;
import cn.fusionfuture.bugu.pk.vo.plan.BasicPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.plan.MyAchievementPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsUserCreatePlan;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zws
 * @since 2020-09-15
 */
@Service
public class PmsUserCreatePlanServiceImpl extends ServiceImpl<PmsUserCreatePlanMapper, PmsUserCreatePlan> implements IPmsUserCreatePlanService {

    @Autowired
    PmsUserCreatePlanMapper userCreatePlanMapper;

    @Override
    public PageInfo<MyAchievementPlanVO> queryPkUserCreatePlanByUserId(Integer pn, Integer ps, Long uid){
        PageHelper.startPage(pn,ps);
        return new PageInfo<>(userCreatePlanMapper.queryPkUserCreatePlanByUserId(uid));
    }
}