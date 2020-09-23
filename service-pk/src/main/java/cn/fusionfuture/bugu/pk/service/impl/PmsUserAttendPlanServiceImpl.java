package cn.fusionfuture.bugu.pk.service.impl;

import cn.fusionfuture.bugu.pk.mapper.PmsPkPlanMapper;
import cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPlan;
import cn.fusionfuture.bugu.pojo.entity.PmsUserAttendPlan;
import cn.fusionfuture.bugu.pk.mapper.PmsUserAttendPlanMapper;
import cn.fusionfuture.bugu.pk.service.IPmsUserAttendPlanService;
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
 * @author thomas
 * @since 2020-08-24
 */
@Service
public class PmsUserAttendPlanServiceImpl extends ServiceImpl<PmsUserAttendPlanMapper, PmsUserAttendPlan> implements IPmsUserAttendPlanService {

    @Autowired
    PmsUserAttendPlanMapper userAttendPlanMapper;

    @Override
    public PageInfo<BasicPkPlanVO> queryPkUserAttendPlanByUserId(Integer pn, Integer ps, Long uid){
        PageHelper.startPage(pn,ps);
        return new PageInfo<>(userAttendPlanMapper.queryPkUserAttendPlanByUserId(uid));
    }

//    @Override
//    public BasicPkPlanVO queryPkUserAttendPlanByPlanId(Long pid){
//        return new userPkPlanMapper.queryPkUserAttendPlanByPlanId(pid);
//    }
}
