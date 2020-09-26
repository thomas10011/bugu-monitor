package cn.fusionfuture.bugu.pk.service;

import cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.NewPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.SimplePkPlanVO;
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
    * @return com.github.pagehelper.PageInfo<cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO>
    **/
    PageInfo<BasicPkPlanVO> queryBasicPkPlanVO(Integer pn,Integer ps,Long userId);

    /*
     * TODO 根据计划id查询计划报名进度的基本信息，供消息侧调用
     * @author zws
     * @since 2020/9/25 19:51
     * @param [planId] 
     * @return cn.fusionfuture.bugu.pk.vo.SimplePkPlanVO 
     **/
    SimplePkPlanVO querySimplePkPlanVO(Long planId);
}
