package me.pingcai;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.reposiroty.TestRepository;
import me.pingcai.util.JsonUtils;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.UpdateByQueryAction;
import org.elasticsearch.index.reindex.UpdateByQueryRequestBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

/**
 * @author huangpingcai
 * @since 2018/8/2 10:18
 */
@Slf4j
public class ElasticsearchTests {

    public static final String INDEX = "test_index";
    public static final String TYPE = "type";


    Client client;

    @Before
    public void before() throws UnknownHostException {
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
    }

    @After
    public void after() {
        client.close();
    }

    @Test
    public void testIndex() {
        me.pingcai.dao.entity.Test test = new me.pingcai.dao.entity.Test();

        int start = 1390;
        int end = start + 29;

        for (int i = start; i < end; i++) {
            test.setId(Integer.toUnsignedLong(i));
            test.setName("vio");
            test.setAge((byte) 100);

            IndexResponse response = client.prepareIndex(INDEX, TYPE)
                    .setOpType(DocWriteRequest.OpType.CREATE)
                    .setSource(JsonUtils.object2Json(test), XContentType.JSON)
                    .setId(String.valueOf(test.getId()))
                    .get();

            log.info("id : {}", response.getId());
            log.info("index : {}", response.getIndex());
            log.info("sharedInfo : {}", JsonUtils.object2Json(response.getShardInfo()));
            log.info("result : {}", response.getResult().toString());

        }

    }

    @Test
    public void testGet() {
        GetResponse response = client.prepareGet(INDEX, TYPE, "1000")
                .setRealtime(true)
                .get();

        response.getFields().forEach((k, v) -> {
            log.info("{} : {}", k, v);
        });

        log.info("address : {}", response.remoteAddress().getAddress());

    }

    @Test
    public void testUpdate() {

        me.pingcai.dao.entity.Test tt = new me.pingcai.dao.entity.Test();
        tt.setName("updated");
        tt.setId(1L);
        tt.setAge((byte) 1);

        UpdateResponse response = client.prepareUpdate(INDEX, TYPE, "1")
                .setDoc(JsonUtils.object2Json(tt), XContentType.JSON)
                .get();

        log.info("{}", JsonUtils.object2Json(response.getGetResult()));

        log.info("status : {}", response.status());

    }

    @Test
    public void testSearch() {

        QueryBuilder qb = QueryBuilders.scriptQuery(new Script("doc['id'].value % 2 != 0"));

        SearchResponse scrollResp = client.prepareSearch(INDEX)
                .setScroll(new Scroll(new TimeValue(60000)))
                .setFrom(0)
                .setSize(10)
                .setTypes(TYPE)
                .setQuery(qb)
                .get();

        do {
            for (SearchHit hit : scrollResp.getHits().getHits()) {
                log.info("hit : {}", JsonUtils.object2Json(hit.getSource()));
            }
            scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(60000)).get();
        }
        while (scrollResp.getHits().getHits().length != 0); // Zero hits mark the end of the scroll and the while loop.
    }

    @Test
    public void testMultiSearch() throws ExecutionException, InterruptedException {
        SearchRequestBuilder srb1 = client
                .prepareSearch().setQuery(QueryBuilders.rangeQuery("id").gt(100).lt(200)).setSize(1);
        SearchRequestBuilder srb2 = client
                .prepareSearch().setQuery(QueryBuilders.matchQuery("name", "tom")).setSize(1);

        MultiSearchResponse sr = client.prepareMultiSearch()
                .add(srb1)
                .add(srb2)
                .get();

        log.info("search 1 hit : {}",client.search(srb1.request()).get().getHits().getTotalHits());
        log.info("search 2 hit : {}",client.search(srb2.request()).get().getHits().getTotalHits());

        // You will get all individual responses from MultiSearchResponse#getResponses()
        long nbHits = 0;
        for (MultiSearchResponse.Item item : sr.getResponses()) {
            SearchResponse response = item.getResponse();
            nbHits += response.getHits().getTotalHits();
        }
        log.info("hit size : {}", nbHits);
    }

    @Test
    public void testQuery() {
        SearchResponse response = client.prepareSearch(INDEX)
        .setQuery(QueryBuilders.multiMatchQuery("tom","name","id").boost(1))
        .get();
        log.info("hits : {}",response.getHits().getTotalHits());
    }


    @Test
    public void testDELETE() throws ExecutionException, InterruptedException {
        DeleteResponse response = client.prepareDelete(INDEX,TYPE,"1").execute().get();
        log.info("deleted : {}",Objects.equals(response.getResult(), DocWriteResponse.Result.DELETED));
    }

    @Test
    public void testAdmin() {
        DeleteIndexResponse response = client.admin().indices().prepareDelete(INDEX)
        .get();

        log.info("delete index, result: {}",response.isAcknowledged());
    }


}
