package cn.fusionfuture.bugu.pk.service;

import cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsUserAttendPlan;
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
public interface IPmsUserAttendPlanService extends IService<PmsUserAttendPlan> {

    /*
     * 根据用户id查询pk计划(用户参与的pk计划）
     * @author zws
     * @since 2020/9/12 16:46
     * @param [pn, ps, uid]
     * @return com.github.pagehelper.PageInfo<cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO>
     **/
    PageInfo<BasicPkPlanVO> queryPkUserAttendPlanByUserId(Integer pn, Integer ps, Long uid);

    /*
     * 根据用户id和计划id查询用户参与的一个pk计划
     * @author zws
     * @since 2020/9/21 11:42
     * @param [uid, pid]
     * @return cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO
     **/
    //BasicPkPlanVO queryPkUserAttendPlanByPlanId(Long pid);
}
