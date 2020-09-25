package cn.fusionfuture.bugu.monitor.service;

import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPunchRecord;
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
public interface IPmsMonitorPunchRecordService extends IService<PmsMonitorPunchRecord> {
    /**
     * 打卡
     * @author thomas
     * @since 2020/9/12 2:39 下午
     * @param planId 监督计划id
     * @param userId 用户id
     * @param content 打卡内容
     * @param imageUrls 打卡图片url链接
     * @return 返回打卡的id
     **/
    Long punch(Long planId, Long userId, String content, List<String> imageUrls);

}
