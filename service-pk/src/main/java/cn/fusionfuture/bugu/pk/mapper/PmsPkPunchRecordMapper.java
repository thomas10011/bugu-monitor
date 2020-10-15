package cn.fusionfuture.bugu.pk.mapper;

import cn.fusionfuture.bugu.pk.dto.SimplePunchDTO;
import cn.fusionfuture.bugu.pk.vo.BasicPunchVO;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPunchRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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

    /*
     * @author zws
     * @description 根据计划id和用户id查询计划的打卡情况
     * @create 2020/10/15 20:43
     * @update 2020/10/15 20:43
     * @param [planId]
     * @return java.util.List<cn.fusionfuture.bugu.pk.dto.UserAttendPlanSimplePunchDTO>
     **/
    List<SimplePunchDTO> querySimplePunchDTO(@Param("uid") Long uid, @Param("pid") Long pid);
}
