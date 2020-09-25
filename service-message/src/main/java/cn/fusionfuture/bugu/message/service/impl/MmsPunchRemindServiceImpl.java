package cn.fusionfuture.bugu.message.service.impl;

import cn.fusionfuture.bugu.message.util.PageUtil;
import cn.fusionfuture.bugu.message.vo.LikeVO;
import cn.fusionfuture.bugu.message.vo.PunchVO;
import cn.fusionfuture.bugu.pojo.entity.MmsPunchRemind;
import cn.fusionfuture.bugu.message.mapper.MmsPunchRemindMapper;
import cn.fusionfuture.bugu.message.service.IMmsPunchRemindService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiLan
 * @version 1.0
 * @class MmsPunchRemindServiceImpl
 * @description TODO
 * @date 2020/8/22 12:04
 */
@Service
public class MmsPunchRemindServiceImpl extends ServiceImpl<MmsPunchRemindMapper, MmsPunchRemind> implements IMmsPunchRemindService {

    @Autowired
    MmsPunchRemindMapper mmsPunchRemindMapper;

    @Override
    public void addPunchRemind(MmsPunchRemind mmsPunchRemind) {
        mmsPunchRemindMapper.insert(mmsPunchRemind);

    }

    @Override
    public PageInfo<PunchVO> getPunchRemind(Integer pn, Integer ps, Long id) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("receive_user_id", id);
        PageHelper.startPage(pn, ps);
        PageInfo<MmsPunchRemind> mmsPunchRemindList = new PageInfo<>(mmsPunchRemindMapper.selectByMap(columnMap));
        System.out.println("查询出数据");
        List<PunchVO> punchVOList = new ArrayList<>();
        for (MmsPunchRemind mmsPunchRemind : mmsPunchRemindList.getList()) {
            PunchVO punchVO = new PunchVO();
            punchVO.setId(mmsPunchRemind.getId());
            punchVO.setSendTime(mmsPunchRemind.getCreateTime());
            punchVO.setSendUserId(mmsPunchRemind.getSendUserId());
            punchVO.setReceiveUserId(mmsPunchRemind.getReceiveUserId());
            punchVO.setPlanId(mmsPunchRemind.getPlanId());
            punchVO.setPlanTypeId(mmsPunchRemind.getPlanTypeId());
            punchVO.setIsChecked(mmsPunchRemind.getIsChecked());
            punchVO.setIsHidden(mmsPunchRemind.getIsHidden());
            punchVOList.add(punchVO);
//          TODO:调用其他微服务获取完整数据

        }
        PageUtil pageUtil = new PageUtil();
        PageInfo<PunchVO> punchVOPageInfo = new PageInfo<>(punchVOList);
        pageUtil.copyAtrr(mmsPunchRemindList,punchVOPageInfo);
        return punchVOPageInfo;
    }
}
