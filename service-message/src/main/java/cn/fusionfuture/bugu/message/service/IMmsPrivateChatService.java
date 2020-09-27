package cn.fusionfuture.bugu.message.service;

import cn.fusionfuture.bugu.message.vo.MessageVO;
import cn.fusionfuture.bugu.pojo.entity.MmsPrivateChat;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

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

    PageInfo<MessageVO> getAllUserChat(Integer pn, Integer ps, Long id);

    PageInfo<MessageVO> getOneUserAllChat(Integer pn, Integer ps, Long id, Long sendId);
}
