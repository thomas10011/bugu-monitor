package cn.fusionfuture.bugu.store.controller;


import cn.fusionfuture.bugu.pojo.entity.SmsProduct;
import cn.fusionfuture.bugu.pojo.entity.SmsProductExchangeRecord;
import cn.fusionfuture.bugu.store.mapper.SmsProductExchangeRecordMapper;
import cn.fusionfuture.bugu.store.mapper.SmsProductMapper;
import cn.fusionfuture.bugu.store.service.impl.SmsProductExchangeRecordServiceImpl;
import cn.hutool.core.convert.Convert;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@RestController
public class SmsProductExchangeRecordController {

    @Autowired(required = false)
    HttpServletRequest request;

    @Autowired
    SmsProductExchangeRecordServiceImpl productExchangeRecordService;

    @GetMapping(value = "/product/exchange/{id}")
    public String exchangeProduct(@Validated @PathVariable(value = "id") Long productId) {
        Long uid = Convert.toLong(request.getHeader("uid"));
        return productExchangeRecordService.exchangeProduct(productId, uid);
    }

    @GetMapping(value = "/product/exhcange/record")
    public PageInfo<?> queryExchangeRecordsByPage(@RequestParam Integer pn,
                                                  @RequestParam Integer ps) {
        return productExchangeRecordService
                .queryExchangeRecordByPage(pn, ps, Convert.toLong(request.getIntHeader("uid")));
    }


}
