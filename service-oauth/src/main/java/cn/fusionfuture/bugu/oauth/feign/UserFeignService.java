package cn.fusionfuture.bugu.oauth.feign;

import cn.fusionfuture.bugu.oauth.vo.UserOauthVO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author thomas
 * @version 1.0
 * @class UserFeignService
 * @description TODO
 * @date 2020/9/14 3:19 下午
 */
@Service
@FeignClient(value = "user-service")
public interface UserFeignService {

    /**
     * 调用user service查看用户是否存在
     * @author thomas
     * @since 2020/9/14 3:22 下午
     * @param openId 用户的openId
     * @return java.lang.Long
     **/
    @GetMapping(value = "/authentication/open-id")
    CommonResult<UserOauthVO> getBindUid(@RequestParam("openId") String openId);


    @GetMapping(value = "/authentication/name-pwd")
    CommonResult<UserOauthVO> getUserNamePassword(@RequestParam("userName") String userName);


}
