package cn.fusionfuture.bugu.pk.service.impl;

import cn.fusionfuture.bugu.pk.mapper.PmsPkPatternMapper;
import cn.fusionfuture.bugu.pk.vo.PkPlanPatternVO;
import cn.fusionfuture.bugu.pk.vo.PkPlanStatusVO;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPlanStatus;
import cn.fusionfuture.bugu.pk.mapper.PmsPkPlanStatusMapper;
import cn.fusionfuture.bugu.pk.service.IPmsPkPlanStatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
@Service
public class PmsPkPlanStatusServiceImpl extends ServiceImpl<PmsPkPlanStatusMapper, PmsPkPlanStatus> implements IPmsPkPlanStatusService {

    @Autowired
    PmsPkPlanStatusMapper pkPlanStatusMapper;

    @Override
    public List<PkPlanStatusVO> queryPkPlanStatus() {
        return pkPlanStatusMapper.queryPkPlanStatus();
    }
}
