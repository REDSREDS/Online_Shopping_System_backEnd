package com.maxChen.onlineStore.repository;

import com.maxChen.onlineStore.dataobjective.OrderMaster;
import org.hibernate.criterion.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private final String email = "5512221596";

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("order2");
        orderMaster.setBuyerName("max");
        orderMaster.setBuyerEmail(email);
        orderMaster.setOrderAmount(new BigDecimal(250));
        repository.save(orderMaster);
        Assert.assertNotNull(orderMaster);
    }
    @Test
    public void findByBuyerEmail() {
        Page<OrderMaster> result = repository.findByBuyerEmail(email, PageRequest.of(1, 1));
        Assert.assertNotEquals(0, result.getTotalElements());
    }

    @Test
    public void findByOrderId() {
        Optional<OrderMaster> result = repository.findById("order4");
        System.out.println(result);
    }
}