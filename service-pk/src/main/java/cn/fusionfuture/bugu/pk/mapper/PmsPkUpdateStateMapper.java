package cn.fusionfuture.bugu.pk.mapper;

public interface PmsPkUpdateStateMapper {

    /*
     * @author zws
     * @description 检测截至的计划
     * @create 2020/12/9 14:16
     * @update 2020/12/9 14:16
     * @param []
     * @return void
     **/
    void checkPlanIsEnd();

    /*
     * @author zws
     * @description 检测开始的计划
     * @create 2020/12/9 14:16
     * @update 2020/12/9 14:16
     * @param []
     * @return void
     **/
    void checkPlanIsStart();
}
