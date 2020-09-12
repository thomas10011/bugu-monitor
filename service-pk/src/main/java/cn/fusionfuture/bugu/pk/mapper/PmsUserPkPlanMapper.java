package cn.fusionfuture.bugu.pk.mapper;

import cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsUserPkPlan;
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
public interface PmsUserPkPlanMapper extends BaseMapper<PmsUserPkPlan> {

    /*
     * TODO
     * @author zws
     * @since 2020/9/12 15:54
     * @param []
     * @return java.util.List<cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO>
     **/
    List<BasicPkPlanVO> queryPkPlanByUserId(Long uid);
}
