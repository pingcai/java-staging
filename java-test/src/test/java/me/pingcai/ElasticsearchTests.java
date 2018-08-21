package me.pingcai;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import me.pingcai.dao.entity.User;
import me.pingcai.util.JsonUtils;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

/**
 * @author huangpingcai
 * @since 2018/8/2 10:18
 */
@Slf4j
public class ElasticsearchTests {

    public static final String INDEX = "test_index";

    public static final String CUSTOM_TYPE = "custom_type";

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
    public void testCreateIndex() {

        String settings = "{\"analysis\" : {\n" +
                "            \"analyzer\" : {\n" +
                "                \"ik\" : {\n" +
                "                    \"tokenizer\" : \"ik_smart\"\n" +
                "                }\n" +
                "            }\n" +
                "        }" +
                "}";

        CreateIndexResponse response = client.admin()
                .indices()
                .prepareCreate(INDEX)
                .setSettings(settings,XContentType.JSON)
                .get();
        System.out.println(JsonUtils.object2Json(response));
    }

    @Test
    public void testDeleteIndex() {
        DeleteIndexResponse response = client.admin().indices().prepareDelete(INDEX)
                .get();

        log.info("delete index, result: {}", response.isAcknowledged());
    }

    @Test
    public void testAddMapping() throws IOException {
        XContentBuilder xContentBuilder = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("properties")
                .startObject("id")
                .field("type").value("long")
                .endObject()
                .startObject("key")
                .field("type").value("keyword")
                .endObject()
                .startObject("name")
                .field("type").value("text")
                .field("analyzer").value("ik_smart") // 最小拆分单词
                .startObject("fields")
                .startObject("keyword")
                .field("ignore_above").value(255)
                .field("type").value("keyword")
                .endObject()
                .endObject()
                .endObject()
                .startObject("birthday")
                .field("type").value("date")
                .endObject()
                .startObject("comment")
                .field("type").value("text")
                .endObject()
                .endObject()
                .endObject();

        System.out.println(xContentBuilder.string());

        PutMappingRequest request = Requests.putMappingRequest(INDEX)
                .type(CUSTOM_TYPE)
                .source(xContentBuilder);

        client.admin().indices().putMapping(request).actionGet();

    }

    @Test
    public void testAddChineseDocument() {

        String[] terms = NAME.split("\\n");
        String[] comments = COMMENT.split("\\n");
        String[] keys = KEY.split("[\\t\\n]+");
        Random random = new Random();

        int count = 100;
        Map<String, Object> data;
        for (int i = 1; i < count; i++) {
            data = Maps.newHashMap();
            data.put("id", i);
            data.put("key", "" + keys[random.nextInt(keys.length)]);
            data.put("name", terms[random.nextInt(terms.length)]);
            data.put("birthday", LocalDateTime.now());
            data.put("comment",comments[random.nextInt(comments.length)]);
            IndexResponse indexResponse = client.prepareIndex(INDEX, CUSTOM_TYPE)
                    .setId(String.valueOf(i))
                    .setSource(data)
                    .get();
            System.out.println(i + "\t: " + indexResponse.status());
        }

    }

    @Test
    public void testUpdateDocument() {

        User tt = new User();
        tt.setName("updated");
        tt.setId(1L);
        tt.setAge((byte) 1);

        UpdateResponse response = client.prepareUpdate(INDEX, CUSTOM_TYPE, "1")
                .setDoc(JsonUtils.object2Json(tt), XContentType.JSON)
                .get();

        log.info("{}", JsonUtils.object2Json(response.getGetResult()));

        log.info("status : {}", response.status());

    }

    @Test
    public void testDeleteDocument() throws ExecutionException, InterruptedException {
        DeleteResponse response = client.prepareDelete(INDEX, CUSTOM_TYPE, "1").execute().get();
        log.info("deleted : {}", Objects.equals(response.getResult(), DocWriteResponse.Result.DELETED));
    }

