package cn.fusionfuture.bugu.monitor.service;

import cn.fusionfuture.bugu.pojo.entity.PmsMonitorUserGrabTicket;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author thomas
 * @since 2020-09-11
 */
public interface IPmsMonitorUserGrabTicketService extends IService<PmsMonitorUserGrabTicket> {

    /*
     * TODO 用户通过抢票方式获得对监督计划进行监督和投票的机会
     * @author zws
     * @since 2020/9/24 21:02
     * @param [userId, planId] 
     * @return java.lang.Long 
     **/
    Long userGrabTicket(Long userId,Long planId);
}
