package cn.fusionfuture.bugu.pk.mapper;

import cn.fusionfuture.bugu.pk.vo.PkPlanPatternVO;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPattern;
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
public interface PmsPkPatternMapper extends BaseMapper<PmsPkPattern> {
    /*
     * 查询所有pk计划模式
     * @author zws
     * @since 2020/9/12 11:41
     * @param []
     * @return java.util.List<cn.fusionfuture.bugu.pk.vo.PkPlanPatternVO>
     **/
    List<PkPlanPatternVO> queryPkPlanPattern();
}
