package cn.fusionfuture.bugu.user.service;

import cn.fusionfuture.bugu.pojo.entity.UmsUserAuthWechat;
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
public interface IUmsUserAuthWechatService extends IService<UmsUserAuthWechat> {
    WechatBindDetailsVO getWechatBind(String code);
}
