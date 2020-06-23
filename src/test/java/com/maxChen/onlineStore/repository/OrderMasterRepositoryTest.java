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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID = "12321";

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("51513151");
        orderMaster.setBuyerAddress("earth");
        orderMaster.setBuyerName("max");
        orderMaster.setBuyerOpenid("12321");
        orderMaster.setBuyerPhone("551222222");
        orderMaster.setOrderAmount(new BigDecimal(8.8));
        repository.save(orderMaster);
        Assert.assertNotNull(orderMaster);
    }
    @Test
    public void findByBuyerOpenid() {
        Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID, PageRequest.of(1, 1));
        Assert.assertNotEquals(0, result.getTotalElements());
    }
}