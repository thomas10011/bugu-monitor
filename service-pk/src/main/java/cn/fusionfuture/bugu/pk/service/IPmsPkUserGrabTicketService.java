package cn.fusionfuture.bugu.pk.service;

import cn.fusionfuture.bugu.pojo.entity.PmsPkUserGrabTicket;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
public interface IPmsPkUserGrabTicketService extends IService<PmsPkUserGrabTicket> {

    /*
     * 用户通过抢票方式获得对pk计划投票的机会
     * @author zws
     * @since 2020/9/23 14:26
     * @param [userId, planId]
     * @return java.lang.Long
     **/
    Long userGrabTicket(Long userId,Long planId);

}
