package cn.fusionfuture.bugu.monitor.service.impl;

import cn.fusionfuture.bugu.monitor.vo.plan.BasicMonitorPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorUserGrabTicket;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorUserGrabTicketMapper;
import cn.fusionfuture.bugu.monitor.service.IPmsMonitorUserGrabTicketService;
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
 * @since 2020-09-11
 */
@Service
public class PmsMonitorUserGrabTicketServiceImpl extends ServiceImpl<PmsMonitorUserGrabTicketMapper, PmsMonitorUserGrabTicket> implements IPmsMonitorUserGrabTicketService {

    @Autowired
    PmsMonitorUserGrabTicketMapper monitorUserGrabTicketMapper;

    @Override
    public Long userGrabTicket(Long userId,Long planId){

        //插入一条用户抢票获得监督机会的记录
        PmsMonitorUserGrabTicket userMonitorPlanRecord=new PmsMonitorUserGrabTicket();
        userMonitorPlanRecord.setUserId(userId).setMonitorPlanId(planId);
        monitorUserGrabTicketMapper.insert(userMonitorPlanRecord);

        //返回抢票记录的id
        return userMonitorPlanRecord.getId();

    }
    @Override
    public PageInfo<BasicMonitorPlanVO> queryUserVotePlanByUserId(Integer pn, Integer ps, Long uid){

        PageHelper.startPage(pn,ps);
        return new PageInfo<>(monitorUserGrabTicketMapper.queryUserVotePlanByUserId(uid));
    }
}
