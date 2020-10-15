package cn.fusionfuture.bugu.monitor.mapper;

import cn.fusionfuture.bugu.monitor.dto.SimplePunchDTO;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPunchRecord;
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
public interface PmsMonitorPunchRecordMapper extends BaseMapper<PmsMonitorPunchRecord> {

    /*
     * @author zws
     * @description 根据计划id在数据库中查询计划的打卡情况
     * @create 2020/10/15 19:39
     * @update 2020/10/15 19:39
     * @param [planId]
     * @return java.util.List<cn.fusionfuture.bugu.monitor.dto.SimplePunchDTO>
     **/
    List<SimplePunchDTO> querySimplePunchDTO(Long planId);
}
