package cn.fusionfuture.bugu.message.service.impl;

import cn.fusionfuture.bugu.message.feign.PkFeignService;
import cn.fusionfuture.bugu.message.vo.EnrollVO;
import cn.fusionfuture.bugu.pojo.entity.MmsCommentRemind;
import cn.fusionfuture.bugu.pojo.entity.MmsEnrollRemind;
import cn.fusionfuture.bugu.message.mapper.MmsEnrollRemindMapper;
import cn.fusionfuture.bugu.message.service.IMmsEnrollRemindService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@Service
public class MmsEnrollRemindServiceImpl extends ServiceImpl<MmsEnrollRemindMapper, MmsEnrollRemind> implements IMmsEnrollRemindService {

   @Autowired
   MmsEnrollRemindMapper mmsEnrollRemindMapper;

   @Autowired
    PkFeignService pkFeignService;

    /**
     *
     * @param mmsEnrollRemind
     * @return
     */
    @Override
    public void addEnrollRemind(MmsEnrollRemind mmsEnrollRemind){
        mmsEnrollRemindMapper.insert(mmsEnrollRemind);

    }

    /**
     *
     * @param receiveUserId
     * @return
     */
    @Override
    public List<EnrollVO> getEnrollRemind(Long receiveUserId){

      Map<String,Object> columnMap = new HashMap<>();
      columnMap.put("receive_user_id",receiveUserId);
      List<MmsEnrollRemind> mmsEnrollRemindList = mmsEnrollRemindMapper.selectByMap(columnMap);
      System.out.println("查询出数据");
      List<EnrollVO> enrollVOList = new ArrayList<>();
      for(MmsEnrollRemind mmsEnrollRemind:mmsEnrollRemindList){
          EnrollVO enrollVO = new EnrollVO();
          enrollVO.setId(mmsEnrollRemind.getId());
          enrollVO.setEnrollTime(mmsEnrollRemind.getCreateTime().toLocalTime());
          enrollVO.setSendUserId(mmsEnrollRemind.getSendUserId());
          enrollVO.setReceiveUserId(mmsEnrollRemind.getReceiveUserId());
          enrollVO.setPlanId(mmsEnrollRemind.getPlanId());
          enrollVO.setPlanTypeId(mmsEnrollRemind.getPlanTypeId());
          enrollVO.setIsChecked(mmsEnrollRemind.getIsChecked());
          enrollVO.setIsHidden(mmsEnrollRemind.getIsHidden());
          enrollVOList.add(enrollVO);
//          调用其他微服务获取完整数据

      }
      return enrollVOList;
    }
}
