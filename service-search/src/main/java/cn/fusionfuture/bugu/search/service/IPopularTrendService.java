package cn.fusionfuture.bugu.search.service;

import cn.fusionfuture.bugu.search.dto.PlanTrendDTO;
import cn.fusionfuture.bugu.search.vo.PopularTrendVO;
import java.util.*;

import java.io.IOException;

/**
 * @author lzr
 * @date 2021/3/3 18:09
 */
public interface IPopularTrendService {

    /**
     * es查询动态
     *
     * @param uid 用户id
     * @param pid 帖子id
     * @param pageNum 分页数
     * @param pageSize 分页大小
     * @return PopularTrendVO
     * @throws IOException e
     */
    PopularTrendVO queryPopularTrend(Long uid, Long pid, Integer pageNum, Integer pageSize) throws IOException;

    /**
     * 给动态点赞
     *
     * @param pid 动态id
     * @throws IOException e
     */
    void ratePopularTrend(Long pid) throws IOException;

    /**
     * 取消动态的点赞
     *
     * @param pid 动态id
     * @throws IOException e
     */
    void cancelRatePopularTrend(Long pid) throws IOException;

    /**
     * 创建动态信息
     *
     * @param planTrendDTO 动态信息
     * @throws IOException e
     */
    void createPopularTrend(PlanTrendDTO planTrendDTO) throws IOException;

    /**
     * 增加打卡认可次数
     *
     * @param pid 动态id
     * @throws IOException e
     */
    void rateAgreeCount(Long pid) throws IOException;

    /**
     * 减少打卡认可次数
     *
     * @param pid 动态id
     * @throws IOException e
     */
    void cancelAgreeCount(Long pid) throws IOException;

    /**
     * 增加打卡不认可次数
     *
     * @param pid 动态id
     * @throws IOException e
     */
    void rateDisagreeCount(Long pid) throws IOException;

    /**
     * 减少打卡不认可次数
     *
     * @param pid 动态id
     * @throws IOException e
     */
    void cancelDisagreeCount(Long pid) throws IOException;

    /**
     * 增加评论数
     *
     * @param pid 动态id
     * @throws IOException e
     */
    void rateCommentQuantity(Long pid) throws IOException;

    /**
     * 减少评论数
     *
     * @param pid 动态id
     * @throws IOException e
     */
    void cancelCommentQuantity(Long pid) throws IOException;

    /**
     * 更新动态图片信息
     *
     * @param pid 动态id
     * @param imageUrls 动态图片
     * @throws IOException e
     */
    void updateImageUrls(Long pid, List<String> imageUrls) throws IOException;

    /**
     * 更新动态内容
     *
     * @param pid 动态id
     * @param content 动态内容
     * @throws IOException e
     */
    void updateContent(Long pid, String content) throws IOException;
}
