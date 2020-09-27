package cn.fusionfuture.bugu.pk.mapper;

import cn.fusionfuture.bugu.pk.vo.BasicPunchVO;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPunchRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
public interface PmsPkPunchRecordMapper extends BaseMapper<PmsPkPunchRecord> {

    /*
     * TODO 根据打卡Id查询打卡一条打卡记录的基本信息(不包括图片）
     * @author zws
     * @since 2020/9/25 20:05
     * @param [punchId] 
     * @return java.util.List<cn.fusionfuture.bugu.pk.vo.BasicPunchVO> 
     **/
    BasicPunchVO queryBasicPunchVO(Long punchId);
    
}
