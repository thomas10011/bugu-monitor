package cn.fusionfuture.bugu.message.service;

import cn.fusionfuture.bugu.message.vo.PrivateChatVO;
import cn.fusionfuture.bugu.pojo.entity.MmsPrivateChat;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
public interface IMmsPrivateChatService extends IService<MmsPrivateChat> {
    void sendPraivateChat(MmsPrivateChat mmsPrivateChat);

    List<PrivateChatVO> getAllUserChat(Long id);

    List<PrivateChatVO> getOneUserAllChat(Long id,Long sendId);
}
