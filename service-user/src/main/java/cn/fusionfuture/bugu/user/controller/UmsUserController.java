package cn.fusionfuture.bugu.user.controller;


import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import cn.fusionfuture.bugu.user.service.IUmsUserService;
import cn.fusionfuture.bugu.user.vo.UserDetailsVO;
import cn.fusionfuture.bugu.user.vo.WechatBindDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@RestController
public class UmsUserController {

    @Autowired
    IUmsUserService iumsUserService;

    @GetMapping("/get-personal-details")
    @Valid
    UserDetailsVO getPersonalDetails(@RequestParam Long id) {
        return iumsUserService.getPersonalDetails(id);
    }
}
