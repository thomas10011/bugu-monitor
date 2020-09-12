package cn.fusionfuture.bugu.pk.service;

import cn.fusionfuture.bugu.pk.vo.PkPlanPatternVO;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPattern;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
public interface IPmsPkPatternService extends IService<PmsPkPattern> {
    /*
     * TODO
     * @author zws
     * @since 2020/9/12 11:47
     * @param []
     * @return java.util.List<cn.fusionfuture.bugu.pk.vo.PkPlanPatternVO>
     **/
    List<PkPlanPatternVO> queryPkPlanPattern();

}
