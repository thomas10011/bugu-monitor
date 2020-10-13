package cn.fusionfuture.bugu.search.vo;

import cn.fusionfuture.bugu.search.dto.PopularPlanDTO;
import cn.hutool.json.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

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

    @ApiModelProperty(name = "页面编号")
    private Integer pageNum;

    @ApiModelProperty(name = "页面大小")
    private Integer pageSize;

    @ApiModelProperty(name = "该条件下查询到的记录总数")
    private Long total;

    @ApiModelProperty(name = "数据列表")
    private List<PopularPlanDTO> list;

}
