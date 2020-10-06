package cn.fusionfuture.bugu.user.service.impl;

import cn.fusionfuture.bugu.pojo.constants.MiniProgramConstants;
import cn.fusionfuture.bugu.pojo.entity.*;
import cn.fusionfuture.bugu.user.mapper.*;
import cn.fusionfuture.bugu.user.service.IUmsUserWxMiniProgramAuthService;
import cn.fusionfuture.bugu.user.vo.UserOauthVO;
import cn.fusionfuture.bugu.user.vo.WechatBindDetailsVO;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.JsonObject;
import io.micrometer.core.instrument.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class UmsUserWxMiniProgramAuthImpl extends ServiceImpl<UmsUserAuthWechatMapper, UmsUserAuthWechat> implements IUmsUserWxMiniProgramAuthService {
    @Autowired
    UmsUserAuthWechatMapper umsUserAuthWechatMapper;

    @Autowired
    UmsUserMapper umsUserMapper;

    @Autowired
    UmsMonitorAchievementMapper umsMonitorAchievementMapper;

    @Autowired
    UmsMonitorPlanAchievementMapper umsMonitorPlanAchievementMapper;

    @Autowired
    UmsPkPlanAchievementMapper umsPkPlanAchievementMapper;

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
        JSONObject hashMap = JSONUtil.parseObj(result);
        String openid = hashMap.get("openid").toString();
        String sessionKey = hashMap.get("session_key").toString();
        // 查找表内是否已有该openid，若存在，则说明已经有该用户
        HashMap<String, Object> map = new HashMap<>();
        map.put("open_id", openid);
        List<UmsUserAuthWechat> searchResult = umsUserAuthWechatMapper.selectByMap(map);
        Long uid;
        if (searchResult.isEmpty()) {
            // 直接注册
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
            //
            UmsMonitorAchievement umsMonitorAchievement = new UmsMonitorAchievement();
            umsMonitorAchievement.setUserId(uid)
                    .setAssistCount(0)
                    .setPlanCount(0)
                    .setSuccessCount(0)
                    .setVoteCount(0);
            umsMonitorAchievementMapper.insert(umsMonitorAchievement);
            //
            UmsMonitorPlanAchievement umsMonitorPlanAchievement = new UmsMonitorPlanAchievement();
            umsMonitorPlanAchievement.setUserId(uid)
                    .setParticipateCount(0)
                    .setPlanCount(0)
                    .setSuccessCount(0);
            umsMonitorPlanAchievementMapper.insert(umsMonitorPlanAchievement);
            //
            UmsPkPlanAchievement umsPkPlanAchievement = new UmsPkPlanAchievement();
            umsPkPlanAchievement.setUserId(uid)
                    .setDefeatCount(0)
                    .setPlanCount(0)
                    .setSuccessCount(0)
                    .setVictoryCount(0);
            umsPkPlanAchievementMapper.insert(umsPkPlanAchievement);

        } else {
            // 更新绑定表的sessionKey
            searchResult.get(0).setSessionKey(sessionKey);
            uid = searchResult.get(0).getUserId();
            // 更新用户表的avatarUrl
            umsUserMapper.selectById(uid).setAvatarUrl(avatarUrl);
        }

        HashMap<String,Object> loginParams = new HashMap<>();
        loginParams.put("grant_type","password");
        loginParams.put("client_id","client-app");
        loginParams.put("client_secret","123456");
        loginParams.put("username",openid);
        // 调用oauth接口获取登录后的token等信息
        result = HttpUtil.post("http://localhost:9527/oauth-service/oauth/token",loginParams);
        JSONObject object = JSONUtil.parseObj(result);

        WechatBindDetailsVO wechatBindDetailsVO = new WechatBindDetailsVO();
        wechatBindDetailsVO.setToken(object.getStr("access_token"));
        wechatBindDetailsVO.setRefreshToken(object.getStr("refresh_token"));
        wechatBindDetailsVO.setUid(uid.toString());

        return wechatBindDetailsVO;
    }

    @Override
    public UserOauthVO getBindUid(String openid) {
        UserOauthVO userOauthVO = new UserOauthVO();
        HashMap<String, Object> map = new HashMap<>();
        map.put("open_id", openid);
        List<UmsUserAuthWechat> searchResult = umsUserAuthWechatMapper.selectByMap(map);
        Long uid = null;
        if(searchResult.isEmpty()){
            return null;
        }
        List<String> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add("admin");
        grantedAuthorityList.add("user");
        userOauthVO.setUserName(searchResult.get(0).getUserId().toString()).setPassword("123123").setGrantedAuthorityList(grantedAuthorityList);
        userOauthVO.setIsEnabled(true);
        return userOauthVO;
    }
}
