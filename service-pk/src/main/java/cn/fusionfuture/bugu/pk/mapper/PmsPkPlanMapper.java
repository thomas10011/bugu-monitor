package cn.fusionfuture.bugu.pk.mapper;

import cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPlan;
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
public interface PmsPkPlanMapper extends BaseMapper<PmsPkPlan> {

    /*
     * 根据计划id查询pk计划
     * @author zws
     * @since 2020/9/12 16:44
     * @param [uid]
     * @return java.util.List<cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO>
     **/
    List<BasicPkPlanVO> queryBasicPkPlanVO(Long uid);
}
