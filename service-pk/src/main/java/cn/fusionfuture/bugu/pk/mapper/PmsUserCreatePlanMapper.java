package cn.fusionfuture.bugu.pk.mapper;


import cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsUserAttendPlan;
import cn.fusionfuture.bugu.pojo.entity.PmsUserCreatePlan;
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
public interface PmsUserCreatePlanMapper extends BaseMapper<PmsUserCreatePlan> {

    /*
     * 根据用户id查询pk计划
     * @author zws
     * @since 2020/9/12 16:45
     * @param [uid]
     * @return java.util.List<cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO>
     **/
    List<BasicPkPlanVO> queryPkUserCreatePlanByUserId(Long uid);

}
