package cn.fusionfuture.bugu.user.service.impl;

import cn.fusionfuture.bugu.pojo.constants.MiniProgramConstants;
import cn.fusionfuture.bugu.pojo.entity.UmsUser;
import cn.fusionfuture.bugu.pojo.entity.UmsUserAuthWechat;
import cn.fusionfuture.bugu.user.mapper.UmsUserAuthWechatMapper;
import cn.fusionfuture.bugu.user.mapper.UmsUserMapper;
import cn.fusionfuture.bugu.user.service.IUmsUserAuthWechatService;
import cn.fusionfuture.bugu.user.vo.WechatBindDetailsVO;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@Service
public class UmsUserAuthWechatServiceImpl extends ServiceImpl<UmsUserAuthWechatMapper, UmsUserAuthWechat> implements IUmsUserAuthWechatService {
    @Autowired
    UmsUserAuthWechatMapper umsUserAuthWechatMapper;

    @Autowired
    UmsUserMapper umsUserMapper;

    @Override
    public WechatBindDetailsVO getWechatBind(String code, String userName, String avatarUrl, Integer gender) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid", MiniProgramConstants.APP_ID);
        paramMap.put("secret", MiniProgramConstants.APP_SECRETE);
        paramMap.put("js_code", code);
        paramMap.put("grant_type", "authorization_code");
        // 调用微信后台接口获取openid和session_key
        String result = HttpUtil.get("https://api.weixin.qq.com/sns/jscode2session", paramMap);
        System.out.println(result);
        HashMap hashMap = JSON.parseObject(result, HashMap.class);
        String openid = hashMap.get("openid").toString();
        String sessionKey = hashMap.get("session_key").toString();
        // 查找表内是否已有该openid，若存在，则说明已经有该用户
        HashMap<String, Object> map = new HashMap<>();
        map.put("open_id", openid);
        List<UmsUserAuthWechat> searchResult = umsUserAuthWechatMapper.selectByMap(map);
        // 处理id
        Long uid;
        if (!searchResult.isEmpty()) {
            uid = searchResult.get(0).getUserId();
        } else {
            // 微信用户直接注册
            UmsUser umsUser = new UmsUser();
            umsUser.setUserName(userName)
                    .setAvatarUrl(avatarUrl)
                    .setGender(gender);
            umsUserMapper.insert(umsUser);
            uid = umsUser.getId();
            // 插入绑定表
            UmsUserAuthWechat umsUserAuthWechat = new UmsUserAuthWechat();
            umsUserAuthWechat.setOpenId(openid)
                    .setSessionKey(sessionKey)
                    .setUserId(uid);
            umsUserAuthWechatMapper.insert(umsUserAuthWechat);
        }

        WechatBindDetailsVO wechatBindDetailsVO = new WechatBindDetailsVO();
        wechatBindDetailsVO.setOpenid(openid);

        return wechatBindDetailsVO;
    }

    @Override
    public Long getBindUid(String openid) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("open_id", openid);
        List<UmsUserAuthWechat> searchResult = umsUserAuthWechatMapper.selectByMap(map);
        Long uid = null;
        if(!searchResult.isEmpty()){
            uid = searchResult.get(0).getUserId();
        }
        return uid;
    }
}
