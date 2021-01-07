package cn.fusionfuture.bugu.pk.service;

import cn.fusionfuture.bugu.pk.vo.plan.BasicPkPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsPkUserGrabTicket;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

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
    Integer userGrabTicket(Long userId,Long planId);

    /*
     * @author zws
     * @description 根据用户id查询pk计划(用户参与投票的pk计划）
     * @create 2020/10/25 13:16
     * @update 2020/10/25 13:16
     * @param [pn, ps, uid]
     * @return com.github.pagehelper.PageInfo<cn.fusionfuture.bugu.pk.vo.plan.BasicPkPlanVO>
     **/
    PageInfo<BasicPkPlanVO> queryUserVotePlanByUserId(Integer pn, Integer ps, Long uid);
}
