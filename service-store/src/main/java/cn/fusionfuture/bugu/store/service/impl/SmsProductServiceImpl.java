package cn.fusionfuture.bugu.store.service.impl;

import cn.fusionfuture.bugu.pojo.entity.SmsProduct;
import cn.fusionfuture.bugu.store.mapper.SmsProductMapper;
import cn.fusionfuture.bugu.store.service.ISmsProductService;
import cn.fusionfuture.bugu.store.vo.NewProductVO;
import cn.fusionfuture.bugu.store.vo.ProductVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@Service
public class SmsProductServiceImpl extends ServiceImpl<SmsProductMapper, SmsProduct> implements ISmsProductService {

    @Autowired
    SmsProductMapper smsProductMapper;

    @Override
    public Long createProduct(NewProductVO newProductVO) {
        SmsProduct smsProduct = new SmsProduct();
        BeanUtils.copyProperties(newProductVO, smsProduct);

        smsProductMapper.insert(smsProduct);
        return smsProduct.getId();
    }

    @Override
    public PageInfo<ProductVO> queryProductByPage(Integer pn, Integer ps) {
        PageHelper.startPage(pn, ps);
        return new PageInfo<>(smsProductMapper.queryProductVO());
    }
}
