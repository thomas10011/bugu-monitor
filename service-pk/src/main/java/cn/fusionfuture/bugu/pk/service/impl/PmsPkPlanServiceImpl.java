package cn.fusionfuture.bugu.pk.service.impl;

import ch.qos.logback.classic.jmx.MBeanUtil;
import cn.fusionfuture.bugu.pk.mapper.PmsUserCreatePlanMapper;
import cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.NewPkPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPlan;
import cn.fusionfuture.bugu.pk.mapper.PmsPkPlanMapper;
import cn.fusionfuture.bugu.pk.service.IPmsPkPlanService;
import cn.fusionfuture.bugu.pojo.entity.PmsUserCreatePlan;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
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
public class PmsPkPlanServiceImpl extends ServiceImpl<PmsPkPlanMapper, PmsPkPlan> implements IPmsPkPlanService {

    @Autowired
    PmsPkPlanMapper pkPlanMapper;

    @Autowired
    PmsUserCreatePlanMapper userCreatePlanMapper;

    @Override
    public Long createPkPlan(NewPkPlanVO newPkPlanVO) {
        PmsPkPlan pkPlan = new PmsPkPlan();
        PmsUserCreatePlan pmsUserCreatePlan=new PmsUserCreatePlan();
        BeanUtils.copyProperties(newPkPlanVO, pkPlan);
        pkPlanMapper.insert(pkPlan);
        pmsUserCreatePlan.setUserId(pkPlan.getUserId());
        pmsUserCreatePlan.setPunchCount(0);
        pmsUserCreatePlan.setPkPlanId(pkPlan.getId());
        userCreatePlanMapper.insert(pmsUserCreatePlan);
        return pkPlan.getId();
    }

    @Override
    public PageInfo<BasicPkPlanVO> queryBasicPkPlanVO(Integer pn, Integer ps, Long uid) {
        PageHelper.startPage(pn,ps);
        return new PageInfo<>(pkPlanMapper.queryBasicPkPlanVO(uid));
    }

}
