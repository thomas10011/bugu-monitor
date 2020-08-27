package cn.fusionfuture.bugu.message.service;

import cn.fusionfuture.bugu.message.vo.MessageVO;
import cn.fusionfuture.bugu.pojo.entity.MmsSystemUser;
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
public interface IMmsSystemUserService extends IService<MmsSystemUser> {
    List<MessageVO> getAllSystem(Long id);

    List<MessageVO> getOneSystemAll(Long id, Long systemId);

    MmsSystemUser getSystemUser(Long id);

}
