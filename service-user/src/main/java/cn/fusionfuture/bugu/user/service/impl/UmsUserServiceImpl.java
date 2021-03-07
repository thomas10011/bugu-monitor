package cn.fusionfuture.bugu.user.service.impl;

import cn.fusionfuture.bugu.pojo.entity.UmsUser;
import cn.fusionfuture.bugu.user.mapper.UmsUserMapper;
import cn.fusionfuture.bugu.user.service.IUmsUserService;
import cn.fusionfuture.bugu.user.vo.UserDetailsVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@Service
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements IUmsUserService {

    @Autowired
    private UmsUserMapper umsUserMapper;

    @Override
    public UserDetailsVO getPersonalDetails(Long id, Long uid) {
        UmsUser umsUser = umsUserMapper.selectById(id);
        UserDetailsVO userDetailsVO = new UserDetailsVO();
        if (umsUser == null) {
            return null;
        }

        if (id.equals(uid)) {
            userDetailsVO.setName(umsUser.getUserName());
            userDetailsVO.setAvatar(umsUser.getAvatarUrl());
            userDetailsVO.setFollowQuantity(umsUser.getFollowQuantity());
            userDetailsVO.setFansQuantity(umsUser.getFansQuantity());
            userDetailsVO.setGender(umsUser.getGender());
            userDetailsVO.setFeatherBalance(umsUser.getFeatherBalance());
            userDetailsVO.setCashBalance(umsUser.getCashBalance());
            // TODO: 远程调用获取今日羽毛数变化
            userDetailsVO.setFeatherChange(new BigDecimal(0));

            return userDetailsVO;
        } else {
            userDetailsVO.setName(umsUser.getUserName());
            userDetailsVO.setAvatar(umsUser.getAvatarUrl());
            userDetailsVO.setFollowQuantity(umsUser.getFollowQuantity());
            userDetailsVO.setFansQuantity(umsUser.getFansQuantity());
            userDetailsVO.setGender(umsUser.getGender());

            userDetailsVO.setFeatherBalance(new BigDecimal(1));
            userDetailsVO.setCashBalance(new BigDecimal(1));

            return userDetailsVO;
        }
    }

    @Override
    public HashMap<String, String> getDetailsForMessage(Long id) {
        UmsUser umsUser = umsUserMapper.selectById(id);
        HashMap<String, String> map = new HashMap<>(2);
        map.put("userName",umsUser.getUserName());
        map.put("avatarUrl",umsUser.getAvatarUrl());
        return map;
    }
}
