package cn.fusionfuture.bugu.monitor.service;

import cn.fusionfuture.bugu.monitor.vo.plan.BasicMonitorPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorUserGrabTicket;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

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
     * 用户通过抢票方式获得对监督计划进行监督和投票的机会
     * @author zws
     * @since 2020/9/24 21:21
     * @param [userId, planId] 
     * @return java.lang.String
     **/
    Integer userGrabTicket(Long userId,Long planId);

    /*
     * @author zws
     * @description 根据用户id查询monitor计划(用户参与投票的monitor计划）
     * @create 2020/10/25 14:10
     * @update 2020/10/25 14:10
     * @param [pn, ps, uid]
     * @return com.github.pagehelper.PageInfo<cn.fusionfuture.bugu.monitor.vo.plan.BasicMonitorPlanVO>
     **/
    PageInfo<BasicMonitorPlanVO> queryUserVotePlanByUserId(Integer pn, Integer ps, Long uid);
}
