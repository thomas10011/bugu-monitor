package cn.fusionfuture.bugu.search.service;

import cn.fusionfuture.bugu.search.vo.PlanTrendVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/*
 * @author zws
 * @since 2020/10/20 16:20
 * @param
 * @return
 **/
public interface IPopularPunchService {
    /*
     * 首页动态界面按照事件显示的已监督打卡信息
     * @author zws
     * @since 2020/10/20 17:07
     * @param [uid]
     * @return cn.fusionfuture.bugu.search.vo.PlanTrendVO
     **/
    PageInfo<PlanTrendVO> queryPopularPunch(Integer pn, Integer ps, Long uid) ;
}
