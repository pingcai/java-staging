package me.pingcai.es.repositories;

import me.pingcai.es.document.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author huangpingcai
 * @since 2018/8/21 11:55
 * */
public interface EsUserRepository extends ElasticsearchRepository<User,Long> {
}
