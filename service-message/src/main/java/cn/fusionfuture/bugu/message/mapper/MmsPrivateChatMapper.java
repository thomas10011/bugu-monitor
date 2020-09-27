package cn.fusionfuture.bugu.message.mapper;

import cn.fusionfuture.bugu.pojo.entity.MmsPrivateChat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
public interface MmsPrivateChatMapper extends BaseMapper<MmsPrivateChat> {
    List<MmsPrivateChat> queryUserChatVOList(Long receiveId);

}
