package cn.fusionfuture.bugu.pk.service.impl;

import cn.fusionfuture.bugu.pojo.entity.PmsPkUserGrabTicket;
import cn.fusionfuture.bugu.pk.mapper.PmsPkUserGrabTicketMapper;
import cn.fusionfuture.bugu.pk.service.IPmsPkUserGrabTicketService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class PmsPkUserGrabTicketServiceImpl extends ServiceImpl<PmsPkUserGrabTicketMapper, PmsPkUserGrabTicket> implements IPmsPkUserGrabTicketService {

    @Autowired
    PmsPkUserGrabTicketMapper pkUserGrabTicketMapper;
    @Override
    public Long userGrabTicket(Long userId,Long planId){

        //保存用户抢票记录
        PmsPkUserGrabTicket pkUserGrabTicketRecord=new PmsPkUserGrabTicket();
        pkUserGrabTicketRecord.setUserId(userId);
        pkUserGrabTicketRecord.setPkPlanId(planId);
        pkUserGrabTicketMapper.insert(pkUserGrabTicketRecord);

        //返回用户抢票记录id
        return pkUserGrabTicketRecord.getId();

    }
}
