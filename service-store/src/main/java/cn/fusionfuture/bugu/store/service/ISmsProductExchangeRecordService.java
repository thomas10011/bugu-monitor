package cn.fusionfuture.bugu.store.service;

import cn.fusionfuture.bugu.pojo.entity.SmsProductExchangeRecord;
import cn.fusionfuture.bugu.store.vo.ExchangeRecordVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
public interface ISmsProductExchangeRecordService extends IService<SmsProductExchangeRecord> {

    /**
     * 兑换商品
     * @author thomas
     * @since 2020/9/9 9:36 上午
     * @param productId 商品id
     * @param uid 用户id
     * @return 兑换成功或者失败的消息
     **/
    String exchangeProduct(Long productId, Long uid);

    /**
     * 分页查询商品兑换记录
     * @author thomas
     * @since 2020/9/9 9:38 上午
     * @param ps 页面大小
     * @param pn 页码
     * @return com.github.pagehelper.PageInfo<cn.fusionfuture.bugu.store.vo.ExchangeRecordVO>
     **/
    PageInfo<ExchangeRecordVO> queryExchangeRecordByPage(Integer pn, Integer ps, Long uid);


}
