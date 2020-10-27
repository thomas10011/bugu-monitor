package cn.fusionfuture.bugu.monitor.mapper;

import cn.fusionfuture.bugu.monitor.vo.plan.MyAchievementPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsUserMonitorPlan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
public interface PmsUserMonitorPlanMapper extends BaseMapper<PmsUserMonitorPlan> {

    /**
     * 查询用户创建的监督计划（成就界面）
     * @author thomas
     * @since 2020/9/12 11:27 上午
     * @param uid 用户的id
     * @return java.util.List<cn.fusionfuture.bugu.monitor.vo.plan.MyAchievementPlanVO>
     **/
    List<MyAchievementPlanVO> queryMonitorPlanByUserId(Long uid);
}