    @Test
    public void testGet() {
        GetResponse response = client.prepareGet(INDEX, CUSTOM_TYPE, "1")
                .setRealtime(true)
                .get();

        response.getSourceAsMap().forEach((k, v) -> {
            log.info("{} : {}", k, v);
        });

        log.info("address : {}", response.remoteAddress().getAddress());

    }

    @Test
    public void testScrollSearch() {

        QueryBuilder qb = QueryBuilders.scriptQuery(new Script("doc['id'].value % 2 != 0"));

        SearchResponse scrollResp = client.prepareSearch(INDEX)
                .setScroll(new Scroll(new TimeValue(60000)))
                .setFrom(0)
                .setSize(10)
                .setTypes(CUSTOM_TYPE)
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

        log.info("search 1 hit : {}", client.search(srb1.request()).get().getHits().getTotalHits());
        log.info("search 2 hit : {}", client.search(srb2.request()).get().getHits().getTotalHits());

        // You will get all individual responses from MultiSearchResponse#getResponses()
        long nbHits = 0;
        for (MultiSearchResponse.Item item : sr.getResponses()) {
            SearchResponse response = item.getResponse();
            nbHits += response.getHits().getTotalHits();
        }
        log.info("hit size : {}", nbHits);
    }

    @Test
    public void testSearchMultiMatchQuery() {
        SearchResponse response = client.prepareSearch(INDEX)
                .setQuery(QueryBuilders.multiMatchQuery("tom", "name", "id").boost(1))
                .get();
        log.info("hits : {}", response.getHits().getTotalHits());
    }

    @Test
    public void testSearchChinese() {

        String text = "小米";

        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("name", text);

        // 指定查询分词器
        queryBuilder.analyzer("ik_smart");

        SearchResponse response = client.prepareSearch(INDEX)
                .setTypes(CUSTOM_TYPE)
                .setQuery(queryBuilder)
                .addSort(SortBuilders.fieldSort("id"))
                .get();

        SearchHits searchHits = response.getHits();

        System.out.println("total hits : " + searchHits.getTotalHits());

        Stream.of(searchHits.getHits()).forEach(hit -> {
            System.out.println(JsonUtils.object2Json(hit.getSourceAsMap()));
        });

    }

    private static final String KEY = "春天\t夏天\t秋天\t冬天\t描写季节\t六月\n" +
            "热\t冷\t太阳\t星星\t月亮\t山\n" +
            "花\t水\t声音\t阳光\t风\t雨\n" +
            "早晨\n" +
            "\n" +
            "中午\t下午(傍晚）\t晚上\t闪电\t雪\n" +
            "沙漠\t\t\t\t\t\n";

    private static final String NAME = "1、俗话说：好马不吃回头草；可俗话又说：浪子回头金不换！\n" +
            "2、俗话说：兔子不吃窝边草；可俗话又说：近水楼台先得月！\n" +
            "3、俗话说：宰相肚里能撑船；可俗话又说：有仇不报非君子！\n" +
            "4、俗话说：男子汉大丈夫，宁死不屈；可俗话又说：男子汉大丈夫，能屈能伸！\n" +
            "5、俗话说：知无不言，言无不尽；可俗话又说：交浅勿言深，沉默是金！\n" +
            "6、俗话说：车到山前必有路；可俗话又说：不撞南墙不回头！\n" +
            "7、俗话说：人不犯我，我不犯人；可俗话又说：先下手为强，后下手遭殃！\n" +
            "8、俗话说：礼轻情谊重；可俗话又说：礼多人不怪！\n" +
            "9、俗话说：一个好汉三个帮；可俗话又说：靠人不如靠己！\n" +
            "10、俗话说：人往高处走；可俗话又说：爬得高，摔得重！\n" +
            "11、俗话说：瘦死的骆驼比马大；可俗话又说：拔了毛的凤凰不如鸡！\n" +
            "12、俗话说：宁为玉碎，不为瓦全；可俗话又说：留得青山在，不怕没柴烧！\n" +
            "13、俗话说：人不可貌相，海水不可斗量；可俗话又说：人靠衣裳马靠鞍！\n" +
            "14、俗话说：苦海无边，回头是岸；可俗话又说：开弓没有回头箭！\n" +
            "15、俗话说：退一步海阔天空；可俗话又说：狭路相逢勇者胜！\n" +
            "16、俗话说：三百六十行，行行出状元；可俗话又说：万般皆下品，唯有读书高！\n" +
            "17、俗话说：书到用时方恨少；可俗话又说：百无一用是书生！\n" +
            "18、俗话说：金钱不是万能的；可俗话又说：有钱能使鬼推磨！\n" +
            "19、俗话说：出淤泥而不染；可俗话又说：近朱者赤，近墨者黑。\n" +
            "20、俗话说：青出于蓝而胜于蓝；可俗话又说：姜还是老的辣！";

