package cn.fusionfuture.bugu.store.controller;


import cn.fusionfuture.bugu.pojo.entity.SmsProduct;
import cn.fusionfuture.bugu.store.service.ISmsProductService;
import cn.fusionfuture.bugu.store.vo.NewProductVO;
import cn.fusionfuture.bugu.utils.oss.OssUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@RestController
@Api(tags = "商品的相关操作")
public class SmsProductController {

    @Autowired
    ISmsProductService productService;

    @Autowired(required = false)
    private HttpServletRequest request;

    @Autowired(required = false)
    private HttpServletResponse response;

    /**
     * 创建商品
     * @author thomas
     * @since 2020/9/7 3:26 下午
     **/
    @PostMapping(value = "/product")
    @ApiOperation(value = "上架一个新的商品")
    public Long createProduct(@Valid @ApiParam(value = "商品的所有信息") @RequestBody NewProductVO productVO) {
        return productService.createProduct(productVO);
    }

    /**
     * 获取oss的policy用于上传图片
     * @author thomas
     * @since 2020/9/7 5:32 下午
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    @GetMapping(value = "/product/policy")
    @ApiOperation(value = "获取oss的policy用于上传图片")
    public Map<String, String> getPolicy() throws IOException, ServletException {
        System.out.println(request.getHeader("uid"));
        return OssUtil.getPolicy("testDir/");
    }

    /**
     * 分页查询商品简略信息
     * @author thomas
     * @since 2020/9/9 8:18 上午
     * @param pn 页码
     * @param ps 页面大小
     * @return com.github.pagehelper.PageInfo<?>
     **/
    @GetMapping(value = "/products")
    @ApiOperation(value = "分页查询商品的简略信息")
    public PageInfo<?> getProducts(@RequestParam(defaultValue = "1") Integer pn, @RequestParam(defaultValue = "5") Integer ps) {
        return productService.queryProductByPage(pn, ps);
    }

    @GetMapping(value = "/product/{id}")
    @ApiOperation(value = "根据id查询商品的详细信息")
    public SmsProduct getProduct(@ApiParam(value = "商品的id") @PathVariable(name = "id") Long id) {
        return productService.getById(id);
    }

}
