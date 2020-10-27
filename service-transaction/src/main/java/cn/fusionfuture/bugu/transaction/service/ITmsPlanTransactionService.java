package cn.fusionfuture.bugu.transaction.service;

import cn.fusionfuture.bugu.pojo.entity.TmsPlanTransaction;
import cn.fusionfuture.bugu.transaction.vo.PlanTransactionVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
public interface ITmsPlanTransactionService extends IService<TmsPlanTransaction> {

    PageInfo<PlanTransactionVO> queryFeatherExpenseByPage(Long uid, Integer pageNum, Integer pageSize);

    PageInfo<PlanTransactionVO> queryFeatherIncomeByPage(Long uid, Integer pageNum, Integer pageSize);

}
