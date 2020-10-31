package cn.fusionfuture.bugu.message.service.impl;

import cn.fusionfuture.bugu.message.feign.MonitorFeignService;
import cn.fusionfuture.bugu.message.feign.PkFeignService;
import cn.fusionfuture.bugu.message.feign.UserFeignService;
import cn.fusionfuture.bugu.message.mapper.MmsCommentRemindMapper;
import cn.fusionfuture.bugu.message.service.IMmsCommentRemindService;
import cn.fusionfuture.bugu.message.util.PageUtil;
import cn.fusionfuture.bugu.message.vo.CommentVO;
import cn.fusionfuture.bugu.message.vo.PunchCommentVO;
import cn.fusionfuture.bugu.message.vo.feignvo.PunchForMessageDTO;
import cn.fusionfuture.bugu.pojo.entity.MmsCommentRemind;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiLan
 * @version 1.0
 * @class MmsCommentRemindServiceImpl
 * @description TODO
 * @date 2020/8/22 15:44
 */
@Service
public class MmsCommentRemindServiceImpl extends ServiceImpl<MmsCommentRemindMapper, MmsCommentRemind> implements IMmsCommentRemindService {
    final Integer MONITOR_PLAN = 1;
    final Integer PK_PLAN = 2;

    @Autowired
    private MmsCommentRemindMapper mmsCommentRemindMapper;

    @Autowired
    private MonitorFeignService monitorFeignService;

    @Autowired
    private PkFeignService pkFeignService;

    @Autowired
    private UserFeignService userFeignService;

    @Override
    public void addComment(MmsCommentRemind mmsCommentRemind) {

        mmsCommentRemindMapper.insert(mmsCommentRemind);
    }

    @Override
    public PageInfo<CommentVO> getCommentRemind(Integer pn, Integer ps,Long id) {
        Map<String, Object> columnMap = new HashMap<>();
        QueryWrapper<MmsCommentRemind> queryWrapper = new QueryWrapper<MmsCommentRemind>();
        queryWrapper.eq("receive_user_id", id)
                .eq("is_hidden",false)
                .orderByDesc("create_time");


        PageHelper.startPage(pn, ps);
        PageInfo<MmsCommentRemind> mmsCommentRemindList =new PageInfo<>(mmsCommentRemindMapper.selectList(queryWrapper)) ;
        //        更新为已读
        MmsCommentRemind updateEntity = new MmsCommentRemind();
        updateEntity.setIsChecked(true);
        mmsCommentRemindMapper.update(updateEntity,queryWrapper);
        System.out.println("查询出数据");
        List<CommentVO> commentVOList = new ArrayList<>();
        for (MmsCommentRemind mmsCommentRemind : mmsCommentRemindList.getList()) {
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(mmsCommentRemind,commentVO);

            HashMap<String,String> sender = userFeignService.getDetailsForMessage(commentVO.getSendUserId()).getData();
            commentVO.setSendUserName(sender.get("userName"));
            commentVO.setSendAvatarUrl(sender.get("avatarUrl"));
//            System.out.println(sender);

//           通过parentId获取父评论相关内容
            Long parentId = mmsCommentRemind.getParentId();
            if(parentId != null){
                MmsCommentRemind mmsCommentRemindParent = mmsCommentRemindMapper.selectById(parentId);
                commentVO.setParentConnent(mmsCommentRemindParent.getCommentContent());
                commentVO.setParentUserId(mmsCommentRemindParent.getSendUserId());
                HashMap<String,String> parentUser = userFeignService.getDetailsForMessage(mmsCommentRemindParent.getSendUserId()).getData();
                commentVO.setParentUserName(parentUser.get("userName"));
            }
            //            commentVO.setParentUserId(mmsCommentRemindParent.getSendUserId());发出父评论的用户就是当前用户，即ReceiveUserId
            //          TODO:调用其他微服务获取完整数据
//            调用用户微服务获取用户头像和名称
//            System.out.println(mmsCommentRemind.getSendUserId());
//            Long sendId = 1305182747930718209L;
//            UserDetailsVO sendUser = userFeignService.getPersonalDetails(sendId);
//            commentVO.setSendUserName(sendUser.getName());
//            System.out.println(sendUser);
//            System.out.println(userFeignService.test());
//            获取打卡相关信息
            int planType=mmsCommentRemind.getPlanTypeId();
//            pk计划信息
            Long punchId = mmsCommentRemind.getPunchId();
            if(planType==PK_PLAN){
                PunchForMessageDTO punchForMessageDTO = pkFeignService.queryPunchForMessageDTO(punchId).getData();
                commentVO.setPlanPattern(punchForMessageDTO.getPlanPattern());
                commentVO.setPlanName(punchForMessageDTO.getName());
                commentVO.setPunchContent(punchForMessageDTO.getContent());
                if(punchForMessageDTO.getImageUrls().size()>0){
                    commentVO.setPunchImageUrl(punchForMessageDTO.getImageUrls().get(0));
                }
            }else{
//                监督计划
                PunchForMessageDTO punchForMessageDTO = monitorFeignService.queryPunchForMessageDTO(punchId).getData();
                commentVO.setPlanPattern(punchForMessageDTO.getPlanPattern());
                commentVO.setPlanName(punchForMessageDTO.getName());
                commentVO.setPunchContent(punchForMessageDTO.getContent());
                if(punchForMessageDTO.getImageUrls().size()>0){
                    commentVO.setPunchImageUrl(punchForMessageDTO.getImageUrls().get(0));
                }
            }
            commentVOList.add(commentVO);
        }
        PageUtil pageUtil = new PageUtil();
        PageInfo<CommentVO> commentVOPageInfo = new PageInfo<>(commentVOList);
        pageUtil.copyAtrr(mmsCommentRemindList,commentVOPageInfo);
        return commentVOPageInfo;
    }

    @Override
    public List<PunchCommentVO> getPunchComment(Long id) {

        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("punch_id", id);
        List<MmsCommentRemind> mmsCommentRemindList = mmsCommentRemindMapper.selectByMap(columnMap);
        List<PunchCommentVO> commentVOList = new ArrayList<>();
        for (MmsCommentRemind mmsCommentRemind : mmsCommentRemindList) {
            PunchCommentVO commentVO = new PunchCommentVO();
            BeanUtils.copyProperties(mmsCommentRemind,commentVO);
//            commentVO.setId(mmsCommentRemind.getId());
//            commentVO.setSendTime(mmsCommentRemind.getCreateTime());
//            commentVO.setSendUserId(mmsCommentRemind.getSendUserId());
//            commentVO.setReceiveUserId(mmsCommentRemind.getReceiveUserId());
//            commentVO.setPunchId(mmsCommentRemind.getPunchId());
//            commentVO.setPlanTypeId(mmsCommentRemind.getPlanTypeId());
//            commentVO.setIsChecked(mmsCommentRemind.getIsChecked());
//            commentVO.setIsHidden(mmsCommentRemind.getIsHidden());
//            commentVO.setCommentContent(mmsCommentRemind.getCommentContent());
//            commentVO.setParentId(mmsCommentRemind.getParentId());

            //          TODO:调用其他微服务获取完整数据

            commentVOList.add(commentVO);
        }
        return commentVOList;
    }
}
