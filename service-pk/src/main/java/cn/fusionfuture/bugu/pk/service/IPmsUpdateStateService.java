package cn.fusionfuture.bugu.pk.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zws
 * @since 2020-11-09
 */
public interface IPmsUpdateStateService{
    /*
     * @author zws
     * @description 检查计划是否开始
     * @create 2020/11/9 20:28
     * @update 2020/11/9 20:28
     * @param [uid]
     * @return void
     **/
    void checkPlanIsStart(Long uid) throws IOException;

    /*
     * @author zws
     * @description 检查计划是否结束
     * @create 2020/11/9
     * @update 2020/11/9
     * @param [uid]
     * @return void
     **/
    void checkPlanIsEnd(Long uid) throws IOException;

    /*
     * @author zws
     * @description 判断计划结果
     * @create 2020/11/9 20:46
     * @update 2020/11/9 20:46
     * @param [uid]
     * @return void
     **/
    void judgePlanResult(Long uid);

    /*
     * @author zws
     * @description 检测打卡是否已截至
     * @create 2020/11/11 21:39
     * @update 2020/11/11 21:39
     * @param [uid]
     * @return void
     **/
    //void checkPunchIsEnd(Long uid);

    /*
     * @author zws
     * @description 判断打卡结果
     * @create 2020/11/9 22:03
     * @update 2020/11/9 22:03
     * @param [punchId]
     * @return void
     **/
    void judgePunchResult(Long uid);
}
