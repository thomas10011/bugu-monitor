package cn.fusionfuture.bugu.search.service.impl;

import cn.fusionfuture.bugu.pojo.constants.MonitorPlanStatus;
import cn.fusionfuture.bugu.pojo.constants.MonitorPlanType;
import cn.fusionfuture.bugu.pojo.constants.PkPlanStatus;
import cn.fusionfuture.bugu.pojo.constants.PkPlanType;
import cn.fusionfuture.bugu.search.dto.PopularPlanDTO;
import cn.fusionfuture.bugu.search.service.IPopularPlanService;
import cn.fusionfuture.bugu.search.vo.PopularPlanVO;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * @author thomas
 * @version 1.0
 * @class PopularPlanServiceImpl
 * @description TODO
 * @date 2020/9/21 5:24 下午
 */
@Service
public class PopularPlanServiceImpl implements IPopularPlanService {

    @Autowired
    RestHighLevelClient client;

    @Override
    public PopularPlanVO queryPopularPlan(String keyWord, Integer pageNum, Integer pageSize, List<String> planType, List<String> planStatus) throws IOException {
        SearchRequest request = new SearchRequest("plan");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (keyWord != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("tt", keyWord).fuzziness(Fuzziness.AUTO));
        }
        if (planType != null) {
            boolQueryBuilder.must(QueryBuilders.termsQuery("tp", planType));
        }
        if (planStatus != null) {
            boolQueryBuilder.must(QueryBuilders.termsQuery("st", planStatus));
        }

        sourceBuilder
                .from((pageNum - 1) * pageSize)
                .size(pageSize)
                .query(boolQueryBuilder);

        request.source(sourceBuilder);

        System.out.println(boolQueryBuilder.toString());

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        List<JSONObject> list = new LinkedList<>();
        for (SearchHit hit : response.getHits().getHits()) {
            list.add(JSONUtil.parseObj(hit.getSourceAsString()));
            System.out.println(hit.getSourceAsString());
        }

        System.out.println(response.toString());
        return new PopularPlanVO()
                .setPageNum(pageNum)
                .setPageSize(pageSize)
                .setList(list)
                .setTotal(response.getHits().getTotalHits().value);
    }

    @Override
    public void createPopularPlan(PopularPlanDTO popularPlanDTO) {

    }
}
