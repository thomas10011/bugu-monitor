package cn.fusionfuture.bugu.pk.service;

import cn.fusionfuture.bugu.pk.vo.PlanTrendVO;
import cn.fusionfuture.bugu.pk.vo.PunchWithImageVO;
import cn.fusionfuture.bugu.pk.vo.SimplePunchVO;
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
        * 打卡
        * @author zws
        * @since 2020/9/21 17:12
        * @param [planId, userId, content, imageUrls]
        * @return java.lang.Long
        **/
        String punch(Long userId, Long planId, String content, List<String> imageUrls);

        /*
         * 根据打卡id对该次打卡进行点赞操作
         * @author zws
         * @since 2020/9/25 19:33
         * @param [punchId] 
         * @return void 
         **/
        void like(Long punchId);

        /*
         * 根据打卡id查询打卡的相关信息
         * @author zws
         * @since 2020/9/25 20:28
         * @param [punchId]
         * @return cn.fusionfuture.bugu.pk.vo.BasicPunchVO
         **/
        PunchWithImageVO queryPunchWithImageVO(Long punchId);

        /*
         * @author zws
         * @description 根据用户id和计划id查询打卡日历
         * @create 2020/10/15 21:01
         * @update 2020/10/15 21:01
         * @param [userId, planId]
         * @return java.util.List<cn.fusionfuture.bugu.pk.vo.SimplePunchVO>
         **/
        List<SimplePunchVO> querySimplePunchVO(Long userId,Long planId);

 /*
  * @author zws
  * @description 根据用户id获取用户所监督计划的打卡情况
  * @create 2020/10/16 18:41
  * @update 2020/10/16 18:41
  * @param [userId]
  * @return java.util.List<cn.fusionfuture.bugu.pk.dto.PkPlanTrendDTO>
  **/
 List<PlanTrendVO> queryPkPlanTrendVO (Long userId);

 /*
  * @author zws
  * @description 根据当前时间及计划id获取当前所处打卡周期
  * @create 2020/10/16 20:08
  * @update 2020/10/16 20:08
  * @param [currentTime, planId]
  * @return java.lang.Integer
  **/
 Integer getCurrentPunchCycle(LocalDateTime currentTime,Long planId);
}
