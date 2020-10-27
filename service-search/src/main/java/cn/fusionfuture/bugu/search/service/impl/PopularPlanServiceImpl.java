package cn.fusionfuture.bugu.search.service.impl;

import cn.fusionfuture.bugu.search.dto.PopularPlanDTO;
import cn.fusionfuture.bugu.search.service.IPopularPlanService;
import cn.fusionfuture.bugu.search.vo.PopularPlanVO;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;


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
        // 关键词不为空则添加关键词查询
        if (!StrUtil.isEmpty(keyWord)) {
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
        List<PopularPlanDTO> list = new LinkedList<>();
        for (SearchHit hit : response.getHits().getHits()) {
            // 要转化为dto传输，否则有可能因为Jackson无法将hutool中的JSONObject的null值序列化而报错
            list.add(JSONUtil.parseObj(hit.getSourceAsString()).toBean(PopularPlanDTO.class));
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
    public void ratePopularPlan(Long pid) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("plan", pid.toString());
        Map<String, Object> parameters = Collections.singletonMap("count", 4);
        Script script = new Script("ctx._source.rt += 1");
        updateRequest.script(script);
        client.update(updateRequest, RequestOptions.DEFAULT);
    }

    @Override
    public void createPopularPlan(PopularPlanDTO popularPlanDTO) throws IOException {

        IndexRequest request = new IndexRequest("plan")
                .id(popularPlanDTO.getId().toString())
                .source(JSONUtil.parseObj(popularPlanDTO, false, true).toStringPretty(), XContentType.JSON);
        System.out.println(JSONUtil.parseObj(popularPlanDTO, false, true).toStringPretty());
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

    }

    @Override
    public void updatePlanStatus(Long planId, String status) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("plan", planId.toString());
        Map<String, Object> parameters = Collections.singletonMap("status", status);
        Script script = new Script(ScriptType.INLINE, "painless", "ctx._source.st = params.status", parameters);
        updateRequest.script(script);
        client.update(updateRequest, RequestOptions.DEFAULT);
    }

    @Override
    public void updatePlanHeadcount(Long planId, Integer headcount) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("plan", planId.toString());
        Map<String, Object> parameters = Collections.singletonMap("headcount", headcount);
        Script script = new Script(ScriptType.INLINE, "painless", "ctx._source.hc = params.headcount", parameters);
        updateRequest.script(script);
        client.update(updateRequest, RequestOptions.DEFAULT);

    }

    @Override
    public void updateUserAvatar(Long planId, String url) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("plan", planId.toString());
        Map<String, Object> parameters = Collections.singletonMap("url", url);
        Script script = new Script(ScriptType.INLINE, "painless", "ctx._source.at = params.url", parameters);
        updateRequest.script(script);
        client.update(updateRequest, RequestOptions.DEFAULT);
    }
}
