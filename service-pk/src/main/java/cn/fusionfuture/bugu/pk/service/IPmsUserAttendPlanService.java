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
     * 根据用户id查询计划
     * @author zws
     * @since 2020/9/12 16:46
     * @param [pn, ps, uid]
     * @return com.github.pagehelper.PageInfo<cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO>
     **/
    PageInfo<BasicPkPlanVO> queryPkPlanByUserId(Integer pn, Integer ps, Long uid);
}