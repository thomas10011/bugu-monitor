package cn.fusionfuture.bugu.pk.service;

import cn.fusionfuture.bugu.pk.vo.plan.BasicPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.plan.MyAchievementPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsUserCreatePlan;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zws
 * @since 2020-09-15
 */
public interface IPmsUserCreatePlanService extends IService<PmsUserCreatePlan> {

    /*
     * 根据用户id查询pk计划(用户创建的pk计划）
     * @author zws
     * @since 2020/9/12 16:46
     * @param [pn, ps, uid]
     * @return com.github.pagehelper.PageInfo<cn.fusionfuture.bugu.pk.vo.plan.MyAchievementPlanVO>
     **/
    PageInfo<MyAchievementPlanVO> queryPkUserCreatePlanByUserId(Integer pn, Integer ps, Long uid);
}
