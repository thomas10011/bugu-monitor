package cn.fusionfuture.bugu.user.service;

import cn.fusionfuture.bugu.pojo.entity.UmsUserAuthWechat;
import cn.fusionfuture.bugu.user.vo.UserOauthVO;
import cn.fusionfuture.bugu.user.vo.WechatBindDetailsVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */

public interface IUmsUserWxMiniProgramAuthService extends IService<UmsUserAuthWechat> {


    WechatBindDetailsVO getWechatBind(String code, String userName, String avatarUrl, Integer gender);


    /**
     * 根据openid获取鉴权vo
     * @author thomas
     * @since 2020/9/15 10:13 上午
     * @param openid openid
     * @return cn.fusionfuture.bugu.user.vo.UserOauthVO
     **/
    UserOauthVO getBindUid(String openid);


}
