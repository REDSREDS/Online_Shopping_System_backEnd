package com.maxChen.onlineStore.repository;

import com.maxChen.onlineStore.dataobjective.OrderDetail;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("detail4");
        orderDetail.setOrderId("order2");
        orderDetail.setProductIcon("http://detail4.com");
        orderDetail.setProductId("4");
        orderDetail.setProductName("product4");
        orderDetail.setProductPrice(new BigDecimal(40.0));
        orderDetail.setProductQuantity(4);
        repository.save(orderDetail);
    }

    @Test
    public void findByOrderId() {

        List<OrderDetail> result = repository.findByOrderId("order1");
        Assert.assertNotEquals(0, result.size());
    }
}