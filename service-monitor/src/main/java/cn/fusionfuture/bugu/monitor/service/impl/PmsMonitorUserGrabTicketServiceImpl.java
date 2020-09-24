package cn.fusionfuture.bugu.monitor.service.impl;

import cn.fusionfuture.bugu.pojo.entity.PmsMonitorUserGrabTicket;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorUserGrabTicketMapper;
import cn.fusionfuture.bugu.monitor.service.IPmsMonitorUserGrabTicketService;
import cn.fusionfuture.bugu.pojo.entity.PmsUserMonitorPlan;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Long userGrabTicket(Long userId,Long planId){
        PmsUserMonitorPlan userMonitorPlan=new PmsUserMonitorPlan();
        userMonitorPlan.set
    }
}
