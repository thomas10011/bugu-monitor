package cn.fusionfuture.bugu.store.service;

import cn.fusionfuture.bugu.pojo.entity.SmsProduct;
import cn.fusionfuture.bugu.store.vo.NewProductVO;
import cn.fusionfuture.bugu.store.vo.ProductVO;
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
public interface ISmsProductService extends IService<SmsProduct> {

    /**
     * 根据ProductVO新增一个商品
     * @author thomas
     * @since 2020/9/7 3:56 下午
     **/
    Long createProduct(NewProductVO productVO);

    /**
     * 分页查询商品信息
     * @author thomas
     * @since 2020/9/7 4:52 下午
     * @param
     * @return
     **/
    PageInfo<ProductVO> queryProductByPage(Integer pn, Integer ps);

}
