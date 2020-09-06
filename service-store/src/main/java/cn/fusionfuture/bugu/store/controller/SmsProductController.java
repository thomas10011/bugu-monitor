package cn.fusionfuture.bugu.store.controller;


import cn.fusionfuture.bugu.utils.oss.OssUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@RestController
public class SmsProductController {

    @PostMapping(value = "/product")
    public void createProduct() {

    }

    @GetMapping(value = "/policy")
    public Map<String, String> getPolicy() throws IOException, ServletException {
        return OssUtil.getPolicy("testDir/");
    }



}
