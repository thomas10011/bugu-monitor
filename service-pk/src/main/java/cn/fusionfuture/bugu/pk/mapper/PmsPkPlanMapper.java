package cn.fusionfuture.bugu.pk.mapper;

import cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.SimplePkPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPlan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.yaml.snakeyaml.scanner.ScannerImpl;

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
     * 根据用户id查询pk计划
     * @author zws
     * @since 2020/9/12 16:44
     * @param [uid]
     * @return java.util.List<cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO>
     **/
    List<BasicPkPlanVO> queryBasicPkPlanVO(Long userId);
    
    /*
     * TODO 根据计划id拆寻计划报名的基本进度
     * @author zws
     * @since 2020/9/25 19:44
     * @param [planId] 
     * @return cn.fusionfuture.bugu.pk.vo.SimplePkPlanVO 
     **/
    SimplePkPlanVO querySimplePkPlanVO(Long planId);
}
