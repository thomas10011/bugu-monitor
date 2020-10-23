package cn.fusionfuture.bugu.search.service.impl;

import cn.fusionfuture.bugu.search.feign.MonitorFeignService;
import cn.fusionfuture.bugu.search.feign.PkFeignService;
import cn.fusionfuture.bugu.search.service.IPopularPunchService;
import cn.fusionfuture.bugu.search.vo.PlanTrendVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author zws
 * @version 1.0
 * @class PopularPunchServiceImpl
 * @description
 * @date 2020/10/20 16:28
 */
@Service
public class PopularPunchServiceImpl implements IPopularPunchService {

    @Autowired
    PkFeignService pkFeignService;

    @Autowired
    MonitorFeignService monitorFeignService;

    @Override
    public PageInfo<PlanTrendVO> queryPopularPunch(Integer pn,Integer ps,Long uid){
    PageHelper.startPage(pn,ps);
    //存储合并并排序后的最终结果
    List<PlanTrendVO> planTrendVOS=new ArrayList<>();
    //按用户id查询监督的monitor计划打卡情况
    List<PlanTrendVO> monitorPlanTrendVOS=monitorFeignService.queryMonitorPlanTrendVO(uid).getData();
    //按用户id查询监督的pk计划打卡情况
    List<PlanTrendVO> pkPlanTrendVOS=pkFeignService.querypkPlanTrendVO(uid).getData();
    //list合并
    planTrendVOS.addAll(monitorPlanTrendVOS);
    planTrendVOS.addAll(pkPlanTrendVOS);
    Collections.sort(planTrendVOS, new Comparator<PlanTrendVO>() {
        @Override
        public int compare(PlanTrendVO o1, PlanTrendVO o2) {

            if(o1.getPunchTime()!=null&&o2.getPunchTime()!=null){
                if(o1.getPunchTime().isBefore(o2.getPunchTime())) {
                    return 2;
                }
            }
            if(o1.getPunchTime()==null||o2.getPunchTime()==null){
                return 1;
            }

            return 0;
        }
    });
    return new PageInfo<>(planTrendVOS);
}
}