    private static final String COMMENT = "始织把加强学习作为第一需要，不断提升自身执政能力\n" +
            "始织把加快发展作为第一要务，全力推动经济健康发展\n" +
            "始织把改善民生作为第一责任，切实维护社会和谐稳定\n" +
            "始织把队伍建设作为第一工程，努力营造清明政治环境\n" +
            "把纪律摆在前面，挺起精神脊梁\n" +
            "把责任扛在肩上，打造有为政康\n" +
            "把清廉落在实处，不断完善自我\n" +
            "把目标定在高点，追求永不止步\n" +
            "把改革年度的账本点清楚\n" +
            "把改革落实的打法弄清楚\n" +
            "把改革成效的思路理清楚\n" +
            "把改革遇到的问题搞清楚\n" +
            "把住路线关，堵住“歪门邪道”的人\n" +
            "把住话筒关，堵住“好念歪经”的人\n" +
            "把住网络关，堵住“造谣生事”的人\n" +
            "把住理论关，堵住“精神恍惚”的人\n" +
            "始终握牢方向盘，把好思想关\n" +
            "努力抬升标尺线，把好学习关\n" +
            "不断注入原动力，把好任务关\n" +
            "用心念好紧箍咒，把好作风关\n" +
            "切实筑牢防火墙，把好廉洁关\n" +
            "热情奉献，把满意留给群众\n" +
            "勤勉敬业，把口碑留给群众\n" +
            "甘守清贫，把实惠留给群众\n" +
            "把信访群众当“家人”\n" +
            "把群众来信当“家书”\n" +
            "把信访之事当“家事”\n" +
            "把信访工作当“家业”\n" +
            "把优势做强\n" +
            "把特色做特\n" +
            "把产业做大\n" +
            "把机制做活\n" +
            "把环境做优\n" +
            "把转变方式作为稳定增长的第一重点\n" +
            "把项目建设作为支撑发展的第一抓手\n" +
            "把改革创新作为克难攻坚的第一动力\n" +
            "把改善民生作为政府工作的第一导向\n" +
            "要努力塑造为民的形象\n" +
            "要努力塑造务实的形象\n" +
            "要努力塑造清廉的形象\n" +
            "联系群众要贴心\n" +
            "服务群众要全心\n" +
            "关心群众要真心\n" +
            "帮助群众要热心\n" +
            "接待群众要耐心\n" +
            "请教群众要虚心\n" +
            "宣传群众要实心\n" +
            "动员群众要细心\n" +
            "依靠群众要诚心\n" +
            "善待群众要恒心\n" +
            "要给领导干部“长条凳”\n" +
            "要给普通群众“麦克风”\n" +
            "要给权力使用“放大镜”\n" +
            "要给民生多用“显微镜”\n" +
            "要追求名节，不要追名逐利\n" +
            "要以权谋事，不要以权谋私\n" +
            "要有所畏惧，不要无所畏惧\n" +
            "常想立身之本，既要有本事，还要守本分\n" +
            "常修为官之德，既要掌好权，还要用好权\n" +
            "常思贪欲之害，既要会干事，还要不出事\n" +
            "常怀律已之心，既要重大节，还要慎小节";

}
