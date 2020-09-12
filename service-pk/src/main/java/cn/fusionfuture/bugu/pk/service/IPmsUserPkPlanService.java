package cn.fusionfuture.bugu.pk.service;

import cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsUserPkPlan;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
public interface IPmsUserPkPlanService extends IService<PmsUserPkPlan> {

    /*
     * TODO
     * @author zws
     * @since 2020/9/12 15:53
     * @param []
     * @return java.util.List<cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO>
     **/
    PageInfo<BasicPkPlanVO> queryPkPlanByUserId(Integer pn, Integer ps, Long uid);
}
