package cn.fusionfuture.bugu.user.service.impl;

import cn.fusionfuture.bugu.pojo.entity.UmsUser;
import cn.fusionfuture.bugu.user.mapper.UmsUserMapper;
import cn.fusionfuture.bugu.user.service.IUmsUserService;
import cn.fusionfuture.bugu.user.vo.UserDetailsVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
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
    public UserDetailsVO getPersonalDetails(Long id) {
        UmsUser umsUser = umsUserMapper.selectById(id);

        UserDetailsVO userDetailsVO = new UserDetailsVO();
        userDetailsVO.setName(umsUser.getUserName());
        userDetailsVO.setAvator(umsUser.getAvatarUrl());
        userDetailsVO.setFollowQuantity(umsUser.getFollowQuantity());
        userDetailsVO.setFansQuantity(umsUser.getFansQuantity());
        userDetailsVO.setGender(umsUser.getGender());
        userDetailsVO.setFeatherBalance(umsUser.getFeatherBalance());
        userDetailsVO.setCashBalance(umsUser.getCashBalance());
        // TODO: 远程调用获取今日羽毛数变化
        userDetailsVO.setFeatherChange(new BigDecimal(0));

        BeanUtils.copyProperties(userDetailsVO, umsUser);

        return userDetailsVO;
    }
}
