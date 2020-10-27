package cn.fusionfuture.bugu.transaction.mapper;

import cn.fusionfuture.bugu.pojo.entity.TmsPlanTransaction;
import cn.fusionfuture.bugu.transaction.vo.PlanTransactionVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
public interface TmsPlanTransactionMapper extends BaseMapper<TmsPlanTransaction> {

    List<PlanTransactionVO> queryPlanIncome(Long uid);

    List<PlanTransactionVO> queryPlanExpense(Long uid);
}
