package cn.fusionfuture.bugu.search.vo;

import cn.fusionfuture.bugu.search.dto.PlanTrendDTO;
import cn.fusionfuture.bugu.search.dto.PopularPlanDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author zws
 * @version 1.0
 * @class PopularTrendVO
 * @description 用户在动态界面进行显示
 * @date 2020/11/9 19:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PopularPlanVO", description="动态界面所需要的详细信息")
public class PopularTrendVO {
    @ApiModelProperty(name = "页面编号")
    private Integer pageNum;

    @ApiModelProperty(name = "页面大小")
    private Integer pageSize;

    @ApiModelProperty(name = "查询到的记录总数")
    private Long total;

    @ApiModelProperty(name = "数据列表")
    private List<PlanTrendDTO> list;
}