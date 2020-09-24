package cn.fusionfuture.bugu.pk.mapper;

import cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.UserAttendPlanRecordVO;
import cn.fusionfuture.bugu.pojo.entity.PmsUserAttendPlan;
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
public interface PmsUserAttendPlanMapper extends BaseMapper<PmsUserAttendPlan> {

    /*
     * 根据用户id查询pk计划
     * @author zws
     * @since 2020/9/12 16:45
     * @param [uid]
     * @return java.util.List<cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO>
     **/
    List<BasicPkPlanVO> queryPkUserAttendPlanByUserId(Long uid);

    /*
     * TODO 根据用户id和计划id选中用户参与计划表中的一条记录
     * @author zws
     * @since 2020/9/23 10:00
     * @param [planId, userId] 
     * @return cn.fusionfuture.bugu.pojo.entity.PmsUserAttendPlan 
     **/
    UserAttendPlanRecordVO queryByUserIdAndPlanId(@Param("userId") Long userId, @Param("planId") Long planId);

    /*
     * 根据计划id查询一个pk计划
     * @author zws
     * @since 2020/9/21 11:35
     * @param [uid, Pid]
     * @return cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO
     **/
    //BasicPkPlanVO queryPkUserAttendPlanByPlanId(Long pid);
}
