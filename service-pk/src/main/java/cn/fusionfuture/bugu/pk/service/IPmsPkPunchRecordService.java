package cn.fusionfuture.bugu.pk.service;

import cn.fusionfuture.bugu.pk.vo.*;
import cn.fusionfuture.bugu.pk.vo.punch.DetailedPunchVO;
import cn.fusionfuture.bugu.pk.vo.punch.PlanTrendVO;
import cn.fusionfuture.bugu.pk.vo.punch.SimplePunchVO;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPunchRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
public interface IPmsPkPunchRecordService extends IService<PmsPkPunchRecord> {
     /*
      * @author zws
      * @description 对计划进行一次打卡
      * @create 2020/10/23 20:39
      * @update 2020/10/23 20:39
      * @param [userId, planId, content, imageUrls]
      * @return java.lang.String
      **/
     String punch(Long userId, Long planId, String content, List<String> imageUrls);

     /*
      * @author zws
      * @description 对一条打卡记录进行点赞
      * @create 2020/10/23 20:41
      * @update 2020/10/23 20:41
      * @param [punchId]
      * @return void
      **/
     void like(Long punchId);

     /*
      * @author zws
      * @description 对一条打卡记录取消点赞
      * @create 2020/10/27 13:25
      * @update 2020/10/27 13:25
      * @param [punchId]
      * @return void
      **/
     void cancelLike(Long punchId);

     /*
      * @author zws
      * @description 根据打卡id查询打卡的相关信息(打卡日历下的打卡信息）
      * @create 2020/10/23 20:42
      * @update 2020/10/23 20:42
      * @param [punchId]
      * @return cn.fusionfuture.bugu.pk.vo.PunchWithImageVO
      **/
     PunchWithImageVO queryPunchWithImageVO(Long punchId);

     /*
      * @author zws
      * @description 根据打卡id查询打卡的相关信息(打卡详情页面的打卡信息）
      * @create 2020/10/27 15:35
      * @update 2020/10/27 15:35
      * @param [punchId]
      * @return cn.fusionfuture.bugu.pk.vo.punch.DetailedPunchVO
      **/
     DetailedPunchVO queryDetailedPunchVO(Long punchId);

     /*
      * @author zws
      * @description 根据用户id和计划id查询打卡日历
      * @create 2020/10/23 20:42
      * @update 2020/10/23 20:42
      * @param [userId, planId]
      * @return java.util.List<cn.fusionfuture.bugu.pk.vo.punch.SimplePunchVO>
      **/
     List<SimplePunchVO> querySimplePunchVO(Long userId, Long planId);

     /*
      * @author zws
      * @description 根据用户id获取用户所监督计划的打卡情况
      * @create 2020/10/23 20:42
      * @update 2020/10/23 20:42
      * @param [userId]
      * @return java.util.List<cn.fusionfuture.bugu.pk.vo.punch.PlanTrendVO>
      **/
     List<PlanTrendVO> queryPkPlanTrendVO (Long userId);

     /*
      * @author zws
      * @description 根据当前时间及计划id获取当前所处打卡周期
      * @create 2020/10/23 20:43
      * @update 2020/10/23 20:43
      * @param [currentTime, planId]
      * @return java.lang.Integer
      **/
     Integer getCurrentPunchCycle(LocalDateTime currentTime,Long planId);
}
