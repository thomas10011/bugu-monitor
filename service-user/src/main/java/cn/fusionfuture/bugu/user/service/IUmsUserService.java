package cn.fusionfuture.bugu.user.service;

import cn.fusionfuture.bugu.pojo.entity.UmsUser;
import cn.fusionfuture.bugu.user.vo.UserDetailsVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
public interface IUmsUserService extends IService<UmsUser> {

    /**
     *
     * @param id    请求获取的目标用户
     * @param uid   当前发送请求登录的用户
     * @return  对应的用户信息
     */
    UserDetailsVO getPersonalDetails(Long id, Long uid);

    HashMap<String,String> getDetailsForMessage(Long id);

}
