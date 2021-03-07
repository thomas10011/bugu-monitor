package cn.fusionfuture.bugu.search.service.impl;

import cn.fusionfuture.bugu.search.dto.PlanTrendDTO;
import cn.fusionfuture.bugu.search.service.IPopularTrendService;
import cn.fusionfuture.bugu.search.vo.PopularTrendVO;
import cn.hutool.json.JSONUtil;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author lzr
 * @date 2021/3/3 18:41
 */
@Service
public class PopularTrendServiceImpl implements IPopularTrendService {

    @Autowired
    RestHighLevelClient client;

    @Override
    public PopularTrendVO queryPopularTrend(Long uid, Long pid, Integer pageNum, Integer pageSize) throws IOException {
        SearchRequest request = new SearchRequest("trend");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (uid != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("uid", uid));
        }
        if (pid != null){
            boolQueryBuilder.must(QueryBuilders.matchQuery("pid", pid));
        }

        sourceBuilder
                .from((pageNum - 1) * pageSize)
                .size(pageSize)
                .query(boolQueryBuilder);

        request.source(sourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        List<PlanTrendDTO> list = new LinkedList<>();
        for (SearchHit hit : response.getHits().getHits()) {
            // 要转化为dto传输，否则有可能因为Jackson无法将hutool中的JSONObject的null值序列化而报错
            list.add(JSONUtil.parseObj(hit.getSourceAsString()).toBean(PlanTrendDTO.class));
        }

        return new PopularTrendVO()
                .setPageNum(pageNum)
                .setPageSize(pageSize)
                .setList(list)
                .setTotal(response.getHits().getTotalHits().value);
    }

    @Override
    public void ratePopularTrend(Long pid) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("trend", pid.toString());
        Script script = new Script("ctx._source.lc += 1");
        updateRequest.script(script);
        client.update(updateRequest, RequestOptions.DEFAULT);
    }

    @Override
    public void cancelRatePopularTrend(Long pid) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("trend", pid.toString());
        Script script = new Script("ctx._source.lc -= 1");
        updateRequest.script(script);
        client.update(updateRequest, RequestOptions.DEFAULT);
    }

    @Override
    public void createPopularTrend(PlanTrendDTO planTrendDTO) throws IOException {
        IndexRequest request = new IndexRequest("trend")
                .id(planTrendDTO.getPid().toString())
                .source(JSONUtil.parseObj(planTrendDTO, false, true).toStringPretty(), XContentType.JSON);
        client.index(request, RequestOptions.DEFAULT);
    }

    @Override
    public void rateAgreeCount(Long pid) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("trend", pid.toString());
        Script script = new Script("ctx._source.ac += 1");
        updateRequest.script(script);
        client.update(updateRequest, RequestOptions.DEFAULT);
    }

    @Override
    public void cancelAgreeCount(Long pid) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("trend", pid.toString());
        Script script = new Script("ctx._source.ac -= 1");
        updateRequest.script(script);
        client.update(updateRequest, RequestOptions.DEFAULT);
    }

    @Override
    public void rateDisagreeCount(Long pid) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("trend", pid.toString());
        Script script = new Script("ctx._source.dac += 1");
        updateRequest.script(script);
        client.update(updateRequest, RequestOptions.DEFAULT);
    }

    @Override
    public void cancelDisagreeCount(Long pid) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("trend", pid.toString());
        Script script = new Script("ctx._source.dac -= 1");
        updateRequest.script(script);
        client.update(updateRequest, RequestOptions.DEFAULT);
    }

    @Override
    public void rateCommentQuantity(Long pid) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("trend", pid.toString());
        Script script = new Script("ctx._source.cq += 1");
        updateRequest.script(script);
        client.update(updateRequest, RequestOptions.DEFAULT);
    }

    @Override
    public void cancelCommentQuantity(Long pid) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("trend", pid.toString());
        Script script = new Script("ctx._source.cq -= 1");
        updateRequest.script(script);
        client.update(updateRequest, RequestOptions.DEFAULT);
    }

    @Override
    public void updateImageUrls(Long pid, List<String> imageUrls) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("trend", pid.toString());
        Map<String, Object> parameters = Collections.singletonMap("imageUrls", imageUrls);
        Script script = new Script(ScriptType.INLINE, "painless", "ctx._source.imageUrls = params.imageUrls", parameters);
        updateRequest.script(script);
        client.update(updateRequest, RequestOptions.DEFAULT);
    }

    @Override
    public void updateContent(Long pid, String content) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("trend", pid.toString());
        Map<String, Object> parameters = Collections.singletonMap("content", content);
        Script script = new Script(ScriptType.INLINE, "painless", "ctx._source.ct = params.content", parameters);
        updateRequest.script(script);
        client.update(updateRequest, RequestOptions.DEFAULT);
    }
}
