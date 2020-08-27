package cn.fusionfuture.bugu.message.service;

import cn.fusionfuture.bugu.message.vo.MessageVO;
import cn.fusionfuture.bugu.pojo.entity.MmsSystemMessage;
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
public interface IMmsSystemMessageService extends IService<MmsSystemMessage> {
    List<MessageVO> getAllSystem(Long id);

    List<MessageVO> getOneSystemAll(Long id, Long systemId);
}
