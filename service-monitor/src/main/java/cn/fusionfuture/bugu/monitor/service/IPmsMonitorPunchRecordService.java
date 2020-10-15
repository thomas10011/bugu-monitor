package cn.fusionfuture.bugu.monitor.service;

import cn.fusionfuture.bugu.monitor.vo.BasicPunchVO;
import cn.fusionfuture.bugu.monitor.vo.SimplePunchVO;
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
     * @param content 打卡内容
     * @param imageUrls 打卡图片url链接
     * @return 返回打卡的id
     **/
    String punch(Long planId, String content, List<String> imageUrls);

    /*
     * 根据打卡id对打卡进行点赞
     * @author zws
     * @since 2020/9/26 15:43
     * @param [punchId]
     * @return void
     **/
    void like(Long punchId);

    /*
     *  通过打卡id查询打卡相关信息
     * @author zws
     * @since 2020/9/26 16:10
     * @param [punchId] 
     * @return cn.fusionfuture.bugu.monitor.vo.BasicPunchVO 
     **/
    BasicPunchVO queryBasicPunchVO(Long punchId);
    /*
     * 根据计划id查询监督计划对应的打卡信息
     * @author zws
     * @since 2020/10/15 19:33
     * @param [planId]
     * @return cn.fusionfuture.bugu.monitor.vo.SimplePunchVO
     **/
    List<SimplePunchVO> querySimplePunchVO(Long planId);
}
