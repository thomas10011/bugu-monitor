package cn.fusionfuture.bugu.pk.service;

import cn.fusionfuture.bugu.pk.mapper.PmsPkPlanMapper;
import cn.fusionfuture.bugu.pk.mapper.PmsPkPunchImageUrlMapper;
import cn.fusionfuture.bugu.pk.mapper.PmsPkPunchRecordMapper;
import cn.fusionfuture.bugu.pk.vo.BasicPunchVO;
import cn.fusionfuture.bugu.pojo.constants.PunchStatus;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPlan;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPunchImageUrl;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPunchRecord;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPunchRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

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
        * TODO 打卡
        * @author zws
        * @since 2020/9/21 17:12
        * @param [planId, userId, content, imageUrls]
        * @return java.lang.Long
        **/
        Long punch(Long userId, Long planId, String content, List<String> imageUrls);

        /*
         * TODO 根据打卡id对该次打卡进行点赞操作
         * @author zws
         * @since 2020/9/25 19:33
         * @param [punchId] 
         * @return void 
         **/
        void like(Long punchId);

        /*
         * TODO 根据打卡id查询打卡的相关信息
         * @author zws
         * @since 2020/9/25 20:28
         * @param [punchId]
         * @return cn.fusionfuture.bugu.pk.vo.BasicPunchVO
         **/
//        BasicPunchVO queryBasicPunchVO(Long punchId);
}
