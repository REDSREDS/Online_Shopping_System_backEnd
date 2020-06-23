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
        orderDetail.setDetailId("19920");
        orderDetail.setOrderId("1232321");
        orderDetail.setProductIcon("http://mixian.com");
        orderDetail.setProductId("12321");
        orderDetail.setProductName("mixian");
        orderDetail.setProductPrice(new BigDecimal(10.0));
        orderDetail.setProductQuantity(10);
        repository.save(orderDetail);
    }

    @Test
    public void findByOrderId() {

        List<OrderDetail> result = repository.findByOrderId("1232321");
        Assert.assertNotEquals(0, result.size());
    }
}