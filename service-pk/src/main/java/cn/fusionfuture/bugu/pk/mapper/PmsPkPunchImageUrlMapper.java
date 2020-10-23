package cn.fusionfuture.bugu.pk.mapper;

import cn.fusionfuture.bugu.pojo.entity.PmsPkPunchImageUrl;
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
public interface PmsPkPunchImageUrlMapper extends BaseMapper<PmsPkPunchImageUrl> {

    /*
     * 根据打卡id查询打卡图片
     * @author zws
     * @since 2020/9/27 21:49
     * @param [punchId] 
     * @return java.util.List<java.lang.String> 
     **/
    List<String> queryImageByPunchId(Long punchId);
}
