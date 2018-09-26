package me.pingcai.reposiroty;

import me.pingcai.dao.entity.Order;
import me.pingcai.dao.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author huangpingcai
 * @since 2018/9/26 11:17
 */
@Repository
public class OrderRepository {

    @Autowired
    private OrderMapper orderMapper;

    public int deleteByPrimaryKey(Long id) {
        return orderMapper.deleteByPrimaryKey(id);
    }

    public int insert(Order record){
        return orderMapper.insert(record);
    }

    public int batchInsert(List<Order> orderList){
        return orderMapper.batchInsert(orderList);
    }
}
