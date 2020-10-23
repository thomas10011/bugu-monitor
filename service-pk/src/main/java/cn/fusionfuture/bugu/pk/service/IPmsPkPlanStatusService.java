package cn.fusionfuture.bugu.pk.service;

import cn.fusionfuture.bugu.pk.vo.PkPlanStatusVO;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPlanStatus;
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
public interface IPmsPkPlanStatusService extends IService<PmsPkPlanStatus> {

    /*
     * 查询所有计划状态
     * @author zws
     * @since 2020/9/12 14:54
     * @param []
     * @return List<PkPlanStatusVO>
     **/
    List<PkPlanStatusVO> queryPkPlanStatus();
}
