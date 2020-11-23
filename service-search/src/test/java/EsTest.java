import cn.fusionfuture.bugu.search.SearchServiceApplication;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author thomas
 * @version 1.0
 * @class EsTest
 * @description TODO
 * @date 2020/9/20 6:58 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchServiceApplication.class)
@Slf4j
public class EsTest {

    @Autowired
    RestHighLevelClient client;

    @Test
    public void loadContext() throws IOException {
        SearchRequest searchRequest = new SearchRequest("plan");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder
                .query(QueryBuilders.matchAllQuery())
                .from(1)
                .size(2);

        searchRequest.source(searchSourceBuilder);
//        GetRequest getRequest = new GetRequest("/plan/1");
        SearchResponse res = client.search(searchRequest, RequestOptions.DEFAULT);
//        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        log.info(res.toString());
    }
}
