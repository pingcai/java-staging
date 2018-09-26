package me.pingcai.reposiroty;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import me.pingcai.ApplicationContextTests;
import me.pingcai.dao.entity.Order;
import me.pingcai.dao.enums.OrderStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author huangpingcai
 * @since 2018/9/26 11:20
 */
@Slf4j
public class OrderRepositoryTest extends ApplicationContextTests {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testInsert() {

        List<Order> orders = Lists.newArrayList();

        int count = 1000;

        for (int i = 0; i < count; i++) {

            Order order = new Order();
            order.setStatus(i % 2 == 0 ? i % 4 == 0 ? OrderStatus.CANCEL : OrderStatus.DONE : OrderStatus.CREATED);
            order.setUserId(1L);
            order.setOrderName(UUID.randomUUID().toString().substring(0,12));
            order.setOrderDesc("订单");
            order.setCreateTime(new Date());
            order.setUpdateTime(new Date());
            orders.add(order);
        }
        orderRepository.batchInsert(orders);
    }
}