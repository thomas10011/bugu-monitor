package cn.fusionfuture.bugu.monitor.mapper;

import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPlan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface PmsMonitorUpdateStateMapper extends BaseMapper<PmsMonitorPlan> {

    /*
     * @author zws
     * @description 检测已过期的计划
     * @create 2020/12/9 14:15
     * @update 2020/12/9 14:15
     * @param []
     * @return void
     **/
    void checkPlanIsEnd();

    /*
     * @author zws
     * @description 检测开始的计划
     * @create 2020/12/9 14:16
     * @update 2020/12/9 14:16
     * @param []
     * @return void
     **/
    void checkPlanIsStart();
}
