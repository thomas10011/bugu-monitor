package cn.fusionfuture.bugu.message.service.impl;

import cn.fusionfuture.bugu.message.feign.PkFeignService;
import cn.fusionfuture.bugu.message.util.PageUtil;
import cn.fusionfuture.bugu.message.vo.CommentVO;
import cn.fusionfuture.bugu.message.vo.EnrollVO;
import cn.fusionfuture.bugu.pojo.entity.MmsCommentRemind;
import cn.fusionfuture.bugu.pojo.entity.MmsEnrollRemind;
import cn.fusionfuture.bugu.message.mapper.MmsEnrollRemindMapper;
import cn.fusionfuture.bugu.message.service.IMmsEnrollRemindService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
     * TODO 
     * @author LiLan
     * @since 2020/8/21 17:48
     * @param mmsEnrollRemind
     * @return void 
     **/
    @Override
    public void addEnrollRemind(MmsEnrollRemind mmsEnrollRemind){
        mmsEnrollRemindMapper.insert(mmsEnrollRemind);

    }

   /**
    * TODO
    * @author LiLan
    * @since 2020/8/21 18:01
    * @param receiveUserId
    * @return java.util.List<cn.fusionfuture.bugu.message.vo.EnrollVO> 
    **/
    @Override
    public PageInfo<EnrollVO> getEnrollRemind(Integer pn, Integer ps, Long receiveUserId){

      Map<String,Object> columnMap = new HashMap<>();
      columnMap.put("receive_user_id",receiveUserId);
      PageHelper.startPage(pn, ps);
      PageInfo<MmsEnrollRemind> mmsEnrollRemindList =new PageInfo<>(mmsEnrollRemindMapper.selectByMap(columnMap)) ;
      System.out.println("查询出数据");
      List<EnrollVO> enrollVOList = new ArrayList<>();
      for(MmsEnrollRemind mmsEnrollRemind:mmsEnrollRemindList.getList()){
          EnrollVO enrollVO = new EnrollVO();
          enrollVO.setId(mmsEnrollRemind.getId());
          enrollVO.setSendTime(mmsEnrollRemind.getCreateTime());
          enrollVO.setSendUserId(mmsEnrollRemind.getSendUserId());
          enrollVO.setReceiveUserId(mmsEnrollRemind.getReceiveUserId());
          enrollVO.setPlanId(mmsEnrollRemind.getPlanId());
          enrollVO.setPlanTypeId(mmsEnrollRemind.getPlanTypeId());
          enrollVO.setIsChecked(mmsEnrollRemind.getIsChecked());
          enrollVO.setIsHidden(mmsEnrollRemind.getIsHidden());
          enrollVOList.add(enrollVO);
//          TODO:调用其他微服务获取完整数据

      }
        PageUtil pageUtil = new PageUtil();
        PageInfo<EnrollVO> enrollVOPageInfo = new PageInfo<>(enrollVOList);
        pageUtil.copyAtrr(mmsEnrollRemindList,enrollVOPageInfo);
        return enrollVOPageInfo;
    }
}
