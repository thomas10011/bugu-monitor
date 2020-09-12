package cn.fusionfuture.bugu.pk.service.impl;

import cn.fusionfuture.bugu.pk.vo.PkPlanPatternVO;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPattern;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPattern;
import cn.fusionfuture.bugu.pk.mapper.PmsPkPatternMapper;
import cn.fusionfuture.bugu.pk.service.IPmsPkPatternService;
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
public class PmsPkPatternServiceImpl extends ServiceImpl<PmsPkPatternMapper, PmsPkPattern> implements IPmsPkPatternService {

    @Autowired
    PmsPkPatternMapper pkPatternMapper;

    @Override
    public List<PkPlanPatternVO> queryPkPlanPattern() {
        return pkPatternMapper.queryPkPlanPattern();
    }

}
