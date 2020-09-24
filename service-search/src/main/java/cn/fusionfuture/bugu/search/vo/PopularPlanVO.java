package cn.fusionfuture.bugu.search.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author thomas
 * @version 1.0
 * @class PopularPlanVO
 * @description TODO
 * @date 2020/9/24 8:46 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PopularPlanVO", description="首页热门计划所需要的详细信息")
public class PopularPlanVO {

}
