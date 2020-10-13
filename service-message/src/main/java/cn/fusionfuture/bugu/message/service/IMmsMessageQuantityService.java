package cn.fusionfuture.bugu.message.service;

import java.util.Map;

/**
 * @author LiLan
 * @version 1.0
 * @class IMmsMessageQuantityService
 * @description 初次加载时返回消息总数
 * @date 2020/10/10 11:02
 */
public interface IMmsMessageQuantityService {
    Map<String ,Integer> GetMessageQuantity(Long userId);
}
