package cn.fusionfuture.bugu.store.mapper;

import cn.fusionfuture.bugu.pojo.entity.SmsProductExchangeRecord;
import cn.fusionfuture.bugu.store.vo.ExchangeRecordVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
public interface SmsProductExchangeRecordMapper extends BaseMapper<SmsProductExchangeRecord> {

    /**
     * 分页查询兑换记录
     * @author thomas
     * @since 2020/9/9 9:32 上午
     * @param uid 用户id
     * @return java.util.List<cn.fusionfuture.bugu.store.vo.ExchangeRecordVO>
     **/
    List<ExchangeRecordVO> queryExchangeRecordVO(Long uid);

}
