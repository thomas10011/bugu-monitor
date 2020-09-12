package cn.fusionfuture.bugu.store.mapper;

import cn.fusionfuture.bugu.pojo.entity.SmsProduct;
import cn.fusionfuture.bugu.store.vo.ProductVO;
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
public interface SmsProductMapper extends BaseMapper<SmsProduct> {
    /**
     * 用于分页查询出ProductVO
     * @author thomas
     * @since 2020/9/8 12:25 上午
     * @return cn.fusionfuture.bugu.store.vo.ProductVO
     **/
    List<ProductVO> queryProductVO();
}
