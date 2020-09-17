package cn.fusionfuture.bugu.pk.service.impl;

import cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO;
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
    PmsUserAttendPlanMapper userPkPlanMapper;

    @Override
    public PageInfo<BasicPkPlanVO> queryPkPlanByUserId(Integer pn, Integer ps, Long uid){
        PageHelper.startPage(pn,ps);
        return new PageInfo<>(userPkPlanMapper.queryPkPlanByUserId(uid));
    }
}
