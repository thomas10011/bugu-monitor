package cn.fusionfuture.bugu.transaction.service.impl;

import cn.fusionfuture.bugu.pojo.entity.TmsPlanTransaction;
import cn.fusionfuture.bugu.transaction.mapper.TmsPlanTransactionMapper;
import cn.fusionfuture.bugu.transaction.service.ITmsPlanTransactionService;
import cn.fusionfuture.bugu.transaction.vo.PlanTransactionVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@Service
public class TmsPlanTransactionServiceImpl extends ServiceImpl<TmsPlanTransactionMapper, TmsPlanTransaction> implements ITmsPlanTransactionService {

    @Autowired
    TmsPlanTransactionMapper planTransactionMapper;


    @Override
    public PageInfo<PlanTransactionVO> queryFeatherExpenseByPage(Long uid, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(planTransactionMapper.queryPlanExpense(uid));
    }

    @Override
    public PageInfo<PlanTransactionVO> queryFeatherIncomeByPage(Long uid, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(planTransactionMapper.queryPlanIncome(uid));
    }
}
