package cn.fusionfuture.bugu.monitor.service;

/*
 * @author zws
 * @description 服务类
 * @create 2020/11/12 11:30
 * @update 2020/11/12 11:30
 * @param
 * @return
 **/

import java.io.IOException;

public interface IPmsUpdateStateService {

    /*
     * @author zws
     * @description 检查计划是否开始
     * @create 2020/11/12 11:31
     * @update 2020/11/12 11:31
     * @param [uid]
     * @return void
     **/
    void checkPlanIsStart() throws IOException;

   /*
    * @author zws
    * @description 检查计划是否结束
    * @create 2020/11/12 11:31
    * @update 2020/11/12 11:31
    * @param [uid]
    * @return void
    **/
    void checkPlanIsEnd() throws IOException;

   /*
    * @author zws
    * @description 判断计划结果
    * @create 2020/11/12 11:31
    * @update 2020/11/12 11:31
    * @param [uid]
    * @return void
    **/
    void judgePlanResult();

    /*
     * @author zws
     * @description 判断打卡结果
     * @create 2020/11/12 11:32
     * @update 2020/11/12 11:32
     * @param [uid]
     * @return void
     **/
    void judgePunchResult();
}
