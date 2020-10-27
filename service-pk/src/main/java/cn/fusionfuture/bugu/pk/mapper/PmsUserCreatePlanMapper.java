package cn.fusionfuture.bugu.pk.mapper;


import cn.fusionfuture.bugu.pk.vo.plan.BasicPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.plan.DetailedPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.UserCreatePlanRecordVO;
import cn.fusionfuture.bugu.pk.vo.plan.MyAchievementPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsUserCreatePlan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
public interface PmsUserCreatePlanMapper extends BaseMapper<PmsUserCreatePlan> {

    /*
     * 根据用户id查询pk计划
     * @author zws
     * @since 2020/9/12 16:45
     * @param [uid]
     * @return java.util.List<cn.fusionfuture.bugu.pk.vo.plan.MyAchievementPlanVO>
     **/
    List<MyAchievementPlanVO> queryPkUserCreatePlanByUserId(Long uid);

    /*
     * TODO 根据用户id和计划id选中用户参与计划表中的一条记录
     * @author zws
     * @since 2020/9/23 10:00
     * @param [planId, userId]
     * @return cn.fusionfuture.bugu.pojo.entity.PmsUserAttendPlan
     **/
    UserCreatePlanRecordVO queryByUserIdAndPlanId(@Param("userId") Long userId, @Param("planId") Long planId);

    /*
     * 根据用户id和计划id查询计划详细信息
     * @author zws
     * @since 2020/10/14 22:30
     * @param [uid, pid]
     * @return cn.fusionfuture.bugu.pk.vo.plan.DetailedPkPlanVO
     **/
    DetailedPkPlanVO queryDetailedPkPlanVO(@Param("uid") Long uid, @Param("pid") Long pid);
}



