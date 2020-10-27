package cn.fusionfuture.bugu.pk.service;

import cn.fusionfuture.bugu.pk.vo.plan.BasicPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.plan.DetailedPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.plan.NewPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.plan.SimplePkPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPlan;
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
public interface IPmsPkPlanService extends IService<PmsPkPlan> {

    /*
     * 创建pk计划
     * @author zws
     * @since 2020/9/12 16:50
     * @param [newPkPlanVO]
     * @return java.lang.Long
     **/
    Long createPkPlan(NewPkPlanVO newPkPlanVO);

   /*
    * 根据用户id查询计划
    * @author zws
    * @since 2020/9/13 15:24
    * @param [pn, ps, uid]
    * @return com.github.pagehelper.PageInfo<cn.fusionfuture.bugu.pk.vo.plan.BasicPkPlanVO>
    **/
    PageInfo<BasicPkPlanVO> queryBasicPkPlanVO(Integer pn,Integer ps,Long userId);

    /*
     * 根据计划id查询计划报名进度的基本信息，供消息侧调用
     * @author zws
     * @since 2020/9/25 19:51
     * @param [planId] 
     * @return cn.fusionfuture.bugu.pk.vo.plan.SimplePkPlanVO
     **/
    SimplePkPlanVO querySimplePkPlanVO(Long planId);

    /*
     * 根据用户id和计划id查询计划详细信息
     * @author zws
     * @since 2020/10/14 22:33
     * @param [uid, pid]
     * @return cn.fusionfuture.bugu.pk.vo.plan.DetailedPkPlanVO
     **/
    DetailedPkPlanVO queryDetailedPkPlanVO(Long uid,Long pid);

    /*
     * 根据计划id定时刷新计划在当前所属周期的打卡情况
     * @author zws
     * @since 2020/10/8 21:52
     * @param [planId]
     * @return void
     **/
    String checkIsPunched(Long userId,Long planId);
}
