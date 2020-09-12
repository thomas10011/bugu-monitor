package cn.fusionfuture.bugu.pk.mapper;

import cn.fusionfuture.bugu.pk.vo.PkPlanStatusVO;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPlanStatus;
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
public interface PmsPkPlanStatusMapper extends BaseMapper<PmsPkPlanStatus> {

    /*
     * TODO 
     * @author zws
     * @since 2020/9/12 14:59
     * @param [] 
     * @return java.util.List<cn.fusionfuture.bugu.pk.vo.PkPlanStatusVO> 
     **/
    List<PkPlanStatusVO> queryPkPlanStatus();
}
