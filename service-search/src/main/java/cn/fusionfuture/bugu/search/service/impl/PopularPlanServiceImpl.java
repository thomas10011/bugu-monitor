package cn.fusionfuture.bugu.search.service.impl;

import cn.fusionfuture.bugu.search.service.IPopularPlanService;
import cn.fusionfuture.bugu.search.vo.PopularPlanDetailVO;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
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
    public List<JSONObject> queryPopularPlan(String keyWord, Integer pageNum, Integer pageSize, String monitorPlanType, String monitorPlanStatus, String pkPlanType, String pkPlanStatus) throws IOException {
        SearchRequest request = new SearchRequest("plan");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        QueryBuilder queryBuilder = QueryBuilders.matchQuery("tt", keyWord);

        sourceBuilder
                .from((pageNum - 1) * pageSize)
                .size(pageSize)
                .query(queryBuilder);

        request.source(sourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsString());

        }
        return Arrays.stream(response.getHits().getHits()).map(SearchHit hit -> JSONUtil.parseObj(hit));
        System.out.println(response.toString());

    }
}
