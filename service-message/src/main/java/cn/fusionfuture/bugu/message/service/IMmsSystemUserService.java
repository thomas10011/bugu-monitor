package cn.fusionfuture.bugu.message.service;

import cn.fusionfuture.bugu.message.vo.MessageVO;
import cn.fusionfuture.bugu.pojo.entity.MmsSystemUser;
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
public interface IMmsSystemUserService extends IService<MmsSystemUser> {
    PageInfo<MessageVO> getAllSystem(Integer pn, Integer ps, Long id);

    PageInfo<MessageVO> getOneSystemAll(Integer pn, Integer ps,Long id, Long systemId);

    MmsSystemUser getSystemUser(Long id);

}
