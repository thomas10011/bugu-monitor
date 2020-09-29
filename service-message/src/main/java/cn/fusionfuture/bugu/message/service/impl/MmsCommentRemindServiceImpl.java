package cn.fusionfuture.bugu.message.service.impl;

import cn.fusionfuture.bugu.message.feign.UserFeignService;
import cn.fusionfuture.bugu.message.mapper.MmsCommentRemindMapper;
import cn.fusionfuture.bugu.message.service.IMmsCommentRemindService;
import cn.fusionfuture.bugu.message.vo.CommentVO;
import cn.fusionfuture.bugu.message.vo.PunchCommentVO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.entity.MmsCommentRemind;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.fusionfuture.bugu.message.util.PageUtil;

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
    @Autowired
    private MmsCommentRemindMapper mmsCommentRemindMapper;

//    @Autowired
//    private UserFeignService userFeignService;

    @Override
    public void addComment(MmsCommentRemind mmsCommentRemind) {

        mmsCommentRemindMapper.insert(mmsCommentRemind);
    }

    @Override
    public PageInfo<CommentVO> getCommentRemind(Integer pn, Integer ps,Long id) {
        Map<String, Object> columnMap = new HashMap<>();
        QueryWrapper<MmsCommentRemind> queryWrapper = new QueryWrapper<MmsCommentRemind>();
        queryWrapper.eq("receive_user_id", id);
        queryWrapper.eq("is_hidden",false);

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
            commentVO.setId(mmsCommentRemind.getId());
            commentVO.setSendTime(mmsCommentRemind.getCreateTime());
            commentVO.setSendUserId(mmsCommentRemind.getSendUserId());
            commentVO.setReceiveUserId(mmsCommentRemind.getReceiveUserId());
            commentVO.setPunchId(mmsCommentRemind.getPunchId());
            commentVO.setPlanTypeId(mmsCommentRemind.getPlanTypeId());
            commentVO.setIsChecked(mmsCommentRemind.getIsChecked());
            commentVO.setIsHidden(mmsCommentRemind.getIsHidden());
            commentVO.setCommentContent(mmsCommentRemind.getCommentContent());
            commentVO.setParentId(mmsCommentRemind.getParentId());

//           通过parentId获取父评论相关内容

            Long parentId = mmsCommentRemind.getParentId();
            if(parentId != null){
                MmsCommentRemind mmsCommentRemindParent = mmsCommentRemindMapper.selectById(parentId);
                commentVO.setParentConnent(mmsCommentRemindParent.getCommentContent());

            }
            //            commentVO.setParentUserId(mmsCommentRemindParent.getSendUserId());发出父评论的用户就是当前用户，即ReceiveUserId
            //          TODO:调用其他微服务获取完整数据
//            调用用户微服务获取用户头像和名称
//            CommonResult<?> sendUser = userFeignService.getPersonalDetails(commentVO.getSendUserId());
//            commentVO.setSendUserName(sendUser.getData());
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
            commentVO.setId(mmsCommentRemind.getId());
            commentVO.setSendTime(mmsCommentRemind.getCreateTime());
            commentVO.setSendUserId(mmsCommentRemind.getSendUserId());
            commentVO.setReceiveUserId(mmsCommentRemind.getReceiveUserId());
            commentVO.setPunchId(mmsCommentRemind.getPunchId());
            commentVO.setPlanTypeId(mmsCommentRemind.getPlanTypeId());
            commentVO.setIsChecked(mmsCommentRemind.getIsChecked());
            commentVO.setIsHidden(mmsCommentRemind.getIsHidden());
            commentVO.setCommentContent(mmsCommentRemind.getCommentContent());
            commentVO.setParentId(mmsCommentRemind.getParentId());

            //          TODO:调用其他微服务获取完整数据

            commentVOList.add(commentVO);
        }
        return commentVOList;
    }
}
