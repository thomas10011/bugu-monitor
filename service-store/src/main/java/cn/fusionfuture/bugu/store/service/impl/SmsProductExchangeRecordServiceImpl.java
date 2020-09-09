package cn.fusionfuture.bugu.store.service.impl;

import cn.fusionfuture.bugu.pojo.entity.SmsProduct;
import cn.fusionfuture.bugu.pojo.entity.SmsProductExchangeRecord;
import cn.fusionfuture.bugu.store.mapper.SmsProductExchangeRecordMapper;
import cn.fusionfuture.bugu.store.mapper.SmsProductMapper;
import cn.fusionfuture.bugu.store.service.ISmsProductExchangeRecordService;
import cn.fusionfuture.bugu.store.vo.ExchangeRecordVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@Service
@Slf4j
public class SmsProductExchangeRecordServiceImpl extends ServiceImpl<SmsProductExchangeRecordMapper, SmsProductExchangeRecord> implements ISmsProductExchangeRecordService {

    @Autowired
    SmsProductExchangeRecordMapper smsProductExchangeRecordMapper;

    @Autowired
    SmsProductMapper smsProductMapper;

    @Transactional(rollbackForClassName = "SmsProduct")
    @Override
    public String exchangeProduct(Long productId, Long uid) {
        SmsProduct product = smsProductMapper.selectById(productId);

        if (product.getStockQuantity() == 0) {
            return "库存不足，兑换失败！";
        }
        smsProductExchangeRecordMapper.insert(
                new SmsProductExchangeRecord()
                        .setImageUrl(product.getImageUrl())
                        .setProductName(product.getName())
                        .setUserId(uid)
                        .setProductId(productId)
                        .setExchangeQuantity(1)
                        .setProductPrice(product.getPrice())
        );
        smsProductMapper.updateById(product
                .setExchangeCount(product.getExchangeCount() + 1)
                .setStockQuantity(product.getStockQuantity() - 1));

        return "兑换成功！";
    }

    @Override
    public PageInfo<ExchangeRecordVO> queryExchangeRecordByPage(Integer pn, Integer ps, Long uid) {
        PageHelper.startPage(pn, ps);
        return new PageInfo<>(smsProductExchangeRecordMapper.queryExchangeRecordVO(uid));
    }
}
