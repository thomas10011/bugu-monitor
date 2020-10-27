package cn.fusionfuture.bugu.monitor.mapper;

import cn.fusionfuture.bugu.monitor.vo.plan.BasicMonitorPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorUserGrabTicket;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author thomas
 * @since 2020-09-11
 */
public interface PmsMonitorUserGrabTicketMapper extends BaseMapper<PmsMonitorUserGrabTicket> {

    /*
     * @author zws
     * @description 根据用户id查询用户参与监督的monitor计划
     * @create 2020/10/25 14:13
     * @update 2020/10/25 14:13
     * @param [uid]
     * @return java.util.List<cn.fusionfuture.bugu.monitor.vo.plan.BasicMonitorPlanVO>
     **/
    List<BasicMonitorPlanVO> queryUserVotePlanByUserId(Long uid);
}